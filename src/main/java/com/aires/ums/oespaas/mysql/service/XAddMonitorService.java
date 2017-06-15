package com.aires.ums.oespaas.mysql.service;

import com.aires.ums.oespaas.mysql.bean.hbase.RegisterInfo;
import com.aires.ums.oespaas.mysql.hbase.RegisterInfoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by root on 9/7/16.
 */
@Service
public class XAddMonitorService {
    private static final Logger logger = LoggerFactory.getLogger(XAddMonitorService.class);

    public Map<String, String> addDBMonitor(RegisterInfo registerInfo) {
        return RegisterInfoHandler.insert(registerInfo);
    }

    public Map<String, String> delDBMonitor(String dbNeId) {
        return RegisterInfoHandler.delete(dbNeId);
    }
}
