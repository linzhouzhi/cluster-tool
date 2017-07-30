package com.lzz.controller;

import com.lzz.logic.NodeLogic;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lzz on 2017/7/28.
 */
@Controller
@RequestMapping("/node")
public class NodeController {
    @RequestMapping("/manager")
    public String manager(Model model){
        return "node_manager";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject list(@RequestBody String jsonBody){
        JSONObject reqObject = JSONObject.fromObject(jsonBody);
        NodeLogic nodeLogic = new NodeLogic();
        JSONObject jsonObject = nodeLogic.listNode(reqObject);
        return jsonObject;
    }

    @RequestMapping("/add")
    @ResponseBody
    public JSONObject add(@RequestBody String jsonBody){
        JSONObject reqObject = JSONObject.fromObject(jsonBody);
        NodeLogic nodeLogic = new NodeLogic();
        JSONObject result = nodeLogic.addNode( reqObject );
        return result;
    }

    @RequestMapping("/move_cluster")
    @ResponseBody
    public JSONObject moveCluster(@RequestBody String jsonBody){
        JSONObject reqObject = JSONObject.fromObject(jsonBody);
        NodeLogic nodeLogic = new NodeLogic();
        JSONObject result = nodeLogic.moveCluster(reqObject);
        return result;
    }

    @RequestMapping("/delete_node")
    @ResponseBody
    public JSONObject deleteNode(@RequestBody String jsonBody){
        JSONObject reqObject = JSONObject.fromObject(jsonBody);
        NodeLogic nodeLogic = new NodeLogic();
        JSONObject result = nodeLogic.deleteNode(reqObject);
        return result;
    }


    @RequestMapping("/index")
    public String index(Model model){
        return "index";
    }
}
