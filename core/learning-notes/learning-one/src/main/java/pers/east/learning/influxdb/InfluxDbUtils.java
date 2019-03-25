package pers.east.learning.influxdb;

import com.alibaba.fastjson.JSON;
import org.influxdb.dto.QueryResult;

import java.util.List;
import java.util.stream.Collectors;

public class InfluxDbUtils {


    public static void main(String[] args) {
        InfluxDbConnection influxDbConnection = new InfluxDbConnection("monitor","123456","http://10.12.53.58:8086",
                "monitor4","hour");

        QueryResult result = influxDbConnection.query("SELECT * FROM log order by time desc limit 1000");

        //同时查询多条SQL语句的返回值，这里只执行一条查询
        //result.getResults();
        QueryResult.Result rs = result.getResults().get(0);
        if(rs.getSeries()!=null){
            List<List<Object>> valueList = rs.getSeries().stream().map(QueryResult.Series::getValues)
                    .collect(Collectors.toList()).get(0);
            if (valueList != null && valueList.size() > 0) {
                for(List<Object> value: valueList){
                    System.out.println("value:"+JSON.toJSONString(value));
                }
            }
        }

    }


}
