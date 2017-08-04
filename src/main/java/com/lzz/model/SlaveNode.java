package com.lzz.model;

/**
 * Created by lzz on 2017/8/4.
 */
public class SlaveNode {
    private String nodeid;
    private String host;
    private String name;
    private String role;
    private String ip;
    private String port;
    private String status;
    private String master;
    private int size = 1;

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
        this.name = this.host;
    }

    public String getName() {
        return name;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "SlaveNode{" +
                "nodeid='" + nodeid + '\'' +
                ", host='" + host + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", status='" + status + '\'' +
                ", master='" + master + '\'' +
                '}';
    }
}
