package pers.east.learning.storm.bolt;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import pers.east.learning.storm.model.MyValue;

public class FirstBolt extends BaseBasicBolt {

    private int i =0;

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String data = tuple.getValue(0).toString();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>FirstBolt >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:"+data);
        MyValue v = new MyValue();
        v.setStep1(data);
        v.setStep2(i++);
        basicOutputCollector.emit(new Values(v));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("step2"));
    }
}
