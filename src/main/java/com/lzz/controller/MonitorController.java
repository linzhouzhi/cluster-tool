package com.lzz.controller;

import com.lzz.logic.MonitorLogic;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * Created by lzz on 2017/8/4.
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController {
    @RequestMapping("/manager")
    public String manager(Model model){
        return "monitor_manager";
    }

    @RequestMapping("/manager2")
    public String manager2(Model model){
        return "monitor_manager2";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject list(@RequestBody String jsonBody){
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    @RequestMapping(value = "/list2", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject list2(@RequestBody String jsonBody){
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    @RequestMapping(value = "/graph_node", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject graphNode() throws IOException {

        JSONObject obj = new JSONObject();
        MonitorLogic logic = new MonitorLogic();
        obj = logic.nodeList();
        return obj;
    }

    @RequestMapping(value = "/node_info", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject nodeInfo(@RequestBody String jsonBody){
        Jedis jedis = new Jedis("127.0.0.1",8007);
        String nodestr = jedis.info();
        JSONObject obj = new JSONObject();
        obj.put("result", nodestr);
        return obj;
    }

}
