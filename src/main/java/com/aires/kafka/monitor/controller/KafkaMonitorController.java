package com.aires.kafka.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.aires.kafka.monitor.domain.model.KafkaMonitorInfo;
import com.aires.kafka.monitor.domain.model.Result;
import com.aires.kafka.monitor.domain.server.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ${10183966} on 12/6/16.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class KafkaMonitorController {
    @Autowired
    private KafkaMonitorServer kafkaMonitorServer;
    @Autowired
    private KafkaClustersServer kafkaClustersServer;
    @Autowired
    private ConsumerTopicInfoServer consumerTopicInfoServer;
    @Autowired
    private BrokerServer brokerServer;
    @Autowired
    private TopicServer topicServer;

    @RequestMapping(value = "/kafkamonitor/addCluster", method = {RequestMethod.POST})
    public Result addKafkaMonitorInfo(@RequestBody KafkaMonitorInfo kafkaMonitorInfo) {
        return kafkaMonitorServer.putKafkaMonitorInfo(kafkaMonitorInfo);
    }

    @RequestMapping(value = "/kafkamonitor/queryKafkaMonitor", method = {RequestMethod.GET})
    public Result queryKafkaMonitor() {
        return kafkaMonitorServer.getKafkaMonitors();
    }

    @RequestMapping(value = "/kafkamonitor/kafkaClusters", method = {RequestMethod.GET})
    public JSONObject getKafkaClusters() {
        return kafkaClustersServer.getKafkaClusters();
    }

    @RequestMapping(value = "/kafkamonitor/monitorNodeDetail", method = {RequestMethod.GET})
    public JSONObject getDetailsMonitorNode(@RequestParam("zookeeper") String zookeeper,
                                            @RequestParam("name") String name) {
        return kafkaMonitorServer.getDetailsMonitorNode(zookeeper, name);
    }

    @RequestMapping(value = "/kafkamonitor/consumerTopicDetail", method = {RequestMethod.GET})
    public JSONObject getConsumerTopicDetail(@RequestParam("zookeeper") String zookeeper,
                                             @RequestParam("name") String groupname) {
        return consumerTopicInfoServer.getConsumerTopicDetail(zookeeper, groupname);
    }

    @RequestMapping(value = "/kafkamonitor/topicDetail", method = {RequestMethod.GET})
    public JSONObject getTopicDetail(@RequestParam("zookeeper") String zookeeper,
                                     @RequestParam("groupname") String groupname,
                                     @RequestParam("topicname") String topicname) {
        return consumerTopicInfoServer.getTopicDetail(zookeeper, groupname, topicname);
    }

    @RequestMapping(value = "/kafkamonitor/brokerDetail", method = {RequestMethod.GET})
    public JSONObject getBrokerDetail(@RequestParam("zookeeper") String zookeeper,
                                      @RequestParam("brokerid") String brokerid
    ) {
        return brokerServer.getDetailofBroker(zookeeper, brokerid);
    }

    @RequestMapping(value = "/kafkamonitor/kafkatopics", method = {RequestMethod.GET})
    public JSONObject getTopicsInformation(@RequestParam("zookeeper") String zookeeper,
//                                           @RequestParam("groupname") String groupname,
                                           @RequestParam("topicname") String topicname) {
        return topicServer.getTopicDetail(zookeeper, topicname);
    }
}
