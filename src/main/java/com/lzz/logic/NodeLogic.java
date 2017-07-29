package com.lzz.logic;

import net.sf.json.JSONObject;

/**
 * Created by lzz on 2017/7/29.
 */
public class NodeLogic {

    public JSONObject addNode(JSONObject reqObject) {
        String ip = reqObject.getString("ip");
        int port = reqObject.getInt("port");
        String username = reqObject.getString("username");
        String password = reqObject.getString("password");
        String install_path = reqObject.getString("install_path");
        String server = reqObject.getString("server");
        System.out.println( ip + port + username + password + install_path + server);
        return null;
    }
}
