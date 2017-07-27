package com.aires.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;
import com.aires.kafka.monitor.KafkaMetricsDataProvider;
import com.aires.kafka.monitor.domain.constant.MbeanName;
import com.aires.kafka.monitor.domain.exception.KafkaMonitorException;
import com.aires.kafka.monitor.domain.model.*;
import com.aires.kafka.monitor.domain.model.zookeeperdata.BrokerData;
import com.aires.kafka.monitor.domain.utils.JsonUtils;
import com.aires.kafka.monitor.domain.utils.StringOperator;
import com.aires.kafka.monitor.zookeeper.ZookeeperFileOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by $aires on 12/6/16.
 */
@Service
public class KafkaMonitorServerImpl implements KafkaMonitorServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMonitorServer.class);

    @Autowired
    private KafkaMonitorRepo kafkaMonitorRepo;

    private KafkaMonitorServerImpl() {
    }

    private static KafkaMonitorVo getKafkaMonitorVoByMonitorInfo(KafkaMonitorInfo kafkaMonitorInfo) {
        KafkaMonitorVo kafkaMonitorVo = new KafkaMonitorVo();
        Overview overview = new Overview();
        Brokers brokers = new Brokers();
        overview.setName(kafkaMonitorInfo.getName());
        overview.setZookeeper(kafkaMonitorInfo.getZookeeper());
        overview.setStatus(true);
        overview.setVersion(kafkaMonitorInfo.getVersion());

        ZookeeperFileOperator zookeeperFileOperator = new ZookeeperFileOperator(kafkaMonitorInfo.getZookeeper());
        overview.setTopics(zookeeperFileOperator.getTopics().size());
        overview.setBrokers(zookeeperFileOperator.getBrokers().size());
        overview.setConsumers(zookeeperFileOperator.getGroups().size());

        List<String> brokerIds = zookeeperFileOperator.getBrokers();
        List<Cluster> clusters = newArrayList();
        List<Broker> brokerList = newArrayList();
        List<CombinedMetric> combinedMetrics = newArrayList();

        for (String brokerId : brokerIds) {
            String fullBrokerId = "/brokers/ids/" + brokerId;
            String brokerData = zookeeperFileOperator.getBrokerDateByBrokerId(fullBrokerId);
            BrokerData brokerDataBo = JsonUtils.deserialize(brokerData, BrokerData.class);
            String endPoint = brokerDataBo.getEndpoints().get(0);
            String host = endPoint.substring(endPoint.lastIndexOf('/') + 1, endPoint.length());
            Cluster cluster = new Cluster("broker" + brokerId, host);
            cluster.setStatus(true);
            clusters.add(cluster);
            Broker broker = new Broker(brokerId, brokerDataBo.getHost(), String.valueOf(brokerDataBo.getPort()));
            if (-1 != brokerDataBo.getJmxPort()) {
                broker.setJmxport(String.valueOf(brokerDataBo.getJmxPort()));
                String jmxURL = broker.getHost() + ":" + broker.getJmxport();
                KafkaMetricsDataProvider kafkaMetricsDataProvider = new KafkaMetricsDataProvider(jmxURL);
                broker.setBytesIn(kafkaMetricsDataProvider.getMbeanLongValue(MbeanName.BYTES_IN_PER_SEC));
                broker.setBytesOut(kafkaMetricsDataProvider.getMbeanLongValue(MbeanName.BYTES_OUT_PER_SEC));
                brokerList.add(broker);

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
            }


        }
        brokers.setCombinedMetrics(combinedMetrics);
        overview.setClusters(clusters);
        brokers.setBrokerList(brokerList);

        MsgCount msgCount = new MsgCount();
        msgCount.setTimes(newArrayList(StringOperator.times(System.currentTimeMillis())));
        msgCount.setConsumer(newArrayList(0, 0, 10, 0, 150, 11, 50, 10, 120, 80));
        msgCount.setProducer(newArrayList(10, 0, 77, 11, 50, 10, 120, 77, 0, 10));
        overview.setMsgCount(msgCount);


        List<Topic> topics = getTopics(zookeeperFileOperator);
        List<Consumer> consumers = getConsumers(zookeeperFileOperator);
        kafkaMonitorVo.setBrokers(brokers);
        kafkaMonitorVo.setOverview(overview);
        kafkaMonitorVo.setTopics(topics);
        kafkaMonitorVo.setConsumers(consumers);
        return kafkaMonitorVo;
    }

    private static List<Topic> getTopics(ZookeeperFileOperator zookeeperFileOperator) {
        List<Topic> topics = newArrayList();
        List<String> topicNames = zookeeperFileOperator.getTopics();

        for (String topicName : topicNames) {
            List<String> partitions = zookeeperFileOperator.getChild("/brokers/topics/" + topicName + "/partitions");
            Topic topic = new Topic(topicName);
            topic.setBrokers(zookeeperFileOperator.getBrokers().size());
            topic.setPartition(partitions.size());
            topic.setBrokersSpread(100);
            topic.setBrokersSkew(0);
            topic.setReplicas(1);
            topic.setUnderReplicated(0);
            topic.setProducerMessage(0.00);

            topics.add(topic);
        }
        return topics;
    }

    private static List<Consumer> getConsumers(ZookeeperFileOperator zookeeperFileOperator) {
        List<Consumer> consumers = newArrayList();
        Set<String> consumerNames = zookeeperFileOperator.getGroups();
        for (String consumerName : consumerNames) {
            Consumer consumer = new Consumer(consumerName);
            consumer.setType("ZK");
            consumers.add(consumer);
        }
        return consumers;
    }

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
    public Result putKafkaMonitorInfo(KafkaMonitorInfo kafkaMonitorInfo) {
        ResultBuilder resultBuilder = ResultBuilder.newResult();
        String zookeeperIp = kafkaMonitorInfo.getZookeeper().substring(0, kafkaMonitorInfo.getZookeeper().indexOf(':'));
        if (StringOperator.isAvailIp(zookeeperIp.trim())) {
            try {
                kafkaMonitorRepo.insertKafkaMonitorInfo(kafkaMonitorInfo);
                resultBuilder.status("OK");
                resultBuilder.message("Add Cluster Success !");
            } catch (KafkaMonitorException e) {
                resultBuilder.status("FAIL");
                resultBuilder.message("Add Cluster Failure !" + e.getMessage());
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            resultBuilder.status("FAIL");
            resultBuilder.message("Please make sure the ip address what you input is available !");
        }

        return resultBuilder.build();
    }

    @Override
    public Result getKafkaMonitors() {
        ResultBuilder resultBuilder = ResultBuilder.newResult();
        try {
            KafkaMonitorInfos kafkaMonitorInfos = new KafkaMonitorInfos();
            kafkaMonitorInfos.setKafkaMonitorInfoList(kafkaMonitorRepo.getKafkaMonitorInfos());
            resultBuilder.status("OK");
            resultBuilder.message("Query Kafkamonitorinfo success !");
            resultBuilder.data(JsonUtils.obj2JSON(kafkaMonitorInfos));

        } catch (KafkaMonitorException e) {
            resultBuilder.status("FAIL");
            resultBuilder.message("Query Kafkamonitorinfo failure !" + e.getMessage());
            LOGGER.error(e.getMessage(), e);
        }
        return resultBuilder.build();
    }

    @Override
    public JSONObject getDetailsMonitorNode(String zk, String name) {
        return JsonUtils.obj2JSON(getKafkaMonitorVoByMonitorInfo(kafkaMonitorRepo.getKafkaMonitorByKafkaMonitorName(zk, name).get(0)));
    }
}
