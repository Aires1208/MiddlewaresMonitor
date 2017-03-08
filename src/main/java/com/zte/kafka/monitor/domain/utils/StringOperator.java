package com.zte.kafka.monitor.domain.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by ${10183966} on 12/13/16.
 */
public class StringOperator {
    private static final long TWO_MIN_INTERVAL = 1000 * 60 * 2l;
    // 长日期格式
    private static final String TIME_FORMAT = "HH:mm:ss";

    public static String convert2String(long time, String dataFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(dataFormat);
        Date date = new Date(time);
        return sf.format(date);
    }

    public static boolean isAvailIp(String ipAddress) {

        if (ipAddress.length() < 7 || ipAddress.length() > 15 || "".equals(ipAddress)) {
            return false;
        }
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(ipAddress);
        return mat.find();
    }

    public static List<String> times(long currentTime) {
        List<String> times = newArrayList();
        for (int lag = 9; lag >= 0; lag--) {
            times.add(9 - lag, StringOperator.convert2String(currentTime - TWO_MIN_INTERVAL * lag, TIME_FORMAT));
        }
        return times;
    }

}
