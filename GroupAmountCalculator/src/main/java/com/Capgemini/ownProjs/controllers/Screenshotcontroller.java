package com.Capgemini.ownProjs.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Capgemini.ownProjs.AmountDeatils.AmountDeatilsBean;
import com.Capgemini.ownProjs.Login.LoginBean;
import com.Capgemini.ownProjs.Login.LoginDao;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@Controller
public class Screenshotcontroller {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
  
  @RequestMapping(value = "/screenshotofAmmountsummary", method = RequestMethod.POST)
  public @ResponseBody AmountDeatilsBean screenshotofAmmountsummary(@RequestBody AmountDeatilsBean bean,HttpServletRequest request, HttpServletResponse response) {
      logger.info(bean.toString());
      logger.info(request.toString());
	  logger.debug("yess:::::::::::::::::::::::::::::::::");
	  
	  
	  
	  try {
		  
		  String format = "jpg";
          String fileName = "AmountSummary." + format;
          
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, format , new File("C:\\Users\\mn143890\\Downloads\\"+fileName));
		   
	} catch (HeadlessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return bean;
  }
  
  
  
  @RequestMapping(value = "/screenshotofAmmountDetails", method = RequestMethod.POST)
  public @ResponseBody AmountDeatilsBean screenshotofAmmountDetails(@RequestBody AmountDeatilsBean bean,HttpServletRequest request, HttpServletResponse response) {
      logger.info(bean.toString());
      logger.info(request.toString());
	  logger.debug("yess:::::::::::::::::::::::::::::::::");
	  
	  
	  
	  try {
		  
		  String format = "jpg";
          String fileName = "AmountDetails." + format;
          
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, format , new File("C:\\Users\\mn143890\\Downloads\\"+fileName));
		   
	} catch (HeadlessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return bean;
  }
 
  
 
  
}