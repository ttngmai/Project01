package com.project.lsh.service;

import java.util.Random;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.lsh.beans.AuthBean;
import com.project.lsh.beans.UserBean;
import com.project.lsh.dao.AuthDao;

@Service
public class MailSendService {
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	@Autowired
	private JavaMailSender mailSender;
    @Autowired
    private AuthDao authDao;
    private int size;
    
    // 메일 전송 전 존재하는 인증키 삭제
    public void deleteAuthKeyBeforeSendMail(int user_idx) {
    	authDao.deleteAuthKeyBeforeSendMail(user_idx);
    }
    
    // 메일 확인 후 해당 인증키 삭제
	public void deleteAuthKeyAfterCheckMail(String auth_key) {
		authDao.deleteAuthKeyAfterCheckMail(auth_key);
	}
    
    // 인증키 생성
    private String getKey(int size) {
        this.size = size;
        
        return getAuthCode();
    }

    // 인증코드 난수 발생
    private String getAuthCode() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }
    
	// 인증 메일 보내기(회원가입 완료용)
	public void sendAuthMailForJoin(UserBean userBean) {
		int user_idx = userBean.getUser_idx();
		String user_email = userBean.getUser_email();
		// 인증번호 생성
		String auth_key = getKey(12);
		
		// 메일 전송 전 존재하는 인증키 삭제
		deleteAuthKeyBeforeSendMail(user_idx);
		
		AuthBean authBean = new AuthBean();
		authBean.setUser_idx(user_idx);
		authBean.setAuth_key(auth_key);
		
		authDao.addAuthKey(authBean);

		// 인증메일 보내기
		MimeMessage message = mailSender.createMimeMessage();
		String mailContent = "<h1>[이메일 인증]</h1><br><p>아래 링크를 클릭하시면 회원가입이 완료됩니다.</p>"
							 + "<a href='http://52.79.58.253:8080/project01/user/mail/join?"
							 + "user_email=" + user_email + "&auth_key=" + auth_key
							 + "' target='_blank'>이메일 인증 확인</a>";

		try {
			message.setSubject("[LSH-Project01] 회원가입 이메일 인증 ", "utf-8");
			message.setText(mailContent, "utf-8", "html");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user_email));
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	// 인증 메일 보내기 (이메일 변경용)
	public void sendAuthMailForUpdate(UserBean userBean) {
		int user_idx = loginUserBean.getUser_idx();
		String user_email = userBean.getUser_email();
		// 인증번호 생성
		String auth_key = getKey(12);
		
		// 메일 전송 전 존재하는 인증키 삭제
		deleteAuthKeyBeforeSendMail(user_idx);
		
		AuthBean authBean = new AuthBean();
		authBean.setUser_idx(user_idx);
		authBean.setAuth_key(auth_key);
		
		authDao.addAuthKey(authBean);
		
		// 인증메일 보내기
		MimeMessage message = mailSender.createMimeMessage();
		String mailContent = "<h1>[이메일 인증]</h1><br><p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>"
							 + "<a href='http://52.79.58.253:8080/project01/user/mail/modify_email?"
							 + "user_email=" + user_email + "&auth_key=" + auth_key
							 + "' target='_blank'>이메일 인증 확인</a>";

		try {
			message.setSubject("[LSH-Project01] 이메일 인증 ", "utf-8");
			message.setText(mailContent, "utf-8", "html");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user_email));
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
