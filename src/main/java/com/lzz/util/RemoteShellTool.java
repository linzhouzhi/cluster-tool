package com.lzz.util;

/**
 * Created by lzz on 2017/7/29.
 */

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 远程Shell脚本执行工具
 *
 * @author Administrator
 */
public class RemoteShellTool {

    private Connection conn;
    private String ipAddr;
    private String charset = Charset.defaultCharset().toString();
    private String userName;
    private String password;

    public RemoteShellTool(String ipAddr, String userName, String password,
                           String charset) {
        this.ipAddr = ipAddr;
        this.userName = userName;
        this.password = password;
        if (charset != null) {
            this.charset = charset;
        }
    }

    /**
     * 登录远程Linux主机
     *
     * @return
     * @throws IOException
     */
    public boolean login() throws IOException {
        conn = new Connection(ipAddr);
        conn.connect(); // 连接
        return conn.authenticateWithPassword(userName, password); // 认证
    }

    /**
     * 执行Shell脚本或命令
     *
     * @param cmds
     *            命令行序列
     * @return
     */
    public String exec(String cmds) {
        InputStream in = null;
        String result = "";
        try {
            if (this.login()) {
                Session session = conn.openSession(); // 打开一个会话
                session.execCommand(cmds);
                in = session.getStdout();
                result = this.processStdout(in, this.charset);
                InputStream errin = null;
                errin = session.getStderr();
                result += this.processStdout(errin, this.charset);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            conn.close();
        }
        return result;
    }

    public String exec2(WebSocketSession socket, String cmds) {
        InputStream in = null;
        String result = "";
        Session session = null;
        try {
            if (this.login()) {
                session = conn.openSession(); // 打开一个会话
                session.execCommand(cmds);
                in = session.getStdout();
                result = this.processStdout2(socket,in, this.charset);
            }
        } catch (IOException e1) {
            session.close();
            e1.printStackTrace();
        } finally {
            conn.close();
        }
        return result;
    }

    /**
     * 解析流获取字符串信息
     *
     * @param in
     *            输入流对象
     * @param charset
     *            字符集
     * @return
     */
    public String processStdout(InputStream in, String charset) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return sb.toString();

    }

    /**
     * 解析流获取字符串信息
     *
     *
     * @param socket
     * @param in
     *            输入流对象
     * @param charset
     *            字符集
     * @return
     */
    public String processStdout2(WebSocketSession socket, InputStream in, String charset) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                socket.sendMessage(new TextMessage( line ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return sb.toString();

    }
}