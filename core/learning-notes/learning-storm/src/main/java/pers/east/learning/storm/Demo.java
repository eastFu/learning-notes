package pers.east.learning.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import pers.east.learning.storm.bolt.FirstBolt;
import pers.east.learning.storm.spout.MySpout;

import java.util.concurrent.TimeUnit;

public class Demo {

    private static final boolean IS_LOCAL = true;

    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException, InterruptedException {

        TopologyBuilder builder = new TopologyBuilder();
        //添加id为mySpout,线程数为1的Spout
        builder.setSpout("mySpout",new MySpout(),1);
        builder.setBolt("firstBolt",new FirstBolt(),1).shuffleGrouping("mySpout");
        builder.setBolt("secondBolt",new FirstBolt(),1).shuffleGrouping("firstBolt");

        //生成拓扑
        StormTopology topology = builder.createTopology();

        Config config = new Config();
        // 不输出发送的消息及系统消息
        config.setDebug(false);

        // 本地模式
        if(IS_LOCAL){
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("east",config,topology);
            TimeUnit.MINUTES.sleep(30);
            cluster.killTopology("");
            cluster.shutdown();
        }else{
            // worker个数
            config.setNumAckers(3);
            StormSubmitter.submitTopology("east",config,topology);
        }
    }

    public void init(){

    }

}
