package com.xqf.hive.model;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-26 9:54
 */
public class BeanInfoIp {
    private String type = "all";
    private String time;
    private String ip;
    private double cpu_max;
    private double disc_max;
    private double net_max;
    private double mem_max;
    private double cpu_min;
    private double disc_min;
    private double net_min;
    private double mem_min;
    private double cpu_avg;
    private double disc_avg;
    private double net_avg;
    private double mem_avg;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getCpu_max() {
        return cpu_max;
    }

    public void setCpu_max(double cpu_max) {
        this.cpu_max = cpu_max;
    }

    public double getDisc_max() {
        return disc_max;
    }

    public void setDisc_max(double disc_max) {
        this.disc_max = disc_max;
    }

    public double getNet_max() {
        return net_max;
    }

    public void setNet_max(double net_max) {
        this.net_max = net_max;
    }

    public double getMem_max() {
        return mem_max;
    }

    public void setMem_max(double mem_max) {
        this.mem_max = mem_max;
    }

    public double getCpu_min() {
        return cpu_min;
    }

    public void setCpu_min(double cpu_min) {
        this.cpu_min = cpu_min;
    }

    public double getDisc_min() {
        return disc_min;
    }

    public void setDisc_min(double disc_min) {
        this.disc_min = disc_min;
    }

    public double getNet_min() {
        return net_min;
    }

    public void setNet_min(double net_min) {
        this.net_min = net_min;
    }

    public double getMem_min() {
        return mem_min;
    }

    public void setMem_min(double mem_min) {
        this.mem_min = mem_min;
    }

    public double getCpu_avg() {
        return cpu_avg;
    }

    public void setCpu_avg(double cpu_avg) {
        this.cpu_avg = cpu_avg;
    }

    public double getDisc_avg() {
        return disc_avg;
    }

    public void setDisc_avg(double disc_avg) {
        this.disc_avg = disc_avg;
    }

    public double getNet_avg() {
        return net_avg;
    }

    public void setNet_avg(double net_avg) {
        this.net_avg = net_avg;
    }

    public double getMem_avg() {
        return mem_avg;
    }

    public void setMem_avg(double mem_avg) {
        this.mem_avg = mem_avg;
    }
}