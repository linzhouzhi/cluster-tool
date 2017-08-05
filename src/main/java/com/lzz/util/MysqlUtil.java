package com.lzz.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2017/8/5.
 */
public class MysqlUtil extends SQLBase{
    private static byte[] lock = new byte[0];
    private static volatile  MysqlUtil mysqlUtil;
    private static SQLBase getInstance(){
        if( null == mysqlUtil ){
            synchronized ( lock ){
                if( null == mysqlUtil ){
                    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
                    mysqlUtil = (MysqlUtil) ctx.getBean("mysqlUitl");
                }
            }
        }
        return mysqlUtil;
    }

    /**
     *  插入一条数据
     * @param sql
     * @return 插入的 ID
     */
    public static int insertId(String sql){
        return getInstance().baseInsert( sql );
    }

    public static int insert(String sql){
        int res = getInstance().baseInsert( sql );
        return res;
    }

    public static int update(String sql){
        int res = getInstance().baseUpdate( sql );
        return res;
    }

    /**
     * 获取 roles 列表
     * @return
     */
    public static List select(String sql){
        return getInstance().baseSelect( sql );
    }

    public static boolean delete( String sql ){
        return  getInstance().baseDelete( sql );
    }

    public static Map selectRow(String sql ){
        return getInstance().baseSelectRow( sql );
    }
}
