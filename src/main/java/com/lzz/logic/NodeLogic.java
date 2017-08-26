package com.lzz.logic;

import com.lzz.dao.NodeDao;
import com.lzz.model.NodeModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by lzz on 2017/7/29.
 */
public class NodeLogic {

    public JSONObject addNode(JSONObject reqObject) {
        NodeModel node = (NodeModel) JSONObject.toBean( reqObject, NodeModel.class);
        NodeDao node1 = new NodeDao();
        //boolean res = node1.addNode( node );
        JSONObject result = new JSONObject();
        result.put("result", null);
        return result;
    }

    public JSONObject listNode(JSONObject reqObject) {
        String clusterid = reqObject.getString("clusterid");
        NodeDao node = new NodeDao();
        List<NodeModel> list = node.listNode(clusterid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", list);
        changeListStatus(jsonObject);
        return jsonObject;
    }

    /**
     * 一个一个的去ping 通
     * @param jsonObject
     */
    private void changeListStatus(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        for(Object item : jsonArray){
            JSONObject object = (JSONObject) item;
            String ip = object.getString("IP");
            int port = object.getInt("PORT");
            Jedis jedis = null;
            try {
                jedis = new Jedis( ip, port);
                String s = jedis.asking();
                object.put("STATUS","OK");
            }catch (Exception e){
                object.put("STATUS","FAIL");
            }finally {
                jedis.close();
            }
        }
    }

    public JSONObject moveCluster(JSONObject reqObject) {
        int id = reqObject.getInt("id");
        String ip = reqObject.getString("ip");
        int port = reqObject.getInt("port");
        String clusterIp = reqObject.getString("cluster_ip");
        int clusterPort = reqObject.getInt("cluster_port");
        JSONObject jsonObj = new JSONObject();
        Jedis jedis = null;
        try {
            jedis = new Jedis(ip, port);
            String res = jedis.clusterMeet(clusterIp, clusterPort);
            if( "OK".equals( res ) ){
                NodeDao node = new NodeDao();
                node.delNode( id );
            }
            jsonObj.put("result", res);
        }catch (Exception e){
            jsonObj.put("result", "FAIL");
        }finally {
            jedis.close();
        }
        return jsonObj;
    }

    public JSONObject deleteNode(JSONObject reqObject) {
        int  id = reqObject.getInt("id");
        NodeDao node = new NodeDao();
        boolean res = node.delNode( id );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", res);
        return jsonObject;
    }
}
