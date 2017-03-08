package com.zte.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;
import com.zte.kafka.monitor.KafkaMetricsDataProvider;
import com.zte.kafka.monitor.domain.constant.MbeanName;
import com.zte.kafka.monitor.domain.model.*;
import com.zte.kafka.monitor.domain.model.zookeeperdata.BrokerData;
import com.zte.kafka.monitor.domain.utils.JsonUtils;
import com.zte.kafka.monitor.domain.utils.StringOperator;
import com.zte.kafka.monitor.zookeeper.ZookeeperFileOperator;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by $10183966 on 12/14/16.
 */
@Service
public class BrokerServerImpl implements BrokerServer {
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
    public JSONObject getDetailofBroker(String zk, String brokerId) {
        return JsonUtils.obj2JSON(getBrokerVo(zk, brokerId));
    }

    private BrokerVo getBrokerVo(String zk, String brokerId) {
        BrokerVo brokerVo = new BrokerVo();

        BrokerSummary brokerSummary = new BrokerSummary();
        ZookeeperFileOperator zookeeperFileOperator = new ZookeeperFileOperator(zk);
        List<String> topics = zookeeperFileOperator.getTopics();
        brokerSummary.setTopics(topics.size());

        List<BrokerTopic> brokerTopics = newArrayList();
        List<CombinedMetric> combinedMetrics = newArrayList();
        int partitions = 0;
        for (String topicName : topics) {
            BrokerTopic brokerTopic = new BrokerTopic();
            brokerTopic.setTopic(topicName);
            List<String> partitionsOfTopic = zookeeperFileOperator.getTopicPartitions(topicName);
            StringBuilder stringBuilder = new StringBuilder("(");
            for (String partition : partitionsOfTopic) {
                stringBuilder.append(partition).append(",");
            }
            brokerTopic.setPartitions(stringBuilder.substring(0, stringBuilder.length() - 1) + ")");
            brokerTopic.setPartitionsOnBroker(partitionsOfTopic.size());
            brokerTopic.setReplication(1);
            brokerTopic.setSkewed(false);
            brokerTopic.setReplication(1);
            brokerTopic.setTotalPartitions(partitionsOfTopic.size());
            brokerTopics.add(brokerTopic);
            partitions += partitionsOfTopic.size();
        }
        brokerSummary.setPartitions(partitions);

        BrokerData brokerData = JsonUtils.deserialize(zookeeperFileOperator.getBrokerDateByBrokerId("/brokers/ids/" + brokerId), BrokerData.class);
        String jmx_url = brokerData.getHost() + ":" + brokerData.getJmxPort();
        KafkaMetricsDataProvider kafkaMetricsDataProvider = new KafkaMetricsDataProvider(jmx_url);
        brokerSummary.setMessages(100.0);
        brokerSummary.setIncoming(100.0);
        brokerSummary.setOutgoing(100.0);

        MsgCount msgCount = new MsgCount();
        msgCount.setTimes(newArrayList(StringOperator.times(System.currentTimeMillis())));
        msgCount.setConsumer(newArrayList(0, 100, 10, 0, 200, 150, 50, 10, 120, 80));
        msgCount.setProducer(newArrayList(10, 0, 200, 150, 50, 10, 120, 80, 0, 10));
        brokerSummary.setMsgCount(msgCount);


        CombinedMetric messageInPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Messages in /sec", MbeanName.MESSAGE_IN_PER_SEC);
        combinedMetrics.add(messageInPerSec);
        CombinedMetric bytesInPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Bytes in /sec", MbeanName.BYTES_IN_PER_SEC);
        combinedMetrics.add(bytesInPerSec);
        CombinedMetric bytsOutInPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Bytes out /sec", MbeanName.BYTES_OUT_PER_SEC);
        combinedMetrics.add(bytsOutInPerSec);
        CombinedMetric byteRejetedInPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Bytes rejected /sec", MbeanName.BYTES_REJECTED_PER_SEC);
        combinedMetrics.add(byteRejetedInPerSec);
        CombinedMetric failedFetchRequestInPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Failed fetch request /sec", MbeanName.FAILED_FETCH_REQUESTS_PER_SEC);
        combinedMetrics.add(failedFetchRequestInPerSec);
        CombinedMetric failedProduceRequestPerSec = getCombinedMetric(kafkaMetricsDataProvider, "Failed produce request /sec", MbeanName.FAILED_PRODUCE_REQUESTS_PER_SEC);
        combinedMetrics.add(failedProduceRequestPerSec);
        brokerVo.setMetrics(combinedMetrics);
        brokerVo.setSummary(brokerSummary);
        brokerVo.setTopics(brokerTopics);

        zookeeperFileOperator.close();

        kafkaMetricsDataProvider.close();
        return brokerVo;
    }
}
