package pers.east.learning.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseWindowedBolt;
import pers.east.learning.storm.bolt.FirstBolt;
import pers.east.learning.storm.bolt.SecondBolt;
import pers.east.learning.storm.bolt.ThirdWindowBolt;
import pers.east.learning.storm.spout.MySpout;

import java.util.concurrent.TimeUnit;

public class Demo {

    private static final boolean IS_LOCAL = true;

    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException, InterruptedException {

        /**
         *
         * 一、Storm 的几个基本概念： worker,executor,task,topology,parallelism,tuple,bolt,spout
         *
         * 二、group的类型：
         * 1.Shuffle Grouping: 随机分组， 随机派发stream里面的tuple， 保证bolt中的每个任务接收到的tuple数目相同.(它能实现较好的负载均衡)
         * 2.Fields Grouping：按字段分组， 比如按userid来分组， 具有同样userid的tuple会被分到同一任务， 而不同的userid则会被分配到不同的任务
         * 3.All Grouping： 广播发送,对于每一个tuple,Bolts中的所有任务都会收到.
         * 4.Global Grouping: 全局分组，这个tuple被分配到storm中的一个bolt的其中一个task.再具体一点就是分配给id值最低的那个task.
         * 5.Non Grouping: 不分组，意思是说stream不关心到底谁会收到它的tuple.目前他和Shuffle grouping是一样的效果,有点不同的是storm会把这个bolt放到这个bolt的订阅者同一个线程去执行.
         * 6.Direct Grouping: 直接分组,这是一种比较特别的分组方法，用这种分组意味着消息的发送者举鼎由消息接收者的哪个task处理这个消息.
         *      只有被声明为Direct Stream的消息流可以声明这种分组方法.而且这种消息tuple必须使用emitDirect方法来发射.消息处理者可以通过TopologyContext
         *      来或者处理它的消息的taskid (OutputCollector.emit方法也会返回taskid)
         *
         */

        TopologyBuilder builder = new TopologyBuilder();

        //添加id为mySpout,线程数为1的Spout
        builder.setSpout("mySpout",new MySpout(),1);

        builder.setBolt("firstBolt",new FirstBolt(),1).shuffleGrouping("mySpout");
        builder.setBolt("secondBolt",new SecondBolt(),1).shuffleGrouping("firstBolt");

        // 滑动窗口
        builder.setBolt("thirdWindowBolt",
                new ThirdWindowBolt().withWindow(new BaseWindowedBolt.Count(30),new BaseWindowedBolt.Count(10)),1)
                .shuffleGrouping("firstBolt");

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
