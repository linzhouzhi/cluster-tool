package com.lzz.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2017/8/5.
 */
public class SQLBase {
    private volatile JdbcTemplate jdbcTpl;

    /**
     *  插入一条数据
     * @param sql
     * @return 插入的 ID
     */
    public int baseInsertId(String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTpl.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
            {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public int baseInsert(String sql){
        int res = jdbcTpl.update(sql);
        return res;
    }

    public int baseUpdate(String sql){
        int res = jdbcTpl.update(sql);
        return res;
    }

    /**
     * 获取 roles 列表
     * @return
     */
    public List baseSelect(String sql){
        List list = jdbcTpl.queryForList(sql);
        return list;
    }

    public boolean  baseDelete( String sql ){
        int i = jdbcTpl.update( sql );
        if( i > 0 ){
            return true;
        }
        return false;
    }

    public Map baseSelectRow(String sql ){
        Map map;
        try{
            map = jdbcTpl.queryForMap( sql );
        }catch (Exception e){
            map = null;
        }
        return map;
    }

    public void baseInitDb(){
        // 创建 node 表
        jdbcTpl.execute("CREATE TABLE if not exists node(" +
                "id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY, " +
                "clusterid VARCHAR(100), " +
                "ip VARCHAR(25), " +
                "port int, " +
                "username VARCHAR(30), " +
                "password VARCHAR(30), " +
                "server VARCHAR(30), " +
                "install_path VARCHAR(255), " +
                "add_time BIGINT)");
    }

    public void setJdbcTpl(JdbcTemplate jdbcTpl) {
        this.jdbcTpl = jdbcTpl;
    }

    public static String groupStr(Object o, String table, String type, ArrayList filterFields){
        String sql = "";
        Field[] fields=o.getClass().getDeclaredFields();
        List field = new ArrayList();
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            String name = f.getName();
            if( !filterFields.contains( name ) ){
                String tmp = type + "(" + name + ") as " + name;
                field.add( tmp );
            }
        }
        String fieldStr = StringUtils.join( field, "," );
        sql += fieldStr + " from " + table;
        return sql;
    }

    public static String insertStr(Object o, String table){
        String sql = "insert into " + table;
        List field = new ArrayList();
        List values = new ArrayList();
        try {
            Field[] fields=o.getClass().getDeclaredFields();
            for(int i=0; i<fields.length; i++){
                Field f = fields[i];
                String name = f.getName();
                f.setAccessible(true);
                field.add( name );
                String tmp = "";
                String type = f.getGenericType().toString();
                if( type.equals("class java.lang.String") ){
                    tmp = "'" + f.get(o) + "'";
                }else{
                    tmp = String.valueOf( f.get(o) );
                }
                values.add( tmp );
            }
            String fieldStr = StringUtils.join( field, "," );
            String valueStr = StringUtils.join( values, "," );
            sql += "(" + fieldStr + ") ";
            sql += "values (" + valueStr + ")";
        }catch (Exception e){
            e.printStackTrace();
        }
        return sql;
    }
}
