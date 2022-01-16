package com.cfx.service;

import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;

//查看收件箱邮件
public class ReadMailUtil {
    public static void main(String[] args) {
        //设置SSL连接、邮件环境
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.imap.host", "imap.qq.com");
        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.imap.port", "993");
        props.setProperty("mail.imap.socketFactory.port", "993");
        props.setProperty("mail.imap.auth", "true");
        //建立邮件会话
        Session session = Session.getInstance(props, null);
        //设置连接邮件仓库的环境
        URLName url = new URLName("imap", "imap.qq.com", 993, null, "380119651@qq.com", "pnykcsuuvascbiaj");
        Store store = null;
        Folder inbox = null;
        try {
            //得到邮件仓库并连接
            store = session.getStore(url);
            store.connect();
            //得到收件箱并抓取邮件
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            FetchProfile profile = new FetchProfile();
            profile.add(FetchProfile.Item.ENVELOPE);
            //false代表未读，true代表已读
            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            //根据设置好的条件获取message
            Message[] messages = inbox.search(ft);
//            Message[] messages = inbox.getMessages();
            inbox.fetch(messages, profile);
            int length = messages.length;
            System.out.println("收件箱的邮件数：" + length);
            System.out.println("-------------------------------------------\n");
            Folder defaultFolder = store.getDefaultFolder();
            Folder[] folders = defaultFolder.list();
            for (int i = 0; i < length; i++) {
                String from = MimeUtility.decodeText(messages[i].getFrom()[0].toString());
                InternetAddress ia = new InternetAddress(from);
                System.out.println("发件人：" + ia.getPersonal() + '(' + ia.getAddress() + ')');
                System.out.println("主题：" + messages[i].getSubject());
                System.out.println("邮件发送时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(messages[i].getSentDate()));
                System.out.println("-------------------------------------------\n");
                messages[i].setFlag(Flags.Flag.SEEN, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inbox != null) {
                    inbox.close(false);
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                if (store != null) {
                    store.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
