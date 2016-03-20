package com.xxplus.jms.chapter02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by admin on 2016-03-13.
 */
public class Chat implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(Chat.class);

    public TopicSession pubSession;
    public TopicPublisher topicPublisher;
    private TopicConnection topicConnection;
    private String username;

    public Chat(final String topicFactory, final String topicName, final String username) throws NamingException, JMSException {

        //使用jdni.properties文件获取一个JNDI连接
        InitialContext initialContext = new InitialContext();

        //查找一个JMS连接工厂并创建
        TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) initialContext.lookup(topicFactory);
        TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();

        //创建会话
        TopicSession pubSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSession subSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        //查找主题
        Topic topic = (Topic) initialContext.lookup(topicName);

        //创建JMS发布者和订阅者
        TopicPublisher topicPublisher = pubSession.createPublisher(topic);
        TopicSubscriber topicSubscriber = subSession.createSubscriber(topic, null, true);

        topicSubscriber.setMessageListener(this);

        this.pubSession = pubSession;
        this.topicPublisher = topicPublisher;
        this.topicConnection = topicConnection;
        this.username = username;

        //启动JMS连接,允许传送消息
        topicConnection.start();
    }


    /**
     * 接收来自TopicSubscriber的消息
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            logger.info("message : {}", ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用发布者创建消息并发送
     * @param text
     */
    public void sendMessage(String text){
        try {
            TextMessage message = pubSession.createTextMessage();
            message.setText(username + " : " + text);
            topicPublisher.publish(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            if(topicConnection != null) topicConnection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args.length != 3){
            logger.warn("Please input Factory, Topic and username.");
            return;
        }

        try {
            Chat chat = new Chat(args[0], args[1], args[2]);
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

            while (true){
                String s = bf.readLine();
                if("exit".equalsIgnoreCase(s)){
                    chat.close();
                    System.exit(0);
                }else{
                    chat.sendMessage(s);
                }
            }

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
