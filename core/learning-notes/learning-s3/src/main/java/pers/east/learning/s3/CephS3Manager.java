package pers.east.learning.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.*;
import com.amazonaws.services.s3.transfer.model.UploadResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author eastFu
 * @Description //TODO
 * @date 2021/3/19 9:40
 */
public class CephS3Manager {

    private static final Logger logger = LoggerFactory.getLogger(CephS3Manager.class);

    private AmazonS3 s3Client;

    private TransferManager transferManager;

    private CephS3Manager() {
    }

    private CephS3Manager(String accessKey, String secretKey, String endpoint) {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        //注意：因为是本地方式，访问相应的S3文件系统，所以signingRegion可以默认为空。
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withClientConfiguration(new ClientConfiguration().withProtocol(Protocol.HTTP).withUseExpectContinue(false))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, ""))
                .withPathStyleAccessEnabled(true)
                .build();
        transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
    }

    // 常规文件上传 ：一次请求将文件上传, 有长度限制，31104000
    public boolean uploadRoutine(String filePath, String bucketName, String keyName) {
        try {
            PutObjectRequest request = new PutObjectRequest(bucketName, keyName, filePath);
            s3Client.putObject(request);
            return true;
        } catch (Exception e) {
            logger.error("uploadRoutine fail:" + ExceptionUtil.getStackTrace(e));
            return false;
        }finally {
            shutdown();
        }
    }

    // 分片上传 : 如果指定上传的本地文件大于16M，TransferManager会自动对文件进行分块，并发调用分块上传接口进行上传，大大提高上传文件的速度
    public boolean uploadSlice(String filePath, String bucketName, String keyName) {
        try {
            Upload upload = transferManager.upload(bucketName, keyName, new File(filePath));
            upload.waitForCompletion();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("uploadBySlice fail : " + ExceptionUtil.getStackTrace(e));
            return false;
        }finally {
            shutdown();
        }
    }


    // 流式上传 : 流式上传无法做到多个分块并发上传，只能一个分块一个分块顺序上传
    public boolean uploadStreaming(String filePath, String bucketName, String keyName) {
        try {
            File file = new File(filePath);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.length());
            InputStream inputStream = new FileInputStream(file);
            Upload upload = transferManager.upload(bucketName, keyName, inputStream, objectMetadata);
            UploadResult result = upload.waitForUploadResult();
            return true;
        } catch (Exception e) {
            logger.error("uploadStreaming fail : " + ExceptionUtil.getStackTrace(e));
            return false;
        }finally {
            shutdown();
        }
    }

    // 目录下所有文件上传 ：使用TransferManager将某个目录下的文件全部上传， 支持 包含活着不包含 子文件夹
    public boolean uploadFileDirectory(String fileDirectory, String bucketName, boolean includeSubdirectories) {
        try {
            MultipleFileUpload upload = transferManager.uploadDirectory(bucketName, null, new File(fileDirectory), includeSubdirectories);
            upload.waitForCompletion();
            return true;
        } catch (Exception e) {
            logger.error("uploadFileDirectory fail : " + ExceptionUtil.getStackTrace(e));
            return false;
        }finally {
            shutdown();
        }
    }


    // 使用transferManager完成对象下载
    public boolean download(String filePath, String bucketName, String keyName) {
        try {
            Download download = transferManager.download(bucketName, keyName, new File(filePath));
            download.waitForCompletion();
            return true;
        } catch (Exception e) {
            logger.error("download fail : " + ExceptionUtil.getStackTrace(e));
            return false;
        }finally {
            shutdown();
        }
    }

    public void shutdown() {
        if (transferManager != null) {
            transferManager.shutdownNow();
        }
    }


    public static CephS3Manager build(String accessKey, String secretKey, String endpoint) {
        return new CephS3Manager(accessKey, secretKey, endpoint);
    }

    public static void main(String[] args) {

        System.out.println(CephS3Manager.build("ZIC9F9ZREOZT7UKB6ADF","kWbX3lUojwwu8vybB8lvhSnYavzgzPfpIEPtFNZM","ceph-sync-b.autohome.com.cn").uploadSlice("d:/1.png","001-test","3.png"));

//        System.out.println(CephS3Manager.build("ZIC9F9ZREOZT7UKB6ADF","kWbX3lUojwwu8vybB8lvhSnYavzgzPfpIEPtFNZM","ceph-sync-b.autohome.com.cn").uploadSlice("d:/1.png","001-test","3.png"));


//        System.out.println(CephS3Manager.build("ZIC9F9ZREOZT7UKB6ADF","kWbX3lUojwwu8vybB8lvhSnYavzgzPfpIEPtFNZM","ceph-sync-b.autohome.com.cn").uploadSlice("d:/1.png","001-test","1.png"));


//        System.out.println(CephS3Manager.build("ZIC9F9ZREOZT7UKB6ADF","kWbX3lUojwwu8vybB8lvhSnYavzgzPfpIEPtFNZM","10.28.5.78").uploadSlice("d:/1.png","001-test","1.png"));


    }

}
