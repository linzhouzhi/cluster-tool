package com.lzz.controller;

import com.lzz.logic.NodeLogic;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/list")
    @ResponseBody
    public JSONObject list(@RequestBody String jsonBody){
        return new JSONObject();
    }

    @RequestMapping("/add")
    @ResponseBody
    public JSONObject add(@RequestBody String jsonBody){
        JSONObject reqObject = JSONObject.fromObject(jsonBody);
        NodeLogic nodeLogic = new NodeLogic();
        JSONObject result = nodeLogic.addNode( reqObject );
        return result;
    }


    @RequestMapping("/index")
    public String index(Model model){
        return "index";
    }
}
