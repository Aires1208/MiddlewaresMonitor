package com.aires.kafka.monitor.domain.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

/**
 * Created by 10183966 on 2016/12/05.
 */
public class KafkaUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaUtil.class);

    private KafkaUtil() {
    }


    public static MBeanServerConnection getMBeanServerConnection(JMXConnector jmxConnector) {
        MBeanServerConnection mBeanServerConnection = null;
        try {
            mBeanServerConnection = jmxConnector.getMBeanServerConnection();
        } catch (NullPointerException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return mBeanServerConnection;
    }

    public static JMXConnector getJMXConnector(String jmxUrl) {
        JMXConnector jmxc = null;
        try {
            JMXServiceURL url = new JMXServiceURL(jmxUrl);
            jmxc = JMXConnectorFactory.connect(url, null);
        } catch (NullPointerException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return jmxc;
    }

}
