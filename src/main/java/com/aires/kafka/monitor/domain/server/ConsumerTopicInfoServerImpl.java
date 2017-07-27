package com.aires.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;
import com.aires.kafka.monitor.domain.model.*;
import com.aires.kafka.monitor.domain.utils.JsonUtils;
import com.aires.kafka.monitor.zookeeper.ZookeeperFileOperator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by ${aires} on 12/13/16.
 */
@Service
public class ConsumerTopicInfoServerImpl implements ConsumerTopicInfoServer {
    @Override
    public JSONObject getConsumerTopicDetail(String zk, String name) {
        ConsumedTopicInfoVo consumedTopicInfoVo = new ConsumedTopicInfoVo(getConsumerTopicInfos(zk, name));
        return JsonUtils.obj2JSON(consumedTopicInfoVo);
    }

    @Override
    public JSONObject getTopicDetail(String zk, String consumerNme, String topicName) {
        ZookeeperFileOperator zookeeperFileOperator = new ZookeeperFileOperator(zk);

        Map<String, List<String>> lagsAndOwnerMap = zookeeperFileOperator.getTopicLagsAndOwnerMap(consumerNme, topicName);
        Map<String, List<String>> topicOffset = zookeeperFileOperator.getTopicOffset(consumerNme, topicName);
        List<String> topicPartitions = zookeeperFileOperator.getTopicPartitions(topicName);
        ConsumerTopicDetail consumerTopicDetail = new ConsumerTopicDetail();
        consumerTopicDetail.setPartition(!topicPartitions.isEmpty() ? topicPartitions.get(0) : "0");
        consumerTopicDetail.setLag("0");
        consumerTopicDetail.setInstanceOwner(!lagsAndOwnerMap.isEmpty() ? lagsAndOwnerMap.get("0").get(0) : "unknown");
        consumerTopicDetail.setConsumerOffset(Long.parseLong(topicOffset.get("0").get(0).trim()));
        consumerTopicDetail.setLogSize(Long.parseLong(topicOffset.get("0").get(0).trim()));
        ConsumerTopicDetailVo consumerTopicDetailVo = new ConsumerTopicDetailVo();
        consumerTopicDetailVo.setName(topicName);
        consumerTopicDetailVo.setOfPartitions(zookeeperFileOperator.getTopicPartitions(topicName).size());
        consumerTopicDetailVo.setDetail(newArrayList(consumerTopicDetail));
        ConsumerTopicDetailSummary consumerTopicDetailSummary = new ConsumerTopicDetailSummary(consumerTopicDetailVo);
        zookeeperFileOperator.close();
        return JsonUtils.obj2JSON(consumerTopicDetailSummary);
    }

    private List<ConsumedTopicInfo> getConsumerTopicInfos(String zk, String name) {
        ZookeeperFileOperator zookeeperFileOperator = new ZookeeperFileOperator(zk);
        Set<String> topicNames = zookeeperFileOperator.getGroupConsumTopicsByGroupName(name);
        List<ConsumedTopicInfo> consumedTopicInfos = newArrayList();
        for (String topicName : topicNames) {
            ConsumedTopicInfo consumedTopicInfo = new ConsumedTopicInfo(topicName);
            consumedTopicInfo.setTotalLag("not available");
            consumedTopicInfo.setPartitionsCovered(100);
            consumedTopicInfos.add(consumedTopicInfo);
        }
        zookeeperFileOperator.close();
        return !consumedTopicInfos.isEmpty() ? consumedTopicInfos : newArrayList();

    }
}
