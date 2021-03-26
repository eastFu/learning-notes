package pers.east.learning.s3;

import com.amazonaws.*;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferProgress;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author eastFu
 * @Description //TODO
 * @date 2021/3/19 9:40
 */
public class CephS3Manager2 {

    private static int count=0;
    public static void main(String[] args) throws IOException {

        //AWSCredentials credentials = new BasicAWSCredentials("N8868192AN0AF3AJWAKE", "7wiXrfAWTdmy1YS8DtNTfCEHJdM5ifC5H0MrFkFR");

        AWSCredentials credentials = new BasicAWSCredentials("ZIC9F9ZREOZT7UKB6ADF", "kWbX3lUojwwu8vybB8lvhSnYavzgzPfpIEPtFNZM");
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        clientConfig.setUseExpectContinue(false);//发送小文件时，减少延迟。
        final AmazonS3 s3 = new AmazonS3Client(credentials, clientConfig);


        S3ClientOptions scop = S3ClientOptions.builder().disableChunkedEncoding().setPathStyleAccess(true).build();
        s3.setS3ClientOptions(scop);

//        s3.setEndpoint("10.28.6.127");
        s3.setEndpoint("ceph-sync-a.autohome.com.cn");
//        s3.setEndpoint("10.28.5.78");
//        s3.setEndpoint("ceph-sync-b.autohome.com.cn");

        final String bucketName = "test";
        //final String bucketName = "flvs";
        //http://m10.play.vp.autohome.com.cn/flvs/0A33363922E51BDE/2017-04-05/68450AF1643DD799-200.mp4
        //final String keyPrefix = "2017-03-28/DC3B94FD6EBC969A-100";
        //final String keyPrefix = "/flvs/FF91E122A113F07F/2017-03-28/DC3B94FD6EBC969A-100";


        try {

            Long starttime=System.currentTimeMillis();
            //创建桶
            //createBucket(s3,bucketName);
            //取出所有的桶
            //	queryBucketList(s3);
            //向桶里上传文件
            //	String path = "E:\\DC3B94FD6EBC969A-100";
            final String path1 = "d://1.png";

            //	geturl(s3, bucketName, keyPrefix+"/index.m3u8");
            //	getFileListName(s3, bucketName);
            uploadMultFiles(s3, bucketName, "1.png",path1,System.currentTimeMillis());
//		for (int i = 0; i < 1; i++) {
//			new Thread(){
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					uploadPart(s3, bucketName, keyPrefix+Thread.currentThread().getName()+".flv",path1,System.currentTimeMillis());
//				}
//			}.start();
//		}


            //	uploadPart(s3, bucketName, keyPrefix+".mp4",path1,System.currentTimeMillis());
            //向桶里上传文件

            //	String localpath = "e:\\mp4";
            //	uploadDirectory(s3,bucketName,keyPrefix,path);  ///flvs/0A33363922E51BDE/2016-08-26/038B06A3B21BA1C5-100/4.ts


            //	uploadLoop(s3,bucketName,keyPrefix,new File(localpath));
            //得到url
            //	setBucketPrivate(s3, bucketName);
            //	geturl(s3, bucketName, keyPrefix+".mp4");
            //下载文件
            //	download(s3, bucketName, key);
            //循环删除文件
            //	deleteLoop(s3, bucketName, keyPrefix);
            //遍历桶里面所有的文件
            //	getFileListName(s3, bucketName);
            //删除文件
            //	deleteObject(s3, bucketName, keyPrefix);
            //删除桶
            //	deleteBucket(s3, bucketName);
            //	String keyPrefix1 = "build";
            //	setDirectoryFileACL(s3, bucketName,keyPrefix);
            Long endtime=System.currentTimeMillis();
            System.out.println("所耗费的时间为："+(endtime-starttime)+"ms");
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    private static void downFile(OutputStream out, InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuffer content = new StringBuffer();
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;

            content.append(line);
            content.append(System.getProperty("line.separator"));
        }
        byte[] contentInBytes = content.toString().getBytes();
        out.write(contentInBytes);
        out.flush();
        out.close();
    }


    private static void createBucket(AmazonS3 s3, String bucketName){

        System.out.println("===========================================");
        System.out.println("Creating bucket " + bucketName + "\n");

//        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
//        Bucket resp = s3.createBucket(createBucketRequest);
//        createBucket(s3, bucketName, CannedAccessControlList.PublicRead);

        /* 通过一个Bucket对象来创建 */
        CreateBucketRequest bucketObj = new CreateBucketRequest(null);// 构造函数入参为Bucket名称，可以为空
        bucketObj.setBucketName(bucketName);// 设置bucketObj名称
        bucketObj.setCannedAcl(CannedAccessControlList.PublicRead);
        s3.createBucket(bucketObj);// 创建Bucket
    }

    private static void queryBucketList(AmazonS3 s3){

        System.out.println("===========================================");
        System.out.println("Listing buckets");
        for (Bucket bucket : s3.listBuckets()) {
            System.out.println(" - " + bucket.getName());
            // 获取Bucket下所有对象
            ObjectListing objects = s3.listObjects(bucket.getName());
            do {
                for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                    System.out.println("\t" + objectSummary.getKey() + "\t" + objectSummary.getSize() + "\t"
                            + StringUtils.fromDate(objectSummary.getLastModified()) + "\t"
                            + objectSummary.getOwner());
                }
                objects = s3.listNextBatchOfObjects(objects);
            } while (objects.isTruncated());

        }
    }


