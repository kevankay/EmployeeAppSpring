package com.capgemini.employeeapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebServletConfiguration implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//Creating an object of container (WebApplicationContext)
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		
		//Specify Spring MVC configuration class.
		context.register(SpringMvcConfig.class);
		
		context.setServletContext(servletContext);
		
		//Configuring DispatcherServlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

}