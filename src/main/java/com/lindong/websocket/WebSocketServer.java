package com.lindong.websocket;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lindong.domain.User;
import com.lindong.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket服务
 */
@Controller
@ServerEndpoint(value = "/websocket/{userId}")
public class WebSocketServer {

    @Resource
    private IUserService userService;
    /**
     * 在线人数
     */
    private static int onlineCount = 0;

    /**
     * 在线用户的Map集合，key：用户id，value：Session对象
     */
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String,WebSocketServer> userMap = new ConcurrentHashMap<>();
    //会话对象
    private Session session;
    //用户标识id
    private String userId;
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        System.out.println(userId);
        this.userId = userId;
        this.session = session;
        if (userMap.get(userId) == null){
            userMap.put(userId,this);
            System.out.println("id为"+userId + "的人上线了!!");
            System.out.println("当前在线连接人数: " + userMap.size());
        }
        //在webSocketMap新增上线用户
        /*sessionMap.put(userId, session);
        System.out.println(sessionMap.get(userId));*/
        //在线人数+1
        WebSocketServer.onlineCount++;
        //System.out.println("上线人数: " + onlineCount);
        //通知除了自己之外的所有人
        //sendOnlineCount(session, "{'type':'onlineCount','onlineCount':" + WebSocketServer.onlineCount + ",userId:'" + userId + "'}");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {

        userMap.remove(userId);
        System.out.println(userId + "  用户下线啦!!");
        //下线用户id
       /* String logoutuserId = "";

        //从webSocketMap删除下线用户
        for (Entry<String, Session> entry : sessionMap.entrySet()) {
            if (entry.getValue() == session) {
                sessionMap.remove(entry.getKey());
                logoutuserId = entry.getKey();
                break;
            }
        }
        //在线人数减减
        WebSocketServer.onlineCount--;
        System.out.println("有人下线了! 在线人数: " + onlineCount);*/

    }

    /**
     * 服务器接收到客户端消息时调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            //JSON字符串转 HashMap
            HashMap hashMap = new ObjectMapper().readValue(message, HashMap.class);
            //System.out.println(message);
            //来源用户
            Map srcUser = (Map) hashMap.get("srcUser");
            //目标用户
            Map tarUser = (Map) hashMap.get("tarUser");
            //目标id
            String tarUserId = tarUser.get("userId") + "";

            if ("allUser" .equals(tarUserId)) {// 发送给所有人
                for (WebSocketServer item : userMap .values()) {
                    item.session.getAsyncRemote().sendText(message);
                }
            } else{ //发给指定人
                if (userMap.get(tarUserId) == null) {
                    System.out.println("该用户不在线!");
                    //userMap.get(srcUser.get("userId")+"").session.getAsyncRemote().sendText("未找到接收人,请确认信息后重新发送");
                } else {
                    userMap.get(srcUser.get("userId")+"").session.getAsyncRemote().sendText(message);
                    userMap.get(tarUserId).session.getAsyncRemote().sendText(message);
                }
            }

            //后期要做消息持久化

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        //error.printStackTrace();
        //System.out.println("发生了一个错误!");
    }

    /**
     * 私聊
     */
    private void privateChat(Session session, Map tarUser, HashMap hashMap) throws IOException {
        //获取目标用户的session
        Session tarUserSession = sessionMap.get(tarUser.get("userId"));

        //如果不在线则发送“对方不在线”回来源用户
        if (tarUserSession == null) {
            //session.getBasicRemote().sendText("{\"type\":\"0\",\"message\":\"对方不在线\"}");
            System.out.println("对方不在线!");
        } else {
            //hashMap.put("type", "1");
            //tarUserSession.getBasicRemote().sendText(new ObjectMapper().writeValueAsString(hashMap));
            tarUserSession.getBasicRemote().sendText(JSON.toJSONString(hashMap));
        }
    }

    /**
     * 群聊
     */
    private void groupChat(Session session,HashMap hashMap) throws IOException {
        for (Entry<String, Session> entry : sessionMap.entrySet()) {
            //自己就不用再发送消息了
            if (entry.getValue() != session) {
                hashMap.put("type", "2");
                //entry.getValue().getBasicRemote().sendText(new ObjectMapper().writeValueAsString(hashMap));
                entry.getValue().getBasicRemote().sendText(JSON.toJSONString(hashMap));
            }
        }
    }

    /**
     * 通知除了自己之外的所有人的方法
     */
    private void sendOnlineCount(Session session, String message) {
        for (Entry<String, Session> entry : sessionMap.entrySet()) {
            try {
                if (entry.getValue() != session) {
                    entry.getValue().getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取在线用户
     */
    @RequestMapping("/getOnlineList")
    @ResponseBody
    private List<User> getOnlineList() {
        List<Integer> list = new ArrayList<>();
        //遍历userMap
        for(String key : userMap.keySet()) {
            Integer uid = Integer.parseInt(key);
            list.add(uid);
        }
        if (list.size() == 0){
            return new ArrayList<>();
        }
        List<User> users = userService.listUser(list);
        return users;
    }

    @RequestMapping("/findUserStatus")
    @ResponseBody
    public String findUser(String uid){
        /*for(String key : userMap.keySet()) {
            System.out.println("---------key = " + key);
        }*/
        if (userMap.get(uid) != null){
            System.out.println("====该用户在线....");
            return "1";
        }else {
            System.out.println("====该用户不在线....");
        }
        //userService.testUser();
        return "0";
    }




}