    private static void putFile(AmazonS3 s3,String bucketName,String key,String path) throws IOException {
        System.out.println("===========================================");
        System.out.println("Uploading a new object to S3 from a file\n");
        //File file = createSampleFile(); //创建随机名称的文本文件
        File file = new File(path);
        System.out.println(file.getAbsolutePath());

        PutObjectRequest p = new PutObjectRequest(bucketName, key, file);

        PutObjectResult putResult=s3.putObject(p.withCannedAcl(CannedAccessControlList.PublicRead));
        //s3.setObjectAcl(bucketName, key,CannedAccessControlList.PublicRead);//设置对象权限
        System.out.println("Uploading a new object result is " +putResult.getVersionId());

    }

    private static void uploadDirectory(AmazonS3 s3,String bucketName, String key,final String localpath){
        TransferManager tm = new TransferManager(s3);

        MultipleFileUpload multipleFileUpload = tm.uploadDirectory(bucketName, key, new File(localpath), false);
        final TransferProgress p = multipleFileUpload.getProgress();


//	        multipleFileUpload.addProgressListener(new ProgressListener() {
//	            public void progressChanged(ProgressEvent progressEvent) {
//
//
//	                double percent = p.getPercentTransferred();
//	                System.out.print("\n" + localpath + " - " + "[ " + String.format("%.2f",percent) + "% ] "
//	                        + p.getBytesTransferred() + " / " + p.getTotalBytesToTransfer() );
//
//	                if (progressEvent.getEventType() == ProgressEventType.TRANSFER_COMPLETED_EVENT) {
//	                    System.out.println(" Upload complete!!!");
//	                }
//	            }
//	        });

        try{
            multipleFileUpload.waitForCompletion();
            setDirectoryFileACL(s3,bucketName,key);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            tm.shutdownNow();
        }
    }

