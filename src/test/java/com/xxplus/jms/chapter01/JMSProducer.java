package com.xxplus.jms.chapter01;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息的生产者
 *
 * Created by admin on 2016-03-10.
 */
public class JMSProducer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static int SEND_NUM = 10;

    public static void main(String[] args) {

        //连接工厂
        ConnectionFactory factory = null;

        //连接
        Connection connection = null;

        //会话，接收或发送消息的线程
        Session session = null;

        //消息的目的地
        Destination destination = null;

        //消息生产者
        MessageProducer messageProducer = null;


        factory = new ActiveMQConnectionFactory(JMSProducer.USERNAME,  JMSProducer.PASSWORD, JMSProducer.BROKEN_URL);

        try{
            connection = factory.createConnection();

            connection.start();

            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            destination = session.createQueue("Hello World");

            messageProducer = session.createProducer(destination);

            sendMessage(session, messageProducer);

            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * @param session
     * @param messageProducer  消息生产者
     * @throws Exception
     */
    public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
        for (int i = 0; i < JMSProducer.SEND_NUM; i++) {
            //创建一条文本消息
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息" +i);
            System.out.println("发送消息：Activemq 发送消息" + i);
            //通过消息生产者发出消息
            messageProducer.send(message);
        }

    }
}
