package com.lzz.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lzz on 2017/8/6.
 */
@Controller
@RequestMapping("/docker")
public class DockerController {
    @RequestMapping("/manager")
    public String manager(Model model){
        return "docker_manager";
    }

    @RequestMapping(value = "/image_list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject nodeInfo(@RequestBody String jsonBody){
        return new JSONObject();
    }
}