    private static void uploadMultFiles( AmazonS3 s3, String bucketName, String key, final String localpath, long starttime) {

        final TransferManager tm = new TransferManager(s3);
        // TransferManager processes all transfers asynchronously,
        // so this call will return immediately.
        Upload upload = tm.upload(bucketName, key, new File(localpath));

        final TransferProgress p = upload.getProgress();


//        upload.addProgressListener(new ProgressListener() {
//            public void progressChanged(ProgressEvent progressEvent) {
//
//
//                double percent = p.getPercentTransferred();
//                System.out.print("\n" + localpath + " - " + "[ " + String.format("%.2f",percent) + "% ] "
//                        + p.getBytesTransferred() + " / " + p.getTotalBytesToTransfer() );
//
//                if (progressEvent.getEventType() == ProgressEventType.TRANSFER_COMPLETED_EVENT) {
//                    System.out.println(" Upload complete!!!");
//                }
//            }
//        });

        try {
            upload.waitForCompletion();
            s3.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
            System.out.println("Upload complete.");
        } catch (InterruptedException e) {
            System.out.println("Unable to upload file, upload was aborted.");
            e.printStackTrace();

        } catch (AmazonClientException amazonClientException) {
            System.out.println("Unable to upload file, upload was aborted.");
            amazonClientException.printStackTrace();
        }finally {
            tm.shutdownNow();
        }
        Long endtime=System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+" is upload over; 所耗费的时间为：" +(endtime-starttime)+"ms"+"; the num is"+getCount());
    }





    private static void geturl(AmazonS3 s3, String bucketName, String key) throws IOException {
        System.out.println("===========================================");
        System.out.println("get URL");
        //S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));
        //System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
        //打印文件内容
        //displayTextInputStream(object.getObjectContent());
//		AccessControlList bucketAcl = s3.getBucketAcl(bucketName);
//		System.out.println(bucketAcl.toString());
//
        URL url=getUrl(s3,bucketName,key);
        System.out.println(url.toString());

//		File file = new File("e:\\index.m3u8");
//		FileOutputStream out =new FileOutputStream(file);
//		downFile(out,object.getObjectContent());
    }


    private static void getFileListName(AmazonS3 s3, String bucketName) {
        System.out.println("===========================================");
        System.out.println("Listing objects");
        ObjectListing objectListing = s3.listObjects(new ListObjectsRequest().withBucketName(bucketName));

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize() + ")"+objectSummary.getLastModified());
        }

    }


    private static void deleteObject(AmazonS3 s3, String bucketName, String key) {
        System.out.println("===========================================");
        System.out.println("Deleting an object\n");
        boolean isexist=s3.doesObjectExist(bucketName, key);
        if(isexist){
            s3.deleteObject(bucketName, key);
        }
    }


    private static void deleteBucket(AmazonS3 s3, String bucketName) {
        System.out.println("===========================================");
        System.out.println("Deleting bucket " + bucketName + "\n");
        s3.deleteBucket(bucketName);
    }

    private static URL getUrl(AmazonS3 s3, String bucketName, String key){
        //获取一个request
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key);
//        Date expirationDate = null;
//        try {
//            expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-31");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        urlRequest.setMethod(HttpMethod.GET);
        //设置过期时间
