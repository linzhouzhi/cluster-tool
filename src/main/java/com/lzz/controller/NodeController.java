package com.lzz.controller;

import com.lzz.logic.NodeLogic;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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

    @RequestMapping(value = "/upload_package", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam(value = "name",defaultValue = "hhh") String name,
                                                 @RequestParam("package") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
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
