package com.Capgemini.ownProjs.controllers;

import java.awt.Window;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Capgemini.ownProjs.Common.MailSender;
import com.Capgemini.ownProjs.Login.LoginBean;
import com.Capgemini.ownProjs.Login.LoginDao;
import com.Capgemini.ownProjs.SignUp.SignUpBean;
import com.Capgemini.ownProjs.SignUp.SignUpDao;

@Controller
public class SignUpLoginController {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody SignUpBean signup(@RequestBody SignUpBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside signUp method:-"+bean.toString());
		SignUpDao dao= new SignUpDao();
		try {
			

			if(dao.signupmailExists(bean)) {
				bean.setEmailstatus("S");   //success
				logger.info("Mail exists!!!");
			}else {
				bean.setEmailstatus("F");   //failure
				logger.info("Mail not exists!!!");
				
				if(dao.signMeUp(bean)) {
					bean.setStatus("S");   //success
					
					logger.info("Sign Up success!!!");
				}else {
					bean.setStatus("F");   //failure
					logger.info("Sign Up failure!!!");
				}
			}
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning from signUp method:-"+bean.toString());
		return bean;
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody LoginBean login(@RequestBody LoginBean bean,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("Inside login method:-"+bean.toString());
		LoginDao dao= new LoginDao();
		try {
			
			if(dao.LoginMailChecking(bean)) {
				bean.setLoginmailstatus("S");   //success
				logger.info("Mail ID check is successfully completed.Proceeding to validate mail ID and password..");
				if(dao.LoginMailAndPasswordChecking(bean)) {
					bean.setLoginmailandpasswordstatus("S");   //success
					logger.info("Mail Id and Password check is Successfull");
					
					String userId = new SimpleDateFormat("ddMMyyyyHmmss").format(new java.util.Date());
					logger.info("Adding USER_ID:-"+userId+" and MAIL_ID:-"+bean.getLoginemail()+" to session");
					session.setAttribute("MAIL", bean.getLoginemail());
					session.setAttribute("USER_ID", userId);
				}else {
					bean.setLoginmailandpasswordstatus("F");   //failure
					logger.info("Mail Id and Password check failed");
				}
			}else {
				bean.setLoginmailstatus("F");   //failure
				logger.info("Mail Id is not valid");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning from login method:-"+bean.toString());
		return bean;
	}
	
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public @ResponseBody LoginBean logout(@RequestBody LoginBean bean,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("Inside login method:-"+bean.toString());
		
		try {
			
			
			session.removeAttribute("MAIL");
			session.removeAttribute("USER_ID");
			 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning from login method:-"+bean.toString());
		return bean;
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public @ResponseBody LoginBean mail(@RequestBody LoginBean bean,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("Inside login method:-"+bean.toString());
		LoginDao dao= new LoginDao();
		try {
			if(dao.reset(bean)) {
				bean.setLoginmailstatus("S");   //success
				logger.info("S");
			}else {
				bean.setLoginmailstatus("F");   //failure
				logger.info("F");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning from login method:-"+bean.toString());
		return bean;
	}
	@RequestMapping(value = "/forgotpass", method = RequestMethod.POST)
	public @ResponseBody LoginBean forgotpass(@RequestBody LoginBean bean,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("Inside login method:-"+bean.toString());
		LoginDao dao= new LoginDao();
		MailSender mailsender= new MailSender();
		try {
			
			if(dao.LoginMailChecking(bean)) {
				if (mailsender.sendEmail(bean.getLoginemail())) {
					bean.setLoginmailstatus("S");
				} else {
					bean.setLoginmailstatus("F"); //failure
				} 
			}else {
				bean.setLoginmailstatus("N");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning from login method:-"+bean.toString());
		return bean;
	}
	
}