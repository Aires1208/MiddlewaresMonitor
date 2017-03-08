package com.zte.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;
import com.zte.kafka.monitor.domain.model.KafkaCluster;
import com.zte.kafka.monitor.domain.model.KafkaClusters;
import com.zte.kafka.monitor.domain.model.KafkaMonitorInfo;
import com.zte.kafka.monitor.domain.utils.JsonUtils;
import com.zte.kafka.monitor.zookeeper.ZookeeperFileOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by ${10183966} on 12/6/16.
 */

@Service
public class KafkaClustersServerImpl implements KafkaClustersServer {
    @Autowired
    private KafkaMonitorRepo kafkaMonitorRepo;

    @Override
    public JSONObject getKafkaClusters() {
        List<KafkaCluster> kafkaClusters = newArrayList();
        List<KafkaMonitorInfo> kafkaMonitorInfos = kafkaMonitorRepo.getKafkaMonitorInfos();
        for (KafkaMonitorInfo kafkaMonitorInfo : kafkaMonitorInfos) {
            String zookeeperIp = kafkaMonitorInfo.getZookeeper();
            ZookeeperFileOperator zookeeperFileOperator = new ZookeeperFileOperator(zookeeperIp);
            kafkaClusters.add(new KafkaCluster(kafkaMonitorInfo.getName(), zookeeperIp, kafkaMonitorInfo.getVersion(), kafkaMonitorInfo.isJMXOn(), kafkaMonitorInfo.getJmxPort(), zookeeperFileOperator.getTopics().size(), zookeeperFileOperator.getBrokers().size(), kafkaMonitorInfo.getDescription()));
            zookeeperFileOperator.close();
        }
        KafkaClusters kafkaClustersBean = new KafkaClusters();
        kafkaClustersBean.setKafkaClusters(kafkaClusters);

        return JsonUtils.obj2JSON(kafkaClustersBean);
    }
}
