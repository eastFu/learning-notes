package pers.east.learning.influxdb;

import com.alibaba.fastjson.JSON;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InfluxDbUtils {

    static InfluxDbConnection influxDbConnection;
    static{
        influxDbConnection = new InfluxDbConnection("monitor","123456","http://10.12.53.58:8086",
                "monitor4","autogen");
    }



    public static void query(){
        QueryResult result = influxDbConnection.query("SELECT * FROM log order by time desc limit 10");
        //同时查询多条SQL语句的返回值，这里只执行一条查询
        //result.getResults();
        QueryResult.Result rs = result.getResults().get(0);
        if(rs.getSeries()!=null){
//            List<List<Object>> valueList = rs.getSeries().pers.east.learning.java8.stream().map(QueryResult.Series::getValues)
//                    .collect(Collectors.toList()).get(0);
//            if (valueList != null && valueList.size() > 0) {
//                for(List<Object> value: valueList){
//                    System.out.println("value:"+JSON.toJSONString(value));
//                }
//            }
            for(QueryResult.Series serie:rs.getSeries()){
                List<List<Object>>  values = serie.getValues();
                List<String> columns = serie.getColumns();

                for (List<Object> list : values) {
                    Map<String,Object> rsMap= new HashMap<>();
                    for(int i=0; i< list.size(); i++){
                        rsMap.put(columns.get(i),list.get(i));
                    }
                    System.out.println(JSON.toJSONString(rsMap));
                }


            }
        }
    }

    //单条插入
    public static void insert(){
        Map<String,String> tags = new HashMap<>();
        tags.put("main_name","问道");
        tags.put("sub_name","2012");
        tags.put("app_id","9527");
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("cpu",20);
        fields.put("disk",66);
        fields.put("ntp",0);
        fields.put("memory",40);
        fields.put("raid",false);
        influxDbConnection.insert("log",tags,fields,0, TimeUnit.MILLISECONDS);
    }

    //批量插入
    public static void batchInsert(){
        Map<String,String> tags = new HashMap<>();
        tags.put("main_name","test");
        tags.put("sub_name","test");
        tags.put("app_id","9528");
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("cpu",20);
        fields.put("disk",66);
        fields.put("ntp",0);
        fields.put("memory",40);
        fields.put("raid",false);

        Point point1 = influxDbConnection.pointBuilder("log",System.currentTimeMillis(),tags,fields);

        Point point2 = influxDbConnection.pointBuilder("log",System.currentTimeMillis(),tags,fields);

        BatchPoints batchPoints1 = BatchPoints.database("monitor4").tag("main_name","test")
                .tag("main_name","test")
                .tag("sub_name","test")
                .tag("app_id","9528")
                .retentionPolicy("").consistency(InfluxDB.ConsistencyLevel.ALL).build();

        BatchPoints batchPoints2 = BatchPoints.database("monitor4").tag("main_name","test")
                .tag("main_name","test")
                .tag("sub_name","test")
                .tag("app_id","9528")
                .retentionPolicy("").consistency(InfluxDB.ConsistencyLevel.ALL).build();

        batchPoints1.point(point1);
        batchPoints2.point(point2);
        // 将两条数据批量插入到数据库中
        influxDbConnection.batchInsert(batchPoints1);
        influxDbConnection.batchInsert(batchPoints2);
    }

    //通过BatchPoints组装数据，序列化后，一次性插入数据库。
    public static void batchInsert2(){
        Map<String,String> tags = new HashMap<>();
        tags.put("main_name","test");
        tags.put("sub_name","test");
        tags.put("app_id","9528");
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("cpu",20);
        fields.put("disk",66);
        fields.put("ntp",0);
        fields.put("memory",40);
        fields.put("raid",false);

        Point point1 = influxDbConnection.pointBuilder("log",System.currentTimeMillis(),tags,fields);

        Point point2 = influxDbConnection.pointBuilder("log",System.currentTimeMillis(),tags,fields);

        BatchPoints batchPoints1 = BatchPoints.database("monitor4").tag("main_name","test")
                .tag("main_name","test")
                .tag("sub_name","test")
                .tag("app_id","9528")
                .retentionPolicy("").consistency(InfluxDB.ConsistencyLevel.ALL).build();

        BatchPoints batchPoints2 = BatchPoints.database("monitor4").tag("main_name","test")
                .tag("main_name","test")
                .tag("sub_name","test")
                .tag("app_id","9528")
                .retentionPolicy("").consistency(InfluxDB.ConsistencyLevel.ALL).build();

        batchPoints1.point(point1);
        batchPoints2.point(point2);

        List<String> records = new ArrayList<String>();
        records.add(batchPoints1.lineProtocol());
        records.add(batchPoints2.lineProtocol());

        influxDbConnection.batchInsert("monitor4","", InfluxDB.ConsistencyLevel.ALL,records);


    }

    public static void main(String[] args) throws Exception{
        /*for(int j=0;j<=5;j++){
            new Thread(){
                @Override
                public void run() {
                    for(int i=0;i<100000;i++){
                        insert();
                        System.out.println(i+"---insert success");
                    }
                }
            }.start();
        }
        Thread.currentThread().wait(500000000);*/


//        batchInsert();

//        batchInsert2();

        query();
    }


}
