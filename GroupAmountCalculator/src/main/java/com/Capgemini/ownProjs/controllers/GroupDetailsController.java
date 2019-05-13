package com.Capgemini.ownProjs.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Capgemini.ownProjs.AmountDeatils.AmountDeatilsBean;
import com.Capgemini.ownProjs.AmountDeatils.AmountDetailsDao;
import com.Capgemini.ownProjs.GroupDetails.GroupDetailsBean;
import com.Capgemini.ownProjs.GroupDetails.GroupDetailsDAO;

@Controller
public class GroupDetailsController {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	
	@RequestMapping(value = "/createGroup", method = RequestMethod.POST)
	public @ResponseBody GroupDetailsBean createGroup(@RequestBody GroupDetailsBean bean,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("In createGroup :-"+bean.toString());
		GroupDetailsDAO dao= new GroupDetailsDAO();
		try {
			session.setAttribute("USER_ID", bean.getUserId());
              if(dao.createGroup(bean)) {
            	  bean.setStatus("S");
              }else {
            	  bean.setStatus("F");
              }
  			
              
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning from createGroup :-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/loadGroupNames", method = RequestMethod.POST)
	public @ResponseBody GroupDetailsBean loadGroupNames(@RequestBody GroupDetailsBean bean,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("In loadGroupNames :-"+bean.toString());
		GroupDetailsDAO dao= new GroupDetailsDAO();
		try {
              if(dao.loadGroupNames(bean)) {
            	  bean.setStatus("S");
              }else {
            	  bean.setStatus("F");
              }
              
             
              
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning from loadGroupNames :-"+bean.toString());
		return bean;
	}

	@RequestMapping(value = "/loadGroupMembers", method = RequestMethod.POST)
	public @ResponseBody GroupDetailsBean loadGroupMembers(@RequestBody GroupDetailsBean bean,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("In loadGroupMembers :-"+bean.toString());
		GroupDetailsDAO dao= new GroupDetailsDAO();
		try {
              if(dao.loadGroupMembers(bean)) {
            	  bean.setStatus("S");
              }else {
            	  bean.setStatus("F");
              }
              
             
              
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning from loadGroupNames :-"+bean.toString());
		return bean;
	}

	
	
	@RequestMapping(value = "/viewgroup", method = RequestMethod.POST)
	public @ResponseBody GroupDetailsBean viewgroup(@RequestBody GroupDetailsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside addAmountDetails method:-"+bean.toString());
		GroupDetailsDAO dao= new GroupDetailsDAO();
		try {
			if(dao.viewgroup(bean, request.getSession())) {
				bean.setStatus("S");
			}else {
				bean.setStatus("F");
			}
		} catch (Exception e) {
		}
		logger.info("returning from addAmountDetails method:-"+bean.toString());
		
		return bean;
	}
	
	@RequestMapping(value = "/getGroupDataForModify", method = RequestMethod.POST)
	public @ResponseBody GroupDetailsBean getGroupDataForModify(@RequestBody GroupDetailsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getGroupDataForModify method:-"+bean.toString());
		GroupDetailsDAO dao= new GroupDetailsDAO();
		try {
			dao.getGroupDataForModify(bean, request.getSession());
		} catch (Exception e) {
		}
		logger.info("returning from getGroupDataForModify method:-"+bean.toString());
		
		return bean;
	}
	
	@RequestMapping(value = "/modifyGroup", method = RequestMethod.POST)
	public @ResponseBody GroupDetailsBean modifyGroup(@RequestBody GroupDetailsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside modifyGroup method:-"+bean.toString());
		GroupDetailsDAO dao= new GroupDetailsDAO();
		try {
			if(dao.modifyGroup(bean, request.getSession())) {
				bean.setStatus("S");
			}else {
				bean.setStatus("F");
			}
		} catch (Exception e) {
		}
		logger.info("returning from modifyGroup method:-"+bean.toString());
		
		return bean;
	}
	
	
	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
	public @ResponseBody GroupDetailsBean deleteGroup(@RequestBody GroupDetailsBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside deleteGroup method:-"+bean.toString());
		GroupDetailsDAO dao= new GroupDetailsDAO();
		try {
			if(dao.deleteGroup(bean, request.getSession())) {
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