//        urlRequest.setExpiration(expirationDate);
        //生成公用的url
        URL url = s3.generatePresignedUrl(urlRequest);
        return url;

    }

    public static void setBucketPrivate (AmazonS3 s3, String bucketName) {

//		AccessControlList bucketAcl = s3.getBucketAcl(bucketName);
//		System.out.println(bucketAcl.toString());
        AccessControlList acl = new AccessControlList();

        //acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
        //acl.grantPermission(new CanonicalGrantee("testuser"), Permission.FullControl);
        //acl.grantPermission(new EmailAddressGrantee("user@email.com"), Permission.WriteAcp);
        Owner owner = new Owner();
        owner.setId("testuser");
        owner.setDisplayName("First User");
        acl.setOwner(owner);

        //acl.revokeAllPermissions(GroupGrantee.AllUsers);
        //s3.setObjectAcl(bucketName, key, acl);
        //s3.setBucketAcl(bucketName, acl);

        s3.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        AccessControlList bucketAcl = s3.getBucketAcl(bucketName);
        System.out.println(bucketAcl.toString());

        s3.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }

    private static void setDirectoryFileACL(AmazonS3 s3, String bucketName, String keyPrefix) {
        // We only want the keys that are in the folder
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName).withPrefix(keyPrefix);
        ObjectListing objectListing;

        // Iterate over all the matching keys
        do {
            objectListing = s3.listObjects(listObjectsRequest);

            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                // Apply the ACL
                s3.setObjectAcl(bucketName, objectSummary.getKey(), CannedAccessControlList.PublicRead);
            }
            listObjectsRequest.setMarker(objectListing.getNextMarker());
        } while (objectListing.isTruncated());
    }

    private static void listFiles(File dir, List results, boolean includeSubDirectories)
    {
        File found[] = dir.listFiles();
        if(found != null)
        {
            File afile[] = found;
            int i = afile.length;
            for(int j = 0; j < i; j++)
            {
                File f = afile[j];
                if(f.isDirectory())
                {
                    if(includeSubDirectories)
                        listFiles(f, results, includeSubDirectories);
                } else
                {
                    results.add(f);
                }
            }

        }
    }

    private static void uploadLoop(AmazonS3 s3, String bucketName, String keyPrefix, File directory) {
        List files = new LinkedList();
        listFiles(directory, files, false);
        Iterator iterator = files.iterator();
        int startingPosition = directory.getAbsolutePath().length();
        if (!directory.getAbsolutePath().endsWith(File.separator))
            startingPosition++;
        do {
            if (!iterator.hasNext())
                break;
            File f = (File) iterator.next();
            if (f.isFile()) {
                String key = f.getAbsolutePath().substring(startingPosition).replaceAll("\\\\", "/");
                PutObjectRequest p = new PutObjectRequest(bucketName, keyPrefix + key, f);
                PutObjectResult putResult = s3.putObject(p.withCannedAcl(CannedAccessControlList.PublicRead));
            }
        } while (true);
    }

    private static void deleteLoop(AmazonS3 s3, String bucketName, String keyPrefix) {
        System.out.println("===========================================");
        System.out.println("Deleting list object\n");
        int count =0;
        do {
            if(count==0){
                s3.deleteObject(bucketName, keyPrefix+"index.m3u8");
            }
            String key =keyPrefix+count+".ts";
            boolean isexist=s3.doesObjectExist(bucketName, key);
            if(!isexist){
                break;
            }
            s3.deleteObject(bucketName, key);
            count++;
        } while (true);

    }

    private static void uploadPart(AmazonS3 s3Client, String existingBucketName, String keyName, String filePath, long starttime) {
        // for each part upload.
        List<PartETag> partETags = new ArrayList<PartETag>();
        // Step 1: Initialize.
        InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(existingBucketName, keyName);
        InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);
        File file = new File(filePath);
        long contentLength = file.length();
        long partSize = 5242880; // Set part size to 5 MB.
        try {
            // Step 2: Upload parts.
            long filePosition = 0;
            for (int i = 1; filePosition < contentLength; i++) {
                // Last part can be less than 5 MB. Adjust part size.
                partSize = Math.min(partSize, (contentLength - filePosition));
                // Create request to upload a part.
                UploadPartRequest uploadRequest = new UploadPartRequest().withBucketName(existingBucketName)
                        .withKey(keyName).withUploadId(initResponse.getUploadId()).withPartNumber(i)
                        .withFileOffset(filePosition).withFile(file).withPartSize(partSize);
                // Upload part and add response to our list.
                partETags.add(s3Client.uploadPart(uploadRequest).getPartETag());
                filePosition += partSize;
            }
            // Step 3: Complete.
            CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(existingBucketName, keyName,
                    initResponse.getUploadId(), partETags);
            s3Client.completeMultipartUpload(compRequest);
            s3Client.setObjectAcl(existingBucketName, keyName, CannedAccessControlList.PublicRead);
            Long endtime=System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" is upload over; 所耗费的时间为：" +(endtime-starttime)+"ms"+"; the num is : "+getCount());
        } catch (Exception e) {
            e.printStackTrace();
            s3Client.abortMultipartUpload(
                    new AbortMultipartUploadRequest(existingBucketName, keyName, initResponse.getUploadId()));
        }
    }

    private synchronized static int getCount(){
        return ++count;
    }

}
