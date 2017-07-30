package com.lzz.dao;

import com.lzz.model.NodeModel;
import com.lzz.util.SqlUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by lzz on 2017/7/30.
 */
public class NodeDao {

    public boolean delNode(int id){
        String sql = "DELETE FROM Node where id = " + id;
        return SqlUtil.delete(sql);
    }

    public boolean addNode(NodeModel node){
        int res = SqlUtil.insert( insertSql( node ) );
        if( res > 0 ){
            return true;
        }
        return false;
    }

    /**
     * node 列表
     * @param clusterid
     * @return
     */
    public List<NodeModel> listNode(String clusterid) {
        String sql = selectSql(clusterid);
        List list = SqlUtil.select( sql );
        return list;
    }

    private String selectSql(String clusterid) {
        String sql = "select * from node where clusterid='" + clusterid + "'";
        return sql;
    }

    public String insertSql(NodeModel node){
        String sql = "";
        sql += "insert into node(clusterid, ip, port, username, password, server,install_path,add_time)";
        sql += "values(";
        sql += "'" + node.getClusterid() + "',";
        sql += "'" + node.getIp() + "',";
        sql += node.getPort() + ",";
        sql += "'" + node.getUsername() + "',";
        sql += "'" + node.getPassword() + "',";
        sql += "'" + node.getServer() + "',";
        sql += "'" + node.getInstall_path() + "',";
        sql += new Date().getTime();
        sql += ")";
        return sql;
    }
}
