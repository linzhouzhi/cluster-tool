package com.lzz.model;

/**
 * Created by lzz on 2017/8/5.
 *
 connected_clients:2
 blocked_clients:0
 used_memory:1500832
 used_memory_rss:2641920
 used_memory_peak:1670480
 mem_fragmentation_ratio:1.76
 aof_enabled:0
 total_connections_received:140
 total_commands_processed:139
 instantaneous_ops_per_sec:0
 total_net_input_bytes:3336
 total_net_output_bytes:148477
 instantaneous_input_kbps:0.00
 instantaneous_output_kbps:0.00
 rejected_connections:0
 sync_full:0
 sync_partial_ok:0
 sync_partial_err:0
 expired_keys:0
 evicted_keys:0
 keyspace_hits:0
 keyspace_misses:0
 pubsub_channels:0
 pubsub_patterns:0
 latest_fork_usec:0
 migrate_cached_sockets:0
 used_cpu_sys:70.38
 used_cpu_user:33.22
 used_cpu_sys_children:0.00
 used_cpu_user_children:0.00
 */
public class NodeMonitorInfo {
    private int connected_clients;
    private int blocked_clients;
    private int used_memory;
    private int used_memory_rss;
    private int used_memory_peak;
    private float mem_fragmentation_ratio;
    private int aof_enabled;
    private int total_connections_received;
    private int total_commands_processed;
    private int instantaneous_ops_per_sec;
    private int total_net_input_bytes;
    private int total_net_output_bytes;
    private float instantaneous_input_kbps;
    private float instantaneous_output_kbps;
    private int rejected_connections;
    private int sync_full;
    private int sync_partial_ok;
    private int sync_partial_err;
    private int expired_keys;
    private int evicted_keys;
    private int keyspace_hits;
    private int keyspace_misses;
    private int pubsub_channels;
    private int pubsub_patterns;
    private int latest_fork_usec;
    private int migrate_cached_sockets;
    private float used_cpu_sys;
    private float used_cpu_user;
    private float used_cpu_sys_children;
    private float used_cpu_user_children;

    private String clusterid;
    private String nodeid;
    private String host;
    private String ip;
    private int port;

    private int day;
    private int hour;
    private int minute;
    private int add_time;

    public int getConnected_clients() {
        return connected_clients;
    }

    public void setConnected_clients(int connected_clients) {
        this.connected_clients = connected_clients;
    }

    public int getBlocked_clients() {
        return blocked_clients;
    }

    public void setBlocked_clients(int blocked_clients) {
        this.blocked_clients = blocked_clients;
    }

    public int getUsed_memory() {
        return used_memory;
    }

    public void setUsed_memory(int used_memory) {
        this.used_memory = used_memory;
    }

    public int getUsed_memory_rss() {
        return used_memory_rss;
    }

    public void setUsed_memory_rss(int used_memory_rss) {
        this.used_memory_rss = used_memory_rss;
    }

    public int getUsed_memory_peak() {
        return used_memory_peak;
    }

    public void setUsed_memory_peak(int used_memory_peak) {
        this.used_memory_peak = used_memory_peak;
    }

    public float getMem_fragmentation_ratio() {
        return mem_fragmentation_ratio;
    }

    public void setMem_fragmentation_ratio(float mem_fragmentation_ratio) {
        this.mem_fragmentation_ratio = mem_fragmentation_ratio;
    }

    public int getAof_enabled() {
        return aof_enabled;
    }

    public void setAof_enabled(int aof_enabled) {
        this.aof_enabled = aof_enabled;
    }

    public int getTotal_connections_received() {
        return total_connections_received;
    }

    public void setTotal_connections_received(int total_connections_received) {
        this.total_connections_received = total_connections_received;
    }

    public int getTotal_commands_processed() {
        return total_commands_processed;
    }

    public void setTotal_commands_processed(int total_commands_processed) {
        this.total_commands_processed = total_commands_processed;
    }

    public int getInstantaneous_ops_per_sec() {
        return instantaneous_ops_per_sec;
    }

    public void setInstantaneous_ops_per_sec(int instantaneous_ops_per_sec) {
        this.instantaneous_ops_per_sec = instantaneous_ops_per_sec;
    }

    public int getTotal_net_input_bytes() {
        return total_net_input_bytes;
    }

    public void setTotal_net_input_bytes(int total_net_input_bytes) {
        this.total_net_input_bytes = total_net_input_bytes;
    }

    public int getTotal_net_output_bytes() {
        return total_net_output_bytes;
    }

    public void setTotal_net_output_bytes(int total_net_output_bytes) {
        this.total_net_output_bytes = total_net_output_bytes;
    }

    public float getInstantaneous_input_kbps() {
        return instantaneous_input_kbps;
    }

    public void setInstantaneous_input_kbps(float instantaneous_input_kbps) {
        this.instantaneous_input_kbps = instantaneous_input_kbps;
    }

    public float getInstantaneous_output_kbps() {
        return instantaneous_output_kbps;
    }

    public void setInstantaneous_output_kbps(float instantaneous_output_kbps) {
        this.instantaneous_output_kbps = instantaneous_output_kbps;
    }

    public int getRejected_connections() {
        return rejected_connections;
    }

