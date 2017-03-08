package com.zte.kafka.monitor.domain.model;

/**
 * Created by ${10183966} on 12/8/16.
 */
public class CombinedMetric {
    //"name":"Messages in /sec","mean":0.0 ,"min1":0.0,"min5":0.0,"min15":0.0
    private String name;
    private double mean;
    private double min1;
    private double min5;
    private double min15;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getMin1() {
        return min1;
    }

    public void setMin1(double min1) {
        this.min1 = min1;
    }

    public double getMin5() {
        return min5;
    }

    public void setMin5(double min5) {
        this.min5 = min5;
    }

    public double getMin15() {
        return min15;
    }

    public void setMin15(double min15) {
        this.min15 = min15;
    }

    @Override
    public String toString() {
        return "CombinedMetric{" +
                "name='" + name + '\'' +
                ", mean=" + mean +
                ", min1=" + min1 +
                ", min5=" + min5 +
                ", min15=" + min15 +
                '}';
    }
}
