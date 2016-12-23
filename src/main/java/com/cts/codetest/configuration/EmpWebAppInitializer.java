package com.cts.codetest.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class EmpWebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.getEnvironment().setActiveProfiles("prod");
	      appContext.setConfigLocation("classpath:core-context.xml");

	      servletContext.addListener(new ContextLoaderListener(appContext));
	        servletContext.addListener(RequestContextListener.class);
	        
	      ServletRegistration.Dynamic dispatcher =
	    		  servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
	      dispatcher.setLoadOnStartup(1);
	      dispatcher.addMapping("/");	
	      
	      FilterRegistration.Dynamic logFilter = servletContext.addFilter( "logFilter", DelegatingFilterProxy.class );
	        logFilter.setAsyncSupported(true);
	        logFilter.addMappingForServletNames( EnumSet.of(DispatcherType.REQUEST), true, "dispatcher" );
	 
	}


}
