package pers.east.learning.storm.bolt;

import com.alibaba.fastjson.JSON;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import pers.east.learning.storm.model.MyValue;

public class SecondBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        MyValue data = (MyValue)tuple.getValue(0);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SecondBolt >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:"+ JSON.toJSONString(data));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
