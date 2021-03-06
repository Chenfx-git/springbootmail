package com.cfx.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeUtility;

import com.sun.mail.imap.IMAPMessage;

public class ReciveIMAPmail {

    public static void main(String[] args) throws Exception {
        messagessl();
        // 准备连接服务器的会话信息111
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", "smtp.qq.com");
        props.setProperty("mail.imap.port", "143");

        // 创建Session实例对象
        Session session = Session.getInstance(props);

        // 创建IMAP协议的Store对象
        Store store = session.getStore("imap");

        // 连接邮件服务器
        store.connect("", "");

        // 获得收件箱
        Folder folder = store.getFolder("INBOX");
        // 以读写模式打开收件箱
        folder.open(Folder.READ_WRITE);

        // 获得收件箱的邮件列表
        Message[] messages = folder.getMessages();

        // 打印不同状态的邮件数量
        System.out.println("收件箱中共" + messages.length + "封邮件!");
        System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
        System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
        System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");

        System.out.println("------------------------开始解析邮件----------------------------------");


        int total = folder.getMessageCount();
        System.out.println("-----------------您的邮箱共有邮件：" + total + " 封--------------");
        // 得到收件箱文件夹信息，获取邮件列表
        Message[] msgs = folder.getMessages();
        System.out.println("\t收件箱的总邮件数：" + msgs.length);
        for (int i = 0; i < total; i++) {
            Message a = msgs[i];
            //   获取邮箱邮件名字及时间

            System.out.println(a.getReplyTo());
            System.out.println(a.getSubject());

            System.out.println("==============");
                System.out.println(a.getSubject() + "   接收时间：" + a.getReceivedDate().toLocaleString()+"  contentType()" +a.getContentType());
        }
        System.out.println("\t未读邮件数：" + folder.getUnreadMessageCount());
        System.out.println("\t新邮件数：" + folder.getNewMessageCount());
        System.out.println("----------------End------------------");


        // 关闭资源
        folder.close(false);
        store.close();
    }



    public static void messagessl() {
/**
 * ssl加密
 */
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";


//方法代码块
        Properties props = new Properties();
        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", "imap.qq.com");
        props.setProperty("mail.imap.port", "993");
// 创建会话
        Session session = Session.getInstance(props);
        try {
            // 创建IMAP协议的Store对象
            Store store = session.getStore("imap");
            // 连接邮件服务器

            store.connect("imap.qq.com", 993, "", "");
            // 获得收件箱
            Folder folder = store.getFolder("INBOX");
            // 以读写模式打开收件箱
            folder.open(Folder.READ_WRITE);
            System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
            int unReadCount = folder.getUnreadMessageCount();
            folder.close(false);
            store.close();
//            return Result.ok(unReadCount);
        } catch (Exception e) {
//            log.warn("用户{}连接邮箱异常",username);
        }

    }

}
