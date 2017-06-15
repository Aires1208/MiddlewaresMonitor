package com.aires.kafka.monitor.domain.constant;

/**
 * Created by 10172605 on 9/6/16.
 */
public final class EnvConstant {
    public static final String ZK_PORT = getZKPort();
    private static final String LOCALHOST = "127.0.0.1";
    public static final String ZK_QUORUM = getZkQuorum();

    private EnvConstant() {
    }

    private static String getZKPort() {
        return System.getenv("HBase_Port") == null ? "2181" : System.getenv("HBase_Port");
    }

    private static String getZkQuorum() {
        return System.getenv("HBase_IP") == null ? LOCALHOST : System.getenv("HBase_IP");
    }
}