package com.Capgemini.ownProjs.controllers;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Capgemini.ownProjs.AmountDeatils.AmountDeatilsBean;
import com.Capgemini.ownProjs.AmountDeatils.AmountDetailsDao;

@Controller
public class AmountController {
	private static final Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/amountSubmit", method = RequestMethod.POST)
	public @ResponseBody AmountDeatilsBean addAmountDetails(@RequestBody AmountDeatilsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside addAmountDetails method:-"+bean.toString());
		AmountDetailsDao dao= new AmountDetailsDao();
		try {
			if(dao.insertAmountDetails(bean,request.getSession())) {
				bean.setStatus("S");
			}else {
				bean.setStatus("F");
			}
		} catch (Exception e) {
		}
		logger.info("returning from addAmountDetails method:-"+bean.toString());
		return bean;
	}


	@RequestMapping(value = "/getMessages", method = RequestMethod.POST)
	public @ResponseBody AmountDeatilsBean getMessages(@RequestBody AmountDeatilsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getMessages method:-"+bean.toString());
		AmountDetailsDao dao= new AmountDetailsDao();
		try {

			bean=dao.getMessagesToDisplay(bean,request.getSession());

		} catch (Exception e) {
		}
		logger.info("returning from getMessagess method:-"+bean.toString());
		return bean;
	}


	@RequestMapping(value = "/getMessagesInTable", method = RequestMethod.POST)
	public @ResponseBody AmountDeatilsBean getMessagesInTable(@RequestBody AmountDeatilsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getMessages method:-"+bean.toString());
		AmountDetailsDao dao= new AmountDetailsDao();
		try {

			bean=dao.getMessagesInTableToDisplay(bean,request.getSession());

		} catch (Exception e) {
		}
		logger.info("returning from getMessagesInTable method:-"+bean.toString());
		return bean;
	}

	@RequestMapping(value = "/createPDF", method = RequestMethod.POST)
	public @ResponseBody AmountDeatilsBean createPDF(@RequestBody AmountDeatilsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside createPDF method:-"+bean.toString());
		AmountDetailsDao dao= new AmountDetailsDao();
		try {
			dao.createPDF(bean,request.getSession(),response);
		} catch (Exception e) {
		}
		logger.info("returning from createPDF method:-"+bean.toString());
		return bean;
	}

	@RequestMapping(value = "/downloadPDF", method = RequestMethod.POST)
	public void downloadPDF(AmountDeatilsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside downloadPDF method:-"+bean.toString());
		InputStream in = null;
		ServletOutputStream out = null;
		try {
			logger.info("Download Started......");
			String file =bean.getPdfFullPath(); 
			in = new BufferedInputStream(new FileInputStream(file)); 
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment; filename="+bean.getPdfFileName()+""); 
			out =response.getOutputStream(); 
			IOUtils.copy(in, out); 
			response.flushBuffer();
			logger.info("Download Ended......");
		} catch (Exception e) {
		}finally{
			try {
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}

			} catch (Exception e) {
				logger.error(" | Exception Occured "+e.getMessage(), e);
			}catch (Throwable e) {
				logger.error(" | Throwable Occured "+e.getMessage(), e);
			}
		}

		logger.info("returning from downloadPDF method:-"+bean.toString());
	}

	@RequestMapping(value = "/viewhistory", method = RequestMethod.POST)
	public @ResponseBody AmountDeatilsBean viewhistory(@RequestBody AmountDeatilsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside addAmountDetails method:-"+bean.toString());
		AmountDetailsDao dao= new AmountDetailsDao();
		try {
			if(dao.viewhistory(bean, request.getSession())) {
				bean.setStatus("S");
			}else {
				bean.setStatus("F");
			}
		} catch (Exception e) {
		}
		logger.info("returning from addAmountDetails method:-"+bean.toString());
		return bean;
	}
	
	
	@RequestMapping(value = "/deleteAmountDetail", method = RequestMethod.POST)
	public @ResponseBody AmountDeatilsBean deleteAmountDetail(@RequestBody AmountDeatilsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside addAmountDetails method:-"+bean.toString());
		AmountDetailsDao dao= new AmountDetailsDao();
		try {
			if(dao.deleteAmountDetail(bean, request.getSession())) {
				bean.setStatus("S");
			}else {
				bean.setStatus("F");
			}
		} catch (Exception e) {
		}
		logger.info("returning from addAmountDetails method:-"+bean.toString());
		return bean;
	}
	

}