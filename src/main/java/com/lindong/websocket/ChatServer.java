/*
package com.lindong.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

*/
/**
 * 聊天服务器
 * /chat 声明websocket的名字
 *//*


@ServerEndpoint("/chat/{userId}")
public class ChatServer {

    // 日期格式化
    //private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //与某个客户端会话连接
    private Session session;
    private String userId;
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    //private static int onlineCount = 0;

    */
/**
     * concurrent包的线程安全，用来存放每个客户端对应的MyWebSocket对象。
     *若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
     *//*

    private static CopyOnWriteArraySet<ChatServer> webSocketSet = new CopyOnWriteArraySet<ChatServer>();


    */
/**
     * 通道建立成功需要执行的动作
     * @param session
     *//*

    @OnOpen
    public void onOpen (@PathParam("userId")String userId, Session session) {
        this.session = session;
        //获取当前登录用户id
        this.userId = userId;
        webSocketSet.add(this);
        System.out.println(this.userId);
        //System.out.println ("连接已经建立: sessionID:" + session.getId());
        //---------单聊-----------将用户id和session绑定到路由表
        //绑定之后就可以在其它地方根据id来获取session，这时两个用户私聊就可以实现了
        //routeTable.put(userId, session);

    }

    */
/**
     * 接收客户端的消息
     * @param message 客户端消息
     *//*

    @OnMessage
    public void onMessage (String message) {
        System.out.println ("开始接收数据: " + message);
        //向客户端发送消息
        try {
            for (ChatServer chatServer : webSocketSet) {
                chatServer.session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void error(Session session,Throwable t) {
        // 添加处理错误的操作
        System.out.println("发生错误");
        //t.printStackTrace();
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //移除当前对象
        System.out.println("连接已关闭!");
    }


}




















*/
