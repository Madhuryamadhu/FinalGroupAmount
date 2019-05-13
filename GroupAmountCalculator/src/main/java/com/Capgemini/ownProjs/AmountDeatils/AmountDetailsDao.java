package com.Capgemini.ownProjs.AmountDeatils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Capgemini.ownProjs.Common.ConnectionParam;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AmountDetailsDao {
	private static final Logger logger = LogManager.getLogger();

	public boolean insertAmountDetails(AmountDeatilsBean bean,HttpSession session) {
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

			String nameComma=StringUtils.join(bean.getName(), ",");
			String amountComma=StringUtils.join(bean.getAmount(), ",");
			String reasonComma=	StringUtils.join(bean.getReason(), ",");	

			String userId = new SimpleDateFormat("ddMMyyyyHmmss").format(new java.util.Date());
			if (conn!=null) {
				//insert into `amount_details` (`SEQ_ID`, `NAMES`, `AMOUNTS`, `REASONS`, `CREATE_DATE`, `USER_EMAIL`, `USER_ID`) values('1','Test1,Test2,Test3','30,40,50','-,test,test2','2019-01-30 00:03:58','Paddum105@gmail.com','3');

				Long date=new java.util.Date().getTime();
				String sql = "insert into amount_details (NAMES, AMOUNTS, REASONS, CREATE_DATE, USER_EMAIL, USER_ID, GROUP_NAME) "
						+ "values(?,?,?,?,?,?,?);";
				stmt = conn.prepareStatement(sql);

				stmt.setString(1, nameComma);
				stmt.setString(2, amountComma);
				stmt.setString(3, reasonComma);
				stmt.setDate(4, new Date(date));
				stmt.setString(5, bean.getUserMail());
				stmt.setString(6, userId);
				if(bean.getGroupname()!=null&&bean.getGroupname()!="")
					stmt.setString(7, bean.getGroupname());
				else
					stmt.setString(7, "-");

				int rows = stmt.executeUpdate();
				logger.info("Rows impacted : " + rows );
				if(rows>=1)
				{
					session.setAttribute("USER_ID", userId);
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

	public AmountDeatilsBean getMessagesToDisplay(AmountDeatilsBean bean, HttpSession session) {
		boolean isSuccess=false;
		int result =0;
		int[] numbers =null;
		HashMap<String, Integer> hmap =null;
		try {
			//get name and amount string from db
			if(getNameandAmountandReason(bean)) {

				String[] nameList=bean.getNamesComma().toUpperCase().split(",");
				String[] amtList = bean.getAmountsComma().split(",");

				logger.info("AamountList:------"+Arrays.toString(amtList));

				numbers=new int[amtList.length];
				for(int i = 0;i < amtList.length;i++)
				{
					numbers[i] = Integer.parseInt(amtList[i]);
				}
				for (int i = 0; i <numbers.length; i++) {
					result = result+numbers[i];
				}


				logger.info("Total Amount " + result);
				//Adding totalAMount to show in front end
				bean.setTotalAmount(result+"");


				hmap= new HashMap<String, Integer>();
				for (int j = 0; j <amtList.length; j++) {
					int amt=numbers[j];
					System.out.println(amt);
					if(!(hmap.containsKey(nameList[j])))
					{
						hmap.put(nameList[j], numbers[j]);

					}else{
						hmap.put(nameList[j], hmap.get(nameList[j])+amt);
					}
				}

				logger.info("Total Person:- " + hmap.size());
				bean.setTotalPerson(hmap.size()+"");

				result=result/(hmap.size());
				logger.info("Per head:- " + result);
				bean.setPerHeadAmount(result+"");

				StringBuilder htmlMessage=new StringBuilder();
				htmlMessage.append("<b><div id=\"totAmountDetails\">Total Amount:"+bean.getTotalAmount()+" | Total Person:"+bean.getTotalPerson()+" | Per Head:"+bean.getPerHeadAmount()+"</div></b></br>");

				for (Entry<String, Integer> entry : hmap.entrySet()) {
					if(entry.getValue()>result)
					{
						htmlMessage.append("<font class=\"greenMessage\"><strong>"+entry.getKey() + " have paid "+entry.getValue()+" and should get back " +(entry.getValue()-result)+"!!!"+"</strong></font><br>");
					}else if(entry.getValue()<result)
					{
						htmlMessage.append("<font class=\"redMessage\"><strong>"+entry.getKey() + " have paid "+entry.getValue()+" and should pay " +(result-entry.getValue()) +" more!!!"+"</strong></font><br>");
					}else if(entry.getValue()==result) {
						htmlMessage.append("<font class=\"yellowMessage\"><strong>"+entry.getKey() + " have paid " + entry.getValue() + " that's enough"+"</strong></font><br>");
					}
				}

				bean.setMessageHtml(htmlMessage.toString());			
				logger.info(bean);

			}
		} catch (Exception e) {
		}
		return bean;
	}

	public boolean getNameandAmountandReason(AmountDeatilsBean bean) {
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
				String selectSQL = "SELECT NAMES,AMOUNTS,REASONS FROM amount_details WHERE USER_ID=? AND USER_EMAIL=?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getUserId());
				preparedStatement.setString(2, bean.getUserMail());
				ResultSet rs = preparedStatement.executeQuery();
				logger.info("SQL QUERY:-"+selectSQL);
				while (rs.next()) {
					bean.setNamesComma(rs.getString("NAMES"));
					bean.setAmountsComma(rs.getString("AMOUNTS"));	
					bean.setReasonsComma(rs.getString("REASONS"));	
				}
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


	public AmountDeatilsBean getMessagesInTableToDisplay(AmountDeatilsBean bean, HttpSession session) {
		boolean isSuccess=false;
		int result =0;
		int[] numbers =null;
		HashMap<String, Integer> hmap =null;
		try {
			if(getNameandAmountandReason(bean)) {
				String[] nameList=bean.getNamesComma().toUpperCase().split(",");
				String[] amtList = bean.getAmountsComma().split(",");
				String[] rsnList = bean.getReasonsComma().toUpperCase().split(",");

				logger.info("AamountList:------"+Arrays.toString(amtList));

				numbers=new int[amtList.length];
				for(int i = 0;i < amtList.length;i++)
				{
					numbers[i] = Integer.parseInt(amtList[i]);
				}
				for (int i = 0; i <numbers.length; i++) {
					result = result+numbers[i];
				}


				logger.info("Total Amount " + result);
				//Adding totalAMount to show in front end
				bean.setTotalAmount(result+"");


				hmap= new HashMap<String, Integer>();
				for (int j = 0; j <amtList.length; j++) {
					int amt=numbers[j];
					System.out.println(amt);
					if(!(hmap.containsKey(nameList[j])))
					{
						hmap.put(nameList[j], numbers[j]);

					}else{
						hmap.put(nameList[j], hmap.get(nameList[j])+amt);
					}
				}

				logger.info("Total Person:- " + hmap.size());
				bean.setTotalPerson(hmap.size()+"");

				result=result/(hmap.size());
				logger.info("Per head:- " + result);
				bean.setPerHeadAmount(result+"");



				StringBuilder htmlMessage=new StringBuilder();
				htmlMessage.append("<b><div id=\"totAmountDetails\">Total Amount:"+bean.getTotalAmount()+" | Total Person:"+bean.getTotalPerson()+" | Per Head:"+bean.getPerHeadAmount()+"</div></b></br>");
				htmlMessage.append("<table id=\"amountList\" style=\"width: 100%; height: 5px;\">" + 
						"<thead>" + 
						"<tr>" + 
						"<th>Name</th>" + 
						"<th>Amount</th>" + 
						"<th>Reason</th>" + 
						"</tr>" + 
						"</thead>\r\n" + 
						"<tbody>");

				for(int i = 0;i < amtList.length;i++)
				{

					htmlMessage.append("<tr>" + 
							"<td align=\"center\">"+nameList[i]+"</td>" + 
							"<td align=\"center\">"+amtList[i]+"</td>" + 
							"<td align=\"center\">"+rsnList[i]+"</td>" + 
							"</tr>");

				}

				htmlMessage.append("</tbody></table><br/><br/>");
				bean.setMessageHtml(htmlMessage.toString());			
				logger.info(bean);

			}
		} catch (Exception e) {
		}
		return bean;
	}



	public void createPDF(AmountDeatilsBean bean, HttpSession session, HttpServletResponse response) {
		boolean isSuccess=false;		
		InputStream in = null;
		ServletOutputStream out = null;

		try {
			//PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);


			if(getNameandAmountandReason(bean)) {

				//===========Step1: we need to create PDF and keep it in a folder in our project================
				String[] nameList=bean.getNamesComma().toUpperCase().split(",");
				String[] amtList = bean.getAmountsComma().split(",");
				String[] rsnList = bean.getReasonsComma().toUpperCase().split(",");
				int count=nameList.length;
				String pdfPath=ConnectionParam.PDF_UPLOAD_PATH;
				String pdfName="PDF-"+bean.getUserId();

				logger.info("Pdf Path:-"+pdfPath);
				Document document = new Document();
				PdfPTable table = new PdfPTable(new float[] { 2, 2 ,2 });
				table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell("Name");
				table.addCell("Amount");
				table.addCell("Reason");
				table.setHeaderRows(1);

				PdfPCell[] cells = table.getRow(0).getCells(); 

				for (int j=0;j<cells.length;j++){
					cells[j].setBackgroundColor(new BaseColor(78, 165, 202));
					cells[j].setPadding(10);
				}


				for(int i=0;i<nameList.length;i++) {

					table.addCell(nameList[i]);
					table.addCell(amtList[i]);
					table.addCell(rsnList[i]);


				}
				for (int m = 1; m <=count; m++) {
					PdfPCell[] rowcells =table.getRow(m).getCells(); 
					for (int k=0;k<rowcells.length;k++){
						rowcells[k].setPadding(10);
					}
				}

				File directory = new File(pdfPath);
				if (! directory.exists()){
					logger.info("Directory doesnot exist!! Creating directory:-"+pdfPath);
					directory.mkdir();
				}

				PdfWriter.getInstance(document, new FileOutputStream(pdfPath+"/"+pdfName+".pdf"));
				document.open();
				Paragraph para = new Paragraph("Entered Details" , FontFactory.getFont(FontFactory.HELVETICA, 18,Font.BOLDITALIC, BaseColor.BLACK));

				para.setAlignment(Element.ALIGN_CENTER);
				para.setSpacingAfter(60);
				document.add(para);

				document.add(table);
				document.close();
				logger.info("Pdf File created.File Name:-"+pdfName+".pdf");
				bean.setPdfFullPath(pdfPath+"/"+pdfName+".pdf");
				bean.setPdfFileName(pdfName+".pdf");

			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}catch (DocumentException e2) {
			e2.printStackTrace();
		}catch (Exception e) {
			logger.error(" | Exception Occured "+e.getMessage(), e);
		}finally{
			try {
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}

			} catch (Exception e) {
				logger.error(" Exception Occured "+e.getMessage(), e);
			}catch (Throwable e) {
				logger.error(" Throwable Occured "+e.getMessage(), e);

			}
		}
	}



	public boolean viewhistory(AmountDeatilsBean bean, HttpSession session) {
		boolean isSuccess=false;
		Connection conn = null;
		PreparedStatement stmt = null;
		int result =0;
		int[] numbers =null;
		try {
			final String JDBC_DRIVER = ConnectionParam.DRIVER;
			final String DB_URL = ConnectionParam.URL;
			final String USER = ConnectionParam.USERNAME;
			final String PASS =ConnectionParam.PASSWORD;
			logger.info("Connection parameters::- Driver->"+JDBC_DRIVER+" | URL->"+DB_URL+" | UserName->"+USER+" | Password->"+PASS );

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			if (conn!=null) {
				String selectSQL = "SELECT  GROUP_NAME,NAMES,AMOUNTS,CREATE_DATE,REASONS,USER_ID FROM amount_details WHERE USER_EMAIL=?";
				PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, bean.getUserMail());
				ResultSet rs = preparedStatement.executeQuery();
				logger.info("SQL QUERY:-"+selectSQL);


				StringBuilder htmlMessage=new StringBuilder();

				while (rs.next()) {

					bean.setGroupname(rs.getString("GROUP_NAME"));
					bean.setNamesComma(rs.getString("NAMES"));
					bean.setAmountsComma(rs.getString("AMOUNTS"));
					rs.getDate("CREATE_DATE");
					bean.setReasonsComma(rs.getString("REASONS"));
					bean.setUserId(rs.getString("USER_ID"));

					String pattern = "dd MMM yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date = simpleDateFormat.format(rs.getDate("CREATE_DATE"));
					bean.setCreatedate(date);

					String[] amtList = bean.getAmountsComma().split(",");
					numbers=new int[amtList.length];
					for(int i = 0;i < amtList.length;i++)
					{
						numbers[i] = Integer.parseInt(amtList[i]);
					}
					for (int i = 0; i <numbers.length; i++) {
						result = result+numbers[i];
					}


					logger.info("Total Amount " + result);
					//Adding totalAMount to show in front end
					bean.setTotalAmount(result+"");

					if(bean.getGroupname()==null || bean.getGroupname()=="undefined"||bean.getGroupname()=="" || bean.getGroupname()==" ")
					{
						bean.setGroupname("-");
					}
					htmlMessage.append("<tr>" + 
							"<td align=\"center\">"+bean.getGroupname()+"</td>" + 
							"<td align=\"center\">"+bean.getNamesComma()+"</td>" + 
							"<td align=\"center\">"+bean.getTotalAmount()+"</td>" +
							"<td align=\"center\">"+bean.getCreatedate()+"</td>" +
							"<td><button align=\"center\" onclick=\"viewDetails('"+bean.getUserId()+"');\"><i class=\"fa fa-eye\" aria-hidden=\"true\"></i></button></td>" +
							"<td><button align=\"center\" onclick=\"deleteDetail('"+bean.getUserId()+"');\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></button></td>" +
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

	public boolean deleteAmountDetail(AmountDeatilsBean bean, HttpSession session) {

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
				String deleteSQL = "Delete FROM amount_details WHERE USER_EMAIL=? and USER_ID=?";
				logger.info("SQL:: "+deleteSQL);
				stmt = conn.prepareStatement(deleteSQL);
				stmt.setString(1, bean.getUserMail());
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