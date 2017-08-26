package com.lzz.terminal;

/**
 * Created by lzz on 2017/7/29.
 */

import com.lzz.util.RemoteShellTool;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;

public class SystemWebSocketHandler implements WebSocketHandler {

    private Logger log = LoggerFactory.getLogger(SystemWebSocketHandler.class);

    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("ConnectionEstablished");
        log.debug("ConnectionEstablished");
        users.add(session);

        session.sendMessage(new TextMessage("connect"));
        session.sendMessage(new TextMessage("new_msg"));

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        JSONObject reqObject = JSONObject.fromObject( message.getPayload().toString() );
        String ip = reqObject.getString("ip").trim();
        int port = reqObject.getInt("port");
        String install_path = reqObject.getString("install_path").trim();
        String username = reqObject.getString("username").trim();
        String password = reqObject.getString("password").trim();
        username="lzz";
        password="363216";
        RemoteShellTool rms = new RemoteShellTool("192.168.31.140", "lzz", "linzhouzhi", "utf-8");
        String[] cmds = new String[3];
        cmds[0] = "cd /home/lzz/work/cluster-test";
        cmds[1] = "wget http://192.168.31.147:8080/shell/install.sh";
        cmds[2] = "sh install.sh 'zookeeper-3.4.8.tar.gz'";
        String cmd = StringUtils.join( cmds, ";");
        System.out.println( cmd );
        rms.exec2( session, cmd );

        //session.sendMessage(new TextMessage(new Date() + "" +message.toString()));

        //sendMessageToUsers();
        //RemoteShellTool rms = new RemoteShellTool("127.0.0.1", "lzz", "363216", "utf-8");
        //String result = rms.exec2(session, "/Users/lzz/work/java/cluster-tool/test.sh");

        //for(int i = 0; i < 100; i++ ){
        //    Thread.sleep(500);
        //    session.sendMessage(new TextMessage(new Date() + ""));
        //}
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        users.remove(session);

        log.debug("handleTransportError" + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        users.remove(session);
        System.out.println("afterConnectionClosed" + closeStatus.getReason());

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}