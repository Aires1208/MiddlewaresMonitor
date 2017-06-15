package com.aires.kafka.monitor.domain.system;

import com.aires.kafka.monitor.domain.constant.EnvConstant;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * Created by ${10183966} on 12/6/16.
 */
@Configuration
public class ApplicationContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContext.class);

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        return container -> {
            if (TomcatEmbeddedServletContainerFactory.class.isAssignableFrom(container.getClass())) {
                int cacheSize = 126 * 1024 * 1024;
                LOGGER.info("Customizing tomcat factory. New cache size (KB) is " + cacheSize / 1024);
                TomcatEmbeddedServletContainerFactory tomcatFactory = (TomcatEmbeddedServletContainerFactory) container;
                tomcatFactory.addConnectorCustomizers(new TomcatConnectorConsumizerImpl());
                tomcatFactory.addContextCustomizers((context) -> {
                    StandardRoot standardRoot = new StandardRoot(context);
                    standardRoot.setCacheMaxSize(cacheSize);
                    context.setResources(standardRoot);
                });
            }
        };

//        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
//        tomcatFactory.addConnectorCustomizers(new TomcatConnectorConsumizerImpl());
    }


    public org.apache.hadoop.conf.Configuration configuration() {
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", EnvConstant.ZK_QUORUM);
        conf.set("hbase.zookeeper.property.clientPort", EnvConstant.ZK_PORT);
        return conf;
    }

    @Bean
    public HbaseTemplate hbaseTemplate() {
        return new HbaseTemplate(configuration());
    }

    class TomcatConnectorConsumizerImpl implements TomcatConnectorCustomizer {

        @Override
        public void customize(Connector connector) {
            Http11NioProtocol nioProtocol = (Http11NioProtocol) connector.getProtocolHandler();
            nioProtocol.setMaxConnections(2000);
            nioProtocol.setMaxThreads(1000);
            nioProtocol.setConnectionTimeout(60000);
        }
    }
}
