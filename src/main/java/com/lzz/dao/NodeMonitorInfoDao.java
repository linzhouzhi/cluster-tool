package com.lzz.dao;

import com.lzz.model.NodeModel;
import com.lzz.model.NodeMonitorInfo;
import com.lzz.util.MysqlUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2017/8/5.
 */
public class NodeMonitorInfoDao {
    public boolean addNodeInfo(NodeMonitorInfo node){
        String sql = MysqlUtil.insertStr(node, "node_info");
        int res = MysqlUtil.insert( sql );
        if( res > 0 ){
            return true;
        }
        return false;
    }

    public List<NodeModel> getNodeMonitorInfo(String node, String date, String type, ArrayList filterFields) {
        String sql = "select ";
        String groupby = " group by " + date + " ";
        int add_time = 0;
        long currentTime = System.currentTimeMillis() / 1000;
        if(date.equals( "day" )){
            sql += "day as date, ";
            add_time = (int) (currentTime - (7 * 24 * 60 * 60));
        }else if( date.equals("hour")){
            sql += "concat(day,':',hour) as date, ";
            groupby += ",day order by day,hour";
            add_time = (int) (currentTime - (24 * 60 * 60));
        }else if( date.equals("minute") ){
            sql += "concat(hour, ':', minute) as date, ";
            groupby += ",hour,day order by day,hour,minute";
            add_time = (int) (currentTime - (60 * 60));
        }

        NodeMonitorInfo nodeInfo = new NodeMonitorInfo();
        sql += MysqlUtil.groupStr(nodeInfo, "node_info", type, filterFields);
        if( "all".equals(node) ){
            sql += " where add_time > " + add_time + groupby;
        }else{
            sql += " where add_time > " + add_time + " and host='" + node + "' " + groupby;
        }

        System.out.println( sql );
        List<NodeModel> list = MysqlUtil.select(sql);
        return list;
    }
}
