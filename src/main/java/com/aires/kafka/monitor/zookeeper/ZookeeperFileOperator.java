package com.aires.kafka.monitor.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by ${aires} on 12/6/16.
 */
public class ZookeeperFileOperator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperFileOperator.class);
    private static final String CONSUMERS = "/consumers";
    private String zookeeperIP;

    private ZooKeeper zooKeeper;


    public ZookeeperFileOperator(String zookeeperIP) {
        this.zookeeperIP = zookeeperIP;
        this.zooKeeper = getZookeeper();
    }

    private ZooKeeper getZookeeper() {
        ZooKeeper zooKeeperInstance = null;
        try {
            zooKeeperInstance = new ZooKeeper(zookeeperIP, 500000, new Watcher() {
                // 监控所有被触发的事件
                @Override
                public void process(WatchedEvent event) {
                    // dosomething
                    LOGGER.info(event.toString());
                }
            });
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return zooKeeperInstance;
    }

    public List<String> getChild(String path) {
        List<String> chileFileNames = newArrayList();
        try {
            chileFileNames = zooKeeper.getChildren(path, false);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error(e.getMessage(), e);
        } catch (KeeperException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return chileFileNames;
    }

    //get broker data by broker id
    public String getBrokerDateByBrokerId(String brokerId) {
        byte[] bytes = new byte[0];
        try {
            bytes = zooKeeper.getData(brokerId, null, null);
        } catch (KeeperException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error(e.getMessage(), e);
        }
        return new String(bytes);
    }

    public List<String> getTopics() {
        return getChild("/brokers/topics");
    }

    //obtain consumers  groups
    public Set<String> getGroups() {
        Set<String> groups = new HashSet<>();
        groups.addAll(getChild(CONSUMERS));
        return groups;
    }

    //obtain topics were consume by group
    public Set<String> getGroupConsumTopicsByGroupName(String groupName) {
        Set<String> topicsConsumeByGroup = new HashSet<>();
        topicsConsumeByGroup.addAll(getChild(CONSUMERS + "/" + groupName + "/owners"));
        return topicsConsumeByGroup;
    }

    public Map<String, List<String>> getTopicLagsAndOwnerMap(String group, String topic) {
        Map<String, List<String>> lagAndOwnerInstance = new HashMap<>();
        String topicPath = CONSUMERS + "/" + group + "/owners/" + topic;
        List<String> lags = getChild(topicPath);
        if (null != lags && !lags.isEmpty()) {
            for (String lag : lags) {
                List<String> owners = newArrayList();
                String owner = getBrokerDateByBrokerId(topicPath + "/" + lag);
                owners.add(owner);
                lagAndOwnerInstance.put(lag, owners);
            }
        }
        return lagAndOwnerInstance;
    }


    public Map<String, List<String>> getTopicOffset(String group, String topic) {
        Map<String, List<String>> lagAndOwnerInstance = new HashMap<>();
        String topicPath = CONSUMERS + "/" + group + "/offsets/" + topic;
        List<String> lags = getChild(topicPath);
        if (null != lags && !lags.isEmpty()) {
            for (String lag : lags) {
                List<String> offsets = newArrayList();
                String owner = getBrokerDateByBrokerId(topicPath + "/" + lag);
                offsets.add(owner);
                lagAndOwnerInstance.put(lag, offsets);
            }
        }
        return lagAndOwnerInstance;
    }

    public List<String> getTopicPartitions(String topic) {
        String topicPath = "/brokers/topics/" + topic + "/partitions";
        return getChild(topicPath);
    }

    public Map<String, Set<String>> getAllConsumersTopicMap() {
        Map<String, Set<String>> consumerTopicMap = new HashMap<>();
        Set<String> consumers = getGroups();
        for (String consumerName : consumers) {
            Set<String> consumerTopics = getGroupConsumTopicsByGroupName(consumerName);
            consumerTopicMap.put(consumerName, consumerTopics);
        }
        return consumerTopicMap;
    }

    public List<String> getBrokers() {
        return getChild("/brokers/ids");
    }

    public void close() {
        try {
            this.zooKeeper.close();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error(e.getMessage(), e);
        }
    }

}
