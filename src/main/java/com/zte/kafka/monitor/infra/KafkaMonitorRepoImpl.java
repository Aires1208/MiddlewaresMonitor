package com.zte.kafka.monitor.infra;

import com.zte.kafka.monitor.domain.constant.KafkaMonitorConstant;
import com.zte.kafka.monitor.domain.model.KafkaMonitorInfo;
import com.zte.kafka.monitor.domain.server.KafkaMonitorRepo;
import com.zte.kafka.monitor.domain.utils.JsonUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by 10183966 on 2016/12/08.
 */
@Repository
public class KafkaMonitorRepoImpl implements KafkaMonitorRepo {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private static List<KafkaMonitorInfo> getKafkaMonitorInfos(ResultScanner resultScanner) {
        List<KafkaMonitorInfo> kafkaMonitorInfos = newArrayList();
        for (Result result : resultScanner) {
            List<Cell> cells = result.listCells();
            addEachPolicyAction(cells, kafkaMonitorInfos);
        }
        return kafkaMonitorInfos;
    }

    private static String generatorRowKey(KafkaMonitorInfo kafkaMonitorInfo) {
        return kafkaMonitorInfo.getZookeeper() + "^" + kafkaMonitorInfo.getName();
    }

    private static void addEachPolicyAction(List<Cell> cells, List<KafkaMonitorInfo> kafkaMonitorInfos) {
        for (Cell cell : cells) {
            KafkaMonitorInfo kafkaMonitorInfo = JsonUtils.deserialize(Bytes.toString(cell.getValue()), KafkaMonitorInfo.class);
            kafkaMonitorInfos.add(kafkaMonitorInfo);
        }
    }

    @Override
    public void insertKafkaMonitorInfo(KafkaMonitorInfo monitorInfo) {
        hbaseTemplate.put(KafkaMonitorConstant.KAFKA_MONITOR_INFO, generatorRowKey(monitorInfo), KafkaMonitorConstant.KAFKA_MONITOR_INFO_CF, monitorInfo.getName(), JsonUtils.serialize(monitorInfo).getBytes());
    }


    @Override
    public List<KafkaMonitorInfo> getKafkaMonitorInfos() {
        return hbaseTemplate.find(KafkaMonitorConstant.KAFKA_MONITOR_INFO, new Scan(), resultScanner -> {
            return getKafkaMonitorInfos(resultScanner);
        });
    }

    @Override
    public KafkaMonitorInfo getKafkaMonitorByZK(String zk) {
        List<KafkaMonitorInfo> kafkaMonitorInfos = getKafkaMonitorInfos();
        KafkaMonitorInfo kafkaMonitorInfoByZk = null;
        for (KafkaMonitorInfo kafkaMonitorInfo : kafkaMonitorInfos) {
            if (zk.equals(kafkaMonitorInfo.getZookeeper().trim())) {
                kafkaMonitorInfoByZk = kafkaMonitorInfo;
                break;
            }
        }
        return kafkaMonitorInfoByZk;
    }

    @Override
    public KafkaMonitorInfo getKafkaMonitorByMonitorName(String kafkaMonitorName) {
        return null;
    }


    @Override
    public List<KafkaMonitorInfo> getKafkaMonitorByKafkaMonitorName(String zookeeper, String kafkaMonitorName) {
        return getKafkaMonitorInfos().stream().filter(kafkaMonitorInfo -> kafkaMonitorInfo.getZookeeper().equals(zookeeper) && kafkaMonitorInfo.getName().equals(kafkaMonitorName)).collect(Collectors.toList());
    }

    @Override
    public boolean isKafkaMonitorExist(List<KafkaMonitorInfo> kafkaMonitorInfos, String kafkaMonitorName) {
        for (KafkaMonitorInfo kafkaMonitorInfo : kafkaMonitorInfos) {
            if (kafkaMonitorInfo.getName().equals(kafkaMonitorName))
                return true;
        }
        return false;
    }


}
