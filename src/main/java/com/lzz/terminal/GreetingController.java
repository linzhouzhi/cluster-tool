package com.lzz.terminal;

import com.lzz.util.RemoteShellTool;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by lzz on 2017/7/28.
 */

@Controller
public class GreetingController {


    @MessageMapping("/com/lzz/terminal")
    @SendTo("/topic/greetings")
    public String greeting(String command) throws Exception {
        RemoteShellTool rms = new RemoteShellTool("127.0.0.1", "lzz", "363216", "utf-8");
        String result = rms.exec( command.trim() );
        return result;
    }

}