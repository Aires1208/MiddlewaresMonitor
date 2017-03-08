package com.zte.kafka.monitor.domain.exception;

/**
 * Created by ${10183966} on 12/6/16.
 */
public class KafkaMonitorException extends RuntimeException {
    public static final String KAFKA_MONITOR_ALREADY_EXISTS = "kafka monitor info already exist in hbase";
    public static final String KAFKA_MONITOR_NOT_EXISTS = "kafka monitor info  does not exists, please check whether it is defined";

    public KafkaMonitorException(String message) {
        super(message);
    }

}
