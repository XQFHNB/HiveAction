package com.xqf.hive.model;

/**
 * 描述:
 * 总表统计
 *
 * @author xqf
 * @create 2018-04-17 9:15
 */
public class BeanInfo {
    private String type = "all";
    private String time;
    private String ip;
    private Double cpu_used;
    private Double cpu_free;
    private Double cpu_utilization;
    private Double mem_used;

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

    public Double getCpu_used() {
        return cpu_used;
    }

    public void setCpu_used(Double cpu_used) {
        this.cpu_used = cpu_used;
    }

    public Double getCpu_free() {
        return cpu_free;
    }

    public void setCpu_free(Double cpu_free) {
        this.cpu_free = cpu_free;
    }

    public Double getCpu_utilization() {
        return cpu_utilization;
    }

    public void setCpu_utilization(Double cpu_utilization) {
        this.cpu_utilization = cpu_utilization;
    }

    public Double getMem_used() {
        return mem_used;
    }

    public void setMem_used(Double mem_used) {
        this.mem_used = mem_used;
    }

    public Double getMem_free() {
        return mem_free;
    }

    public void setMem_free(Double mem_free) {
        this.mem_free = mem_free;
    }

    public Double getMem_utilization() {
        return mem_utilization;
    }

    public void setMem_utilization(Double mem_utilization) {
        this.mem_utilization = mem_utilization;
    }

    public Double getDisc_used() {
        return disc_used;
    }

    public void setDisc_used(Double disc_used) {
        this.disc_used = disc_used;
    }

    public Double getDisc_free() {
        return disc_free;
    }

    public void setDisc_free(Double disc_free) {
        this.disc_free = disc_free;
    }

    public Double getDisc_utilization() {
        return disc_utilization;
    }

    public void setDisc_utilization(Double disc_utilization) {
        this.disc_utilization = disc_utilization;
    }

    public Double getNet_upload() {
        return net_upload;
    }

    public void setNet_upload(Double net_upload) {
        this.net_upload = net_upload;
    }

    public Double getNet_download() {
        return net_download;
    }

    public void setNet_download(Double net_download) {
        this.net_download = net_download;
    }

    public Double getNet_utilization() {
        return net_utilization;
    }

    public void setNet_utilization(Double net_utilization) {
        this.net_utilization = net_utilization;
    }

    private Double mem_free;
    private Double mem_utilization;
    private Double disc_used;
    private Double disc_free;
    private Double disc_utilization;
    private Double net_upload;
    private Double net_download;
    private Double net_utilization;
}