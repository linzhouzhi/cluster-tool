package com.lzz.util;

import com.lzz.dao.NodeMonitorInfoDao;
import com.lzz.model.NodeMonitorInfo;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by lzz on 2017/8/5.
 */
public class NodeInfoProducer {
    private static final int QUEUE_SIZE = 10;
    private static final int SCHEDULE_TIME = 60;
    private static BlockingQueue detailQueue = new LinkedBlockingDeque(QUEUE_SIZE);
    public static Thread producer = new Thread(){
        @Override
        public void run() {
            List<Map> list = new ArrayList();
            Map host1 = new HashMap();
            host1.put("ip", "127.0.0.1");
            host1.put("port", 8001);
            list.add( host1 );
            Map host2 = new HashMap();
            host2.put("ip", "127.0.0.1");
            host2.put("port", 8002);
            list.add( host2 );
            Map host3 = new HashMap();
            host3.put("ip", "127.0.0.1");
            host3.put("port", 8003);
            list.add( host3 );
            Map host4 = new HashMap();
            host4.put("ip", "127.0.0.1");
            host4.put("port", 8004);
            list.add( host4 );
            Map host5 = new HashMap();
            host5.put("ip", "127.0.0.1");
            host5.put("port", 8005);
            list.add( host5 );
            while (true){
                for(Map node : list){
                    Jedis jedis = null;
                    try {
                        String ip = (String) node.get("ip");
                        int port = (int) node.get("port");
                        jedis = new Jedis(ip, port, 20);
                        String strInfo = jedis.info();
                        NodeMonitorInfo info = getNodeMonitorInfo(strInfo);
                        NodeMonitorInfo resInfo = changeNodeInfoTime( info );
                        // 添加到数据库中
                        NodeMonitorInfoDao dao = new NodeMonitorInfoDao();
                        dao.addNodeInfo( resInfo );
                    }finally {
                        if( null != jedis ){
                            jedis.close();
                        }
                    }
                }
                try {
                    sleep( SCHEDULE_TIME * 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private static NodeMonitorInfo getNodeMonitorInfo(String strInfo) {
        NodeMonitorInfo o = null;
        try {
            o = new NodeMonitorInfo();
            Field[] fields=o.getClass().getDeclaredFields();

            BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(strInfo.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
            String line;
            while ( (line = br.readLine()) != null ) {
                String[] arr = line.split(":");
                if( arr.length == 2 ){
                    for(Field f : fields){
                        f.setAccessible(true);
                        if( arr[0].equals( f.getName() ) ){
                            String type = f.getGenericType().toString();
                            if( type.equals("class java.lang.String") ){
                                f.set( o, String.valueOf(arr[1]) );
                            }else if( "int".equals(type)){
                                f.set( o, Integer.valueOf(arr[1]) );
                            }else if( "float".equals( type ) ){
                                f.set( o, Float.valueOf(arr[1]) );
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return o;
    }

    private static NodeMonitorInfo changeNodeInfoTime(NodeMonitorInfo info) {
        info.setAdd_time( CommonUtil.getTime() );
        info.setDay( CommonUtil.getDay() );
        info.setHour( CommonUtil.getHour() );
        info.setMinute( CommonUtil.getMinute());
        return info;
    }
}
