package com.Capgemini.ownProjs.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;

import com.Capgemini.ownProjs.SignUp.SignUpBean;
import com.Capgemini.ownProjs.Common.ConnectionParam;
import com.Capgemini.ownProjs.Common.MailSender;
import com.Capgemini.ownProjs.Login.LoginBean;

public class LoginDao {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	String password="";

	public boolean LoginMailChecking(LoginBean bean) {
		boolean isSuccess=false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			final String JDBC_DRIVER = ConnectionParam.DRIVER;
			final String DB_URL = ConnectionParam.URL;
			final String USER = ConnectionParam.USERNAME;
			final String PASS =ConnectionParam.PASSWORD;
			logger.info("Connection parameters::- Driver->"+JDBC_DRIVER+" | URL->"+DB_URL+" | UserName->"+USER+" | Password->"+PASS );

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			if (conn!=null) {
				logger.info("Connection Establishment success!!!");
				logger.info("Cheking if the entered mail id "+bean.getLoginemail()+" is valid");
				
				String selectSQL = "Select count(*)as count from user_details where MAIL= ?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getLoginemail());
				ResultSet rs = preparedStatement.executeQuery(); 
				int numberOfRows = 0;
				if (rs.next()) {
					numberOfRows = rs.getInt("count");
				}
				if (numberOfRows >= 1) {
					isSuccess = true;
					logger.info("Mail Id present in the DB");
				}else {
					logger.info("Mail Id not present in the DB");
				}
			}else {
				logger.info("Connection Establishment failed!!!");
			}

		} catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return isSuccess;
	}

	
	
	public boolean LoginMailAndPasswordChecking(LoginBean bean) {
		boolean isSuccess=false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			final String JDBC_DRIVER = ConnectionParam.DRIVER;
			final String DB_URL = ConnectionParam.URL;
			final String USER = ConnectionParam.USERNAME;
			final String PASS =ConnectionParam.PASSWORD;
			logger.info("Connection parameters::- Driver->"+JDBC_DRIVER+" | URL->"+DB_URL+" | UserName->"+USER+" | Password->"+PASS );

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			if (conn!=null) {
				logger.info("Connection Establishment success!!!");

				
				String selectSQL = "Select count(*) as count  from user_details where MAIL= ? and PASSWORD= ?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getLoginemail());
				preparedStatement.setString(2, bean.getLoginpassword());
				ResultSet rs = preparedStatement.executeQuery(); 
				int numberOfRows = 0;
				
				if (rs.next()) {
					numberOfRows = rs.getInt("count");
				} 
				
				if (numberOfRows >= 1) {
					isSuccess = true;
				} 
			}else {
				logger.info("Connection Establishment failed!!!");
			}

		} catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return isSuccess;
	}
	
	
	public boolean reset(LoginBean bean) {
		boolean isSuccess=false;
		Connection conn = null;
		PreparedStatement stmt = null;
		String Password="";
		try {
			final String JDBC_DRIVER = ConnectionParam.DRIVER;
			final String DB_URL = ConnectionParam.URL;
			final String USER = ConnectionParam.USERNAME;
			final String PASS =ConnectionParam.PASSWORD;
			logger.info("Connection parameters::- Driver->"+JDBC_DRIVER+" | URL->"+DB_URL+" | UserName->"+USER+" | Password->"+PASS );

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			/*UPDATE Customers
			SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
			WHERE CustomerID = 1;*/
			if (conn!=null) {
				logger.info("Connection Establishment success!!!");

				
				String selectSQL = "UPDATE user_details SET PASSWORD = ? where MAIL= ?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getLoginpassword());
				preparedStatement.setString(2, bean.getLoginemail());
				int rs=preparedStatement.executeUpdate(); 
				if (rs == 1) {
					isSuccess = true;
				} else
				{
					isSuccess = false;
				}
				
			}else {
				logger.info("Connection Establishment failed!!!");
			}

		} catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return isSuccess;
	}

	public boolean password(LoginBean bean) {
		boolean isSuccess=false;
		Connection conn = null;
		PreparedStatement stmt = null;
	
		try {
			final String JDBC_DRIVER = ConnectionParam.DRIVER;
			final String DB_URL = ConnectionParam.URL;
			final String USER = ConnectionParam.USERNAME;
			final String PASS =ConnectionParam.PASSWORD;
			logger.info("Connection parameters::- Driver->"+JDBC_DRIVER+" | URL->"+DB_URL+" | UserName->"+USER+" | Password->"+PASS );

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			if (conn!=null) {
				logger.info("Connection Establishment success!!!");

				
				String selectSQL = "Select PASSWORD from user_details where MAIL= ?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getLoginemail());
				ResultSet rs = preparedStatement.executeQuery(); 
				while (rs.next()) {
					password=rs.getString("PASSWORD");
					isSuccess=true;
				}
				
			}else {
				logger.info("Connection Establishment failed!!!");
			}

		} catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return isSuccess;
	}

	
	public boolean forgotPass(LoginBean bean) {
		boolean isSuccess=false;	
		if(LoginMailChecking(bean))
		{
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.debug", "true");
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress("paddum19@gmail.com"));
				message.setRecipient(RecipientType.TO, new InternetAddress(bean.getLoginemail()));
				message.setSubject("Old Password");
				 if(password(bean))
				 {
				String body = "Hi,</br></br><p>Your Old Password is '" + password + "'  ,Please reset your password as soon as u login.</p></br></br> Thanks and Regards,</br>GroupAmountCalculator TEAM ";
				message.setText(body, "UTF-8", "html"); 
				message.setSentDate(new Date());
				Transport.send(message);
				isSuccess=true;	
				 }
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return isSuccess;
	}
}
