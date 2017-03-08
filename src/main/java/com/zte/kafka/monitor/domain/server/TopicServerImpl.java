package com.zte.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;
import com.zte.kafka.monitor.KafkaMetricsDataProvider;
import com.zte.kafka.monitor.domain.constant.MbeanName;
import com.zte.kafka.monitor.domain.model.*;
import com.zte.kafka.monitor.domain.model.zookeeperdata.TopicPartitionData;
import com.zte.kafka.monitor.domain.utils.JsonUtils;
import com.zte.kafka.monitor.zookeeper.ZookeeperFileOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by ${10183966} on 12/15/16.
 */
@Service
public class TopicServerImpl implements TopicServer {
    @Autowired
    private KafkaMonitorRepo kafkaMonitorRepo;

    private static CombinedMetric getCombinedMetric(KafkaMetricsDataProvider kafkaMetricsDataProvider, String name, String mbaeanNme) {
        CombinedMetric combinedMetric = new CombinedMetric();
        combinedMetric.setName(name);
        combinedMetric.setMean(kafkaMetricsDataProvider.getMbeanLongValue(mbaeanNme));
        combinedMetric.setMin1(kafkaMetricsDataProvider.getMbeanLongValue(mbaeanNme));
        combinedMetric.setMin5(kafkaMetricsDataProvider.getMbeanLongValue(mbaeanNme));
        combinedMetric.setMin15(kafkaMetricsDataProvider.getMbeanLongValue(mbaeanNme));
        return combinedMetric;
    }

    @Override
    public JSONObject getTopicDetail(String zk, String topicName) {
        TopicVo topicVo = getTopicVo(zk, topicName);
        return JsonUtils.obj2JSON(topicVo);
    }

    private TopicVo getTopicVo(String zk, String topicName) {
        ZookeeperFileOperator zookeeperFileOperator = new ZookeeperFileOperator(zk);
        TopicVo topicVo = new TopicVo();
        List<PairKeyValue> summary = getSummary(zookeeperFileOperator, topicName);
        KafkaMonitorInfo kafkaMonitorInfo = kafkaMonitorRepo.getKafkaMonitorByZK(zk);
        String preJMX = zk.substring(0, zk.indexOf(':'));

        KafkaMetricsDataProvider kafkaMetricsDataProvider = new KafkaMetricsDataProvider(preJMX + ":" + kafkaMonitorInfo.getJmxPort());

        List<CombinedMetric> metrics = newArrayList();
        List<TopicPartitionBroker> partitionBroker = newArrayList();
        List<TopicPartitionInfo> partitionInfo = getTopicPartitionInfos(topicName, zookeeperFileOperator);

        List<String> brokers = zookeeperFileOperator.getBrokers();
        for (String brokerId : brokers) {
            TopicPartitionBroker topicPartitionBroker = new TopicPartitionBroker();
            topicPartitionBroker.setBroker(Integer.parseInt(brokerId.trim()));
            List<String> topicPartitions = zookeeperFileOperator.getTopicPartitions(topicName);
            topicPartitionBroker.setOfPartitions(topicPartitions.size());

            StringBuilder stringBuilder = new StringBuilder("(");
            for (String partition : topicPartitions) {
                stringBuilder.append(partition).append(",");
            }
            topicPartitionBroker.setPartitions(stringBuilder.substring(0, stringBuilder.length() - 1) + ")");
            topicPartitionBroker.setSkewed(false);

            partitionBroker.add(topicPartitionBroker);
        }


        CombinedMetric messageInPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Messages in /sec", MbeanName.MESSAGE_IN_PER_SEC);
        metrics.add(messageInPerSec);
        CombinedMetric bytesInPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Bytes in /sec", MbeanName.BYTES_IN_PER_SEC);
        metrics.add(bytesInPerSec);
        CombinedMetric bytsOutInPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Bytes out /sec", MbeanName.BYTES_OUT_PER_SEC);
        metrics.add(bytsOutInPerSec);
        CombinedMetric byteRejetedInPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Bytes rejected /sec", MbeanName.BYTES_REJECTED_PER_SEC);
        metrics.add(byteRejetedInPerSec);
        CombinedMetric failedFetchRequestInPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Failed fetch request /sec", MbeanName.FAILED_FETCH_REQUESTS_PER_SEC);
        metrics.add(failedFetchRequestInPerSec);
        CombinedMetric failedProduceRequestPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Failed produce request /sec", MbeanName.FAILED_PRODUCE_REQUESTS_PER_SEC);
        metrics.add(failedProduceRequestPerSec);


        topicVo.setMetrics(metrics);
        topicVo.setPartitionBroker(partitionBroker);
        topicVo.setPartitionInfo(partitionInfo);
        topicVo.setSummary(summary);
        zookeeperFileOperator.close();
        return topicVo;
    }

    private List<TopicPartitionInfo> getTopicPartitionInfos(String topicName, ZookeeperFileOperator zookeeperFileOperator) {
        List<String> partitions = zookeeperFileOperator.getTopicPartitions(topicName);

        List<TopicPartitionInfo> partitionInfo = newArrayList();
        for (String partitionId : partitions) {
            TopicPartitionInfo topicPartitionInfo = new TopicPartitionInfo();
            TopicPartitionData topicPartitionData = JsonUtils.deserialize(zookeeperFileOperator.getBrokerDateByBrokerId("/brokers/topics/" + topicName + "/partitions/" + partitionId + "/state"), TopicPartitionData.class);
            topicPartitionInfo.setPartition(Integer.parseInt(partitionId.trim()));
            topicPartitionInfo.setLeader(topicPartitionData.getLeader());
            topicPartitionInfo.setInSyncReplicas("(" + topicPartitionData.getLeader() + ")");
            topicPartitionInfo.setReplicas("(" + topicPartitionData.getLeader() + ")");
            topicPartitionInfo.setPreferredLeader(true);
            topicPartitionInfo.setUnderReplicated(false);
            topicPartitionInfo.setLatestOffset(0);

            partitionInfo.add(topicPartitionInfo);
        }
        return partitionInfo;
    }

    private List<PairKeyValue> getSummary(ZookeeperFileOperator zookeeperFileOperator, String topicName) {
        List<PairKeyValue> summary = newArrayList();
        summary.add(new PairKeyValue("Replication", 1));
        summary.add(new PairKeyValue("Preferred Replicas (%)", 100));
        summary.add(new PairKeyValue("Brokers Skewed (%)", 0));
        summary.add(new PairKeyValue("Brokers Spread (%)", 100));
        summary.add(new PairKeyValue("Under-replicated (%)", 100));
        summary.add(new PairKeyValue("Number of Partitions", zookeeperFileOperator.getTopicPartitions(topicName).size()));
        summary.add(new PairKeyValue("Sum of partition offsets", 0));
        summary.add(new PairKeyValue("Total number of Brokers", zookeeperFileOperator.getBrokers().size()));
        summary.add(new PairKeyValue("Number of Brokers for Topic", zookeeperFileOperator.getBrokers().size()));
        return summary;
    }
}
