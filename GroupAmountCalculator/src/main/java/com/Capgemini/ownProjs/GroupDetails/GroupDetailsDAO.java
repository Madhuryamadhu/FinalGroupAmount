package com.Capgemini.ownProjs.GroupDetails;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Capgemini.ownProjs.AmountDeatils.AmountDeatilsBean;
import com.Capgemini.ownProjs.Common.ConnectionParam;
import com.Capgemini.ownProjs.Login.LoginBean;

public class GroupDetailsDAO {

	private static final Logger logger = LogManager.getLogger();

	public boolean createGroup(GroupDetailsBean bean) {
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
				//INSERT INTO group_details (GROUP_NAME,GROUP_MEMBERS,USER_EMAIL,USER_ID,COUNT) VALUES ('TEST_GROUP_2','Pradeep,mahesh,ramesh,umesh','p@p.com','9980835293',0);

				Long date=new java.util.Date().getTime();
				String sql = "INSERT INTO group_details (GROUP_NAME,GROUP_MEMBERS,USER_EMAIL,USER_ID,COUNT_VALUE,CREATE_DATE) VALUES (?,?,?,?,?,?);";
				stmt = conn.prepareStatement(sql);

				stmt.setString(1, bean.getGroupName());
				stmt.setString(2, bean.getGroupMembers());
				stmt.setString(3, bean.getUserEmail());
				stmt.setString(4, bean.getUserId());
				stmt.setInt(5, 0);
				stmt.setDate(6, new Date(date));

				int rows = stmt.executeUpdate();
				logger.info("Rows impacted : " + rows );
				if(rows>=1)
				{
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

	public boolean loadGroupNames(GroupDetailsBean bean) {
		boolean isSuccess=false;
		Connection conn = null;
		PreparedStatement stmt = null;
		HashMap<String, String> groupNameMap=new  HashMap<String, String>();
		StringBuilder options=null;
		try {
			final String JDBC_DRIVER = ConnectionParam.DRIVER;
			final String DB_URL = ConnectionParam.URL;
			final String USER = ConnectionParam.USERNAME;
			final String PASS =ConnectionParam.PASSWORD;
			logger.info("Connection parameters::- Driver->"+JDBC_DRIVER+" | URL->"+DB_URL+" | UserName->"+USER+" | Password->"+PASS );

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);


			if (conn!=null) {
				String selectSQL = "SELECT GROUP_NAME,GROUP_ID FROM group_details WHERE USER_EMAIL=?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getUserEmail());
				ResultSet rs = preparedStatement.executeQuery();
				logger.info("SQL QUERY:-"+selectSQL);
				while (rs.next()) {
					//bean.setGroupName(rs.getString("GROUP_NAME"));
					groupNameMap.put(rs.getInt("GROUP_ID")+"", rs.getString("GROUP_NAME"));
				}
				
				if(groupNameMap.size()>0) {
					options=new StringBuilder();
					options.append("<option value=''>Select</option>");
					for (Map.Entry<String, String> entry : groupNameMap.entrySet()) {
						options.append("<option value=\""+entry.getKey()+"\">"+entry.getValue()+"</option>");
					}
					
				}
				if(options!=null) {
					bean.setDropDownOptions(options.toString());
				}
				isSuccess=true;

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

	public boolean loadGroupMembers(GroupDetailsBean bean) {
		boolean isSuccess=false;
		Connection conn = null;
		PreparedStatement stmt = null;
		StringBuilder options=null;
		String groupMembers="";
		try {
			final String JDBC_DRIVER = ConnectionParam.DRIVER;
			final String DB_URL = ConnectionParam.URL;
			final String USER = ConnectionParam.USERNAME;
			final String PASS =ConnectionParam.PASSWORD;
			logger.info("Connection parameters::- Driver->"+JDBC_DRIVER+" | URL->"+DB_URL+" | UserName->"+USER+" | Password->"+PASS );

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);


			if (conn!=null) {
				String selectSQL = "SELECT GROUP_MEMBERS FROM group_details WHERE GROUP_ID = ?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setInt(1, Integer.parseInt(bean.getGroupId()));
				ResultSet rs = preparedStatement.executeQuery();
				logger.info("SQL QUERY:-"+selectSQL);
				while (rs.next()) {
					groupMembers=rs.getString("GROUP_MEMBERS");
				}
				String[] groupmemb =   groupMembers.split(",");
				options=new StringBuilder();
				options.append("<option value=''>Select</option>");
				for (int i = 0; i < groupmemb.length; i++) {
					
					options.append("<option value=\""+groupmemb[i]+"\">"+groupmemb[i]+"</option>");
				}
				if(options!=null) {
					bean.setDropDownOptions(options.toString());
				}
				isSuccess=true;

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
	
	
	public boolean viewgroup(GroupDetailsBean bean, HttpSession session) {
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
				String selectSQL = "SELECT  GROUP_NAME,GROUP_MEMBERS,USER_ID,CREATE_DATE FROM group_details WHERE USER_EMAIL=?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getUserEmail());
				ResultSet rs = preparedStatement.executeQuery();
				logger.info("SQL QUERY:-"+selectSQL);


				StringBuilder htmlMessage=new StringBuilder();

				while (rs.next()) {

					bean.setGroupName(rs.getString("GROUP_NAME"));
					bean.setGroupMembers(rs.getString("GROUP_MEMBERS"));
					bean.setUserId(rs.getString("USER_ID"));
					String pattern = "dd MMM yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date = simpleDateFormat.format(rs.getDate("CREATE_DATE"));
					bean.setCreateDate(date);

					
			
					htmlMessage.append("<tr>" + 
							"<td align=\"center\">"+bean.getGroupName()+"</td>" + 
							"<td align=\"center\">"+bean.getGroupMembers()+"</td>" +
							"<td align=\"center\">"+bean.getCreateDate()+"</td>" +
							"<td><button align=\"center\" onclick=\"modifyGroup('"+bean.getUserId()+"');\"><i class=\"fas fa-edit\" area-hidden=\"true\"></i></button></td>" +
							"<td><button align=\"center\" onclick=\"deletegroup('"+bean.getUserId()+"');\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></button></td>" +
							"</tr>");

				}
				bean.setMessageHtml(htmlMessage.toString());

				isSuccess=true;
			}else {
				logger.info("Connection Establishment failed!!!");
			}

		}catch(SQLException se){
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
	
	public boolean modifyGroup(GroupDetailsBean bean,  HttpSession session) {
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
			if (conn!=null) {
				logger.info("Connection Establishment success!!!");

				
				String selectSQL = "UPDATE group_details SET GROUP_NAME=? ,GROUP_MEMBERS=? where USER_ID= ? AND USER_EMAIL=?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getGroupName());
				preparedStatement.setString(2, bean.getGroupMembers());
				preparedStatement.setString(3, bean.getUserId());
				preparedStatement.setString(4, bean.getUserEmail());
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

	public void getGroupDataForModify(GroupDetailsBean bean, HttpSession session) {
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
				String selectSQL = "SELECT  GROUP_NAME,GROUP_MEMBERS FROM group_details WHERE USER_ID=? and USER_EMAIL=?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getUserId());
				preparedStatement.setString(2, bean.getUserEmail());
				ResultSet rs = preparedStatement.executeQuery();
				logger.info("SQL QUERY:-"+selectSQL);
				while (rs.next()) {
					bean.setGroupName(rs.getString("GROUP_NAME"));
					bean.setGroupMembers(rs.getString("GROUP_MEMBERS"));
				}
			}else {
				logger.info("Connection Establishment failed!!!");
			}

		}catch(SQLException se){
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
	}

	public boolean deleteGroup(GroupDetailsBean bean, HttpSession session) {

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
				String deleteSQL = "Delete FROM group_details WHERE USER_EMAIL=? and USER_ID=?";
				logger.info("SQL:: "+deleteSQL);
				stmt = conn.prepareStatement(deleteSQL);
				stmt.setString(1, bean.getUserEmail());
				stmt.setString(2, bean.getUserId());

				// execute delete SQL stetement
				int i=stmt.executeUpdate();
				if(i>0)
					isSuccess=true;
			}else {
				logger.info("Connection Establishment failed!!!");
			}

		}catch(SQLException se){
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