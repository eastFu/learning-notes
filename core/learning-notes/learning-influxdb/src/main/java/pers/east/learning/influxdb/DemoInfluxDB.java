package pers.east.learning.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
// 			佛祖保佑       永无BUG

public class DemoInfluxDB {

    private static InfluxDBConnection influxDBConnection;

    static {
        influxDBConnection = new InfluxDBConnection("admin", "admin", "10.12.23.5", "svr_log", "hour");
    }

    private static void testQuery(String sql){

        QueryResult results = influxDBConnection.query(sql);
        //results.getResults()是同时查询多条SQL语句的返回值，此处我们只有一条SQL，所以只取第一个结果集即可。
        QueryResult.Result oneResult = results.getResults().get(0);
        if (oneResult.getSeries() != null) {
            List<List<Object>> valueList = oneResult.getSeries().stream().map(QueryResult.Series::getValues)
                    .collect(Collectors.toList()).get(0);
            if (valueList != null && valueList.size() > 0) {
                for (List<Object> value : valueList) {
                    Map<String, String> map = new HashMap<String, String>();
                    // 数据库中字段1取值
                    String field1 = value.get(0) == null ? null : value.get(0).toString();
                    // 数据库中字段2取值
                    String field2 = value.get(1) == null ? null : value.get(1).toString();
                }
            }
        }
    }

    private static void testInsert(){
        Map<String, String> tags = new HashMap<String, String>();
        tags.put("tag1", "标签值");
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("field1", "String类型");
        // 数值型，InfluxDB的字段类型，由第一天插入的值得类型决定
        fields.put("field2", 3.141592657);
        // 时间使用毫秒为单位
        influxDBConnection.insert("表名", tags, fields, System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    private static void testBatchInsert(){
        Map<String, String> tags = new HashMap<String, String>();
        tags.put("tag1", "标签值");
        Map<String, Object> fields1 = new HashMap<String, Object>();
        fields1.put("field1", "abc");
        // 数值型，InfluxDB的字段类型，由第一天插入的值得类型决定
        fields1.put("field2", 123456);
        Map<String, Object> fields2 = new HashMap<String, Object>();
        fields2.put("field1", "String类型");
        fields2.put("field2", 3.141592657);
        // 一条记录值
        Point point1 = influxDBConnection.pointBuilder("表名", System.currentTimeMillis(), tags, fields1);
        Point point2 = influxDBConnection.pointBuilder("表名", System.currentTimeMillis(), tags, fields2);
        // 将两条记录添加到batchPoints中
        BatchPoints batchPoints1 = BatchPoints.database("db-test").tag("tag1", "标签值1").retentionPolicy("hour")
                .consistency(InfluxDB.ConsistencyLevel.ALL).build();
        BatchPoints batchPoints2 = BatchPoints.database("db-test").tag("tag2", "标签值2").retentionPolicy("hour")
                .consistency(InfluxDB.ConsistencyLevel.ALL).build();
        batchPoints1.point(point1);
        batchPoints2.point(point2);
        // 将两条数据批量插入到数据库中
        influxDBConnection.batchInsert(batchPoints1);
        influxDBConnection.batchInsert(batchPoints2);
    }

    private static void testBatchInsert2(){
        Map<String, String> tags1 = new HashMap<String, String>();
        tags1.put("tag1", "标签值");
        Map<String, String> tags2 = new HashMap<String, String>();
        tags2.put("tag2", "标签值");
        Map<String, Object> fields1 = new HashMap<String, Object>();
        fields1.put("field1", "abc");
        // 数值型，InfluxDB的字段类型，由第一天插入的值得类型决定
        fields1.put("field2", 123456);

        Map<String, Object> fields2 = new HashMap<String, Object>();
        fields2.put("field1", "String类型");
        fields2.put("field2", 3.141592657);

        // 一条记录值
        Point point1 = influxDBConnection.pointBuilder("表名", System.currentTimeMillis(), tags1, fields1);
        Point point2 = influxDBConnection.pointBuilder("表名", System.currentTimeMillis(), tags2, fields2);
        BatchPoints batchPoints1 = BatchPoints.database("db-test").tag("tag1", "标签值1")
                .retentionPolicy("hour").consistency(InfluxDB.ConsistencyLevel.ALL).build();
        // 将两条记录添加到batchPoints中
        batchPoints1.point(point1);
        BatchPoints batchPoints2 = BatchPoints.database("db-test").tag("tag2", "标签值2")
                .retentionPolicy("hour").consistency(InfluxDB.ConsistencyLevel.ALL).build();
        // 将两条记录添加到batchPoints中
        batchPoints2.point(point2);
        // 将不同的batchPoints序列化后，一次性写入数据库，提高写入速度
        List<String> records = new ArrayList<String>();
        records.add(batchPoints1.lineProtocol());
        records.add(batchPoints2.lineProtocol());
        // 将两条数据批量插入到数据库中
        influxDBConnection.batchInsert("db-test", "hour", InfluxDB.ConsistencyLevel.ALL, records);
    }

    private static void testDelete(String sql){

    }


    public static void main(String[] args) {

        testQuery("SELECT * FROM measurement where name = '大脑补丁'  order by time desc limit 1000");

        testInsert();

    }
}
