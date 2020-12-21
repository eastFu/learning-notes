package pers.east.learning.storm.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MySpout extends BaseRichSpout {

    //数据收集器，用于发射
    private SpoutOutputCollector collector;

    //数据源
    private List<String> dataList = Arrays.asList("study", "play", "shopping", "travel");

    private Random random = new Random();

    //初始化
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
    }

    //元组获取
    @Override
    public void nextTuple() {
        //随机生成数据
        String str = dataList.get(random.nextInt(dataList.size()));
        this.collector.emit(new Values(str));
        System.out.println("StrSpout emit: " + str);
        Utils.sleep(1000);
    }

    //声明输出字段
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("step1"));
    }

}
