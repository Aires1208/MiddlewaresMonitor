package com.aires.kafka.monitor;

import com.aires.kafka.monitor.domain.utils.KafkaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.*;
import javax.management.remote.JMXConnector;
import java.io.IOException;

/**
 * Created by ${aires} on 12/5/16.
 */
public class KafkaMetricsDataProvider {
    private static final String PRE_JMX_URL = "service:jmx:rmi:///jndi/rmi://";
    private static final String SUB_JMX_URL = "/jmxrmi";
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMetricsDataProvider.class);
    private String constructorjmxURL;
    private JMXConnector jmxConnector;
    private MBeanServerConnection mBeanServerConnection;

    public KafkaMetricsDataProvider(String jmxURL) {
        this.constructorjmxURL = PRE_JMX_URL + jmxURL + SUB_JMX_URL;
        this.jmxConnector = KafkaUtil.getJMXConnector(constructorjmxURL);
        this.mBeanServerConnection = getJmxConnection();
    }


    public Long getMbeanLongValue(String mbeanName) {
        Long mbeanAttributeValue = null;
        try {
            ObjectName mbean = new ObjectName(mbeanName);
            if (null == mBeanServerConnection) {
                mbeanAttributeValue = -1l;
                throw new NullPointerException();
            } else {
                mbeanAttributeValue = (Long) mBeanServerConnection.getAttribute(mbean, "Count");
            }
        } catch (NullPointerException |
                MalformedObjectNameException |
                IOException |
                ReflectionException |
                InstanceNotFoundException |
                AttributeNotFoundException |
                MBeanException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null != mbeanAttributeValue ? mbeanAttributeValue : -1;
    }


    private MBeanServerConnection getJmxConnection() {
        return KafkaUtil.getMBeanServerConnection(jmxConnector);
    }

    public void close() {
        try {
            jmxConnector.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }
}
