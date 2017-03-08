package com.zte.kafka.monitor.domain.constant;

/**
 * Created by ${10183966} on 12/9/16.
 */
public class MbeanName {
    public static final String MESSAGE_IN_PER_SEC = "kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec";
    public static final String BYTES_IN_PER_SEC = "kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec";
    public static final String BYTES_OUT_PER_SEC = "kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec";
    public static final String PRODUCE_REQUEST_PER_SEC = "kafka.network:type=RequestMetrics,name=RequestsPerSec,request=Produce";
    public static final String CONSUMER_REQUEST_PER_SEC = "kafka.network:type=RequestMetrics,name=RequestsPerSec,request=FetchConsumer";
    public static final String FLOWER_REQUEST_PER_SEC = "kafka.network:type=RequestMetrics,name=RequestsPerSec,request=FetchFollower";
    public static final String LOG_FLUS_RATE_AND_TIME_MS = "kafka.log:type=LogFlushStats,name=LogFlushRateAndTimeMs";
    public static final String UNDER_REPLICATED_PARTITIONS = "kafka.server:type=ReplicaManager,name=UnderReplicatedPartitions";
    public static final String ACTIVE_CONTROLLER_COUNT = "kafka.controller:type=KafkaController,name=ActiveControllerCount";
    public static final String LEADER_ELECTION_RATE_AND_TIME_MS = "kafka.controller:type=ControllerStats,name=LEADER_ELECTION_RATE_AND_TIME_MS";
    public static final String UNCLEAN_LEADER_ELECTIONS_PER_SEC = "kafka.controller:type=ControllerStats,name=UNCLEAN_LEADER_ELECTIONS_PER_SEC";
    public static final String PART_COUNT = "kafka.server:type=ReplicaManager,name=PartitionCount";
    public static final String LEADER_COUNT = "kafka.server:type=ReplicaManager,name=LEADER_COUNT";
    public static final String BYTES_REJECTED_PER_SEC = "kafka.server:type=BrokerTopicMetrics,name=BytesRejectedPerSec";
    public static final String FAILED_FETCH_REQUESTS_PER_SEC = "kafka.server:type=BrokerTopicMetrics,name=FailedFetchRequestsPerSec";
    public static final String FAILED_PRODUCE_REQUESTS_PER_SEC = "kafka.server:type=BrokerTopicMetrics,name=TotalProduceRequestsPerSec";
    public static final String UNDER_REPLICATED = "kafka.cluster:type=Partition,name=UNDER_REPLICATED,topic=policy_event,partition=0";

    private MbeanName() {
    }


}