    public void setRejected_connections(int rejected_Econnections) {
        this.rejected_connections = rejected_Econnections;
    }

    public int getSync_full() {
        return sync_full;
    }

    public void setSync_full(int sync_full) {
        this.sync_full = sync_full;
    }

    public int getSync_partial_ok() {
        return sync_partial_ok;
    }

    public void setSync_partial_ok(int sync_partial_ok) {
        this.sync_partial_ok = sync_partial_ok;
    }

    public int getSync_partial_err() {
        return sync_partial_err;
    }

    public void setSync_partial_err(int sync_partial_err) {
        this.sync_partial_err = sync_partial_err;
    }

    public int getExpired_keys() {
        return expired_keys;
    }

    public void setExpired_keys(int expired_keys) {
        this.expired_keys = expired_keys;
    }

    public int getEvicted_keys() {
        return evicted_keys;
    }

    public void setEvicted_keys(int evicted_keys) {
        this.evicted_keys = evicted_keys;
    }

    public int getKeyspace_hits() {
        return keyspace_hits;
    }

    public void setKeyspace_hits(int keyspace_hits) {
        this.keyspace_hits = keyspace_hits;
    }

    public int getKeyspace_misses() {
        return keyspace_misses;
    }

    public void setKeyspace_misses(int keyspace_misses) {
        this.keyspace_misses = keyspace_misses;
    }

    public int getPubsub_channels() {
        return pubsub_channels;
    }

    public void setPubsub_channels(int pubsub_channels) {
        this.pubsub_channels = pubsub_channels;
    }

    public int getPubsub_patterns() {
        return pubsub_patterns;
    }

    public void setPubsub_patterns(int pubsub_patterns) {
        this.pubsub_patterns = pubsub_patterns;
    }

    public int getLatest_fork_usec() {
        return latest_fork_usec;
    }

    public void setLatest_fork_usec(int latest_fork_usec) {
        this.latest_fork_usec = latest_fork_usec;
    }

    public int getMigrate_cached_sockets() {
        return migrate_cached_sockets;
    }

    public void setMigrate_cached_sockets(int migrate_cached_sockets) {
        this.migrate_cached_sockets = migrate_cached_sockets;
    }

    public float getUsed_cpu_sys() {
        return used_cpu_sys;
    }

    public void setUsed_cpu_sys(float used_cpu_sys) {
        this.used_cpu_sys = used_cpu_sys;
    }

    public float getUsed_cpu_user() {
        return used_cpu_user;
    }

    public void setUsed_cpu_user(float used_cpu_user) {
        this.used_cpu_user = used_cpu_user;
    }

    public float getUsed_cpu_sys_children() {
        return used_cpu_sys_children;
    }

    public void setUsed_cpu_sys_children(float used_cpu_sys_children) {
        this.used_cpu_sys_children = used_cpu_sys_children;
    }

    public float getUsed_cpu_user_children() {
        return used_cpu_user_children;
    }

    public void setUsed_cpu_user_children(float used_cpu_user_children) {
        this.used_cpu_user_children = used_cpu_user_children;
    }

    public String getClusterid() {
        return clusterid;
    }

    public void setClusterid(String clusterid) {
        this.clusterid = clusterid;
    }

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
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return "NodeMonitorInfo{" +
                "connected_clients=" + connected_clients +
                ", blocked_clients=" + blocked_clients +
                ", used_memory=" + used_memory +
                ", used_memory_rss=" + used_memory_rss +
                ", used_memory_peak=" + used_memory_peak +
                ", mem_fragmentation_ratio=" + mem_fragmentation_ratio +
                ", aof_enabled=" + aof_enabled +
                ", total_connections_received=" + total_connections_received +
                ", total_commands_processed=" + total_commands_processed +
                ", instantaneous_ops_per_sec=" + instantaneous_ops_per_sec +
                ", total_net_input_bytes=" + total_net_input_bytes +
                ", total_net_output_bytes=" + total_net_output_bytes +
                ", instantaneous_input_kbps=" + instantaneous_input_kbps +
                ", instantaneous_output_kbps=" + instantaneous_output_kbps +
                ", rejected_connections=" + rejected_connections +
                ", sync_full=" + sync_full +
                ", sync_partial_ok=" + sync_partial_ok +
                ", sync_partial_err=" + sync_partial_err +
                ", expired_keys=" + expired_keys +
                ", evicted_keys=" + evicted_keys +
                ", keyspace_hits=" + keyspace_hits +
                ", keyspace_misses=" + keyspace_misses +
                ", pubsub_channels=" + pubsub_channels +
                ", pubsub_patterns=" + pubsub_patterns +
                ", latest_fork_usec=" + latest_fork_usec +
                ", migrate_cached_sockets=" + migrate_cached_sockets +
                ", used_cpu_sys=" + used_cpu_sys +
                ", used_cpu_user=" + used_cpu_user +
                ", used_cpu_sys_children=" + used_cpu_sys_children +
                ", used_cpu_user_children=" + used_cpu_user_children +
                ", clusterid=" + clusterid +
                ", nodeid=" + nodeid +
                ", host=" + host +
                ", ip=" + ip +
                ", port=" + port +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", add_time=" + add_time +
                '}';
    }
}
