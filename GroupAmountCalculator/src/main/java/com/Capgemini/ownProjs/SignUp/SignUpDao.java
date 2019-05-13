package com.Capgemini.ownProjs.SignUp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.logging.log4j.LogManager;

import com.Capgemini.ownProjs.Common.ConnectionParam;

public class SignUpDao {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

	Connection conn = null;
	PreparedStatement stmt = null;

	public boolean signMeUp(SignUpBean bean) {

		////create a table USER_DETAILS columns should be username password and name seqId(pk)
		///Write logic here to insert the data into table
		///in bean data is coming from front end. Use place holders in insert query.And return true if success.
		boolean isSuccess=true;
		logger.info("outside try......");
		try {

			logger.info("inside try......");
			final String JDBC_DRIVER = ConnectionParam.DRIVER;
			final String DB_URL = ConnectionParam.URL;
			final String USER = ConnectionParam.USERNAME;
			final String PASS =ConnectionParam.PASSWORD;


			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			logger.info("Creating statement...");
			String sql = "INSERT INTO USER_DETAILS (USERNAME, PASSWORD, MAIL) VALUES (?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, bean.getName());
			stmt.setString(2, bean.getPassword());
			stmt.setString(3, bean.getEmail());

			int rows = stmt.executeUpdate();
			logger.info("Rows impacted : " + rows );
			if(rows>=1)
			{
				isSuccess=true;
				return isSuccess;
			}else
			{
				isSuccess=false;
				return isSuccess;
			}

		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}
		return isSuccess;
	}


	public boolean signupmailExists(SignUpBean bean) {
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


				String selectSQL = "Select count(*) as signcount from user_details where MAIL= ?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getEmail());
				//ResultSet rs = preparedStatement.executeQuery(selectSQL);   //This was wrong. I was getting syntax error in query. Solved using link-> https://stackoverflow.com/questions/43833033/why-do-i-get-a-syntax-error-for-prepared-statement
				ResultSet rs = preparedStatement.executeQuery(); 
				int numberOfRows = 0;
				if (rs.next()) {
					numberOfRows = rs.getInt("signcount");
					logger.info("numberOfRowsfor email= " + numberOfRows +"signupdao");
				} else {
					logger.info("error: could not get the record counts");
				}
				if (numberOfRows >=1) {
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




}
