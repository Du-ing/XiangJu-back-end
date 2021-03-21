package com.xiangju.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{userid}")
@Component
public class WebSocketServer {

    //map存储所有在线用户，并且保证线程安全
    private static Map<String, WebSocketServer> clients = new ConcurrentHashMap<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接成功
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userid") String userid, Session session) throws IOException {
        this.session = session;
        clients.put(userid, this);
        System.out.println(userid + "用户连接成功！");
        session.getBasicRemote().sendText("Welcome!");

        //TODO:用户上线，告诉用户是否有新的消息

    }

    /**
     * 连接断开
     */
    @OnClose
    public void onClose() throws IOException {
        this.session.close(); //关闭会话
        String userid = null;
        for (String uid : clients.keySet()) {
            if (clients.get(uid) == this){
                userid = uid;
                clients.remove(this);   //从在线客户中删除该用户
                break;
            }
        }
        System.out.println(userid + "用户断开连接");
    }

    /**
     * 接收到客户端消息
     * 将发送者消息转发给接收者
     * data包含发送者、接收者和消息的信息，需要特殊设计
     */
    @OnMessage
    public void onMessage(String data, Session session) throws IOException {
        System.out.println(data);
        session.getBasicRemote().sendText("get it!");

        //TODO:从data中提取需要的信息
        String senderid = "senderid";   //发送者id
        String receiverid = "receveid";  //接收者id
        String msg = "msg";   //消息

        WebSocketServer receiver = clients.get(receiverid);  //接收者会话
        if (null == receiver){
            //接收者不在线，返回给发送者的消息
            session.getBasicRemote().sendText("此用户不在线！");
        }else {
            //TODO:接收者在线，处理消息后发送给接收者
            String data1 = senderid + "$" + msg;
            receiver.session.getBasicRemote().sendText(data1);
        }
    }
}
