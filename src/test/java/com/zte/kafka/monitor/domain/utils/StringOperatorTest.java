package com.zte.kafka.monitor.domain.utils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ${10183966} on 12/21/16.
 */
public class StringOperatorTest {
    @Test
    public void convert2String() throws Exception {
        StringOperator stringOperator = new StringOperator();
        assertEquals("08:43:36", StringOperator.convert2String(1482281016975l, "HH:mm:ss"));
    }

    @Test
    public void isAvailIp() throws Exception {
        assertTrue(StringOperator.isAvailIp("10.62.100.78"));

    }

    @Test
    public void should_be_return_false_when_ip_less_than_7() {
        assertFalse(StringOperator.isAvailIp("10"));

    }

    @Test
    public void should_be_return_false_when_ip_more_than_15() {
        assertFalse(StringOperator.isAvailIp("10.62.100.78:51858"));

    }

    @Test
    public void should_be_return_false_when_ip_is_null() {
        assertFalse(StringOperator.isAvailIp(""));

    }

    @Test
    public void times() throws Exception {
        List<String> times = StringOperator.times(1482281016975l);
        times.stream().forEach(time -> System.out.println(time));
    }

}