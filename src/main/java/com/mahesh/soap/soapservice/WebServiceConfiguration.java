package com.mahesh.soap.soapservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfiguration {
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}
	
	@Bean
	public XsdSchema coursesSchema(){
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
	
	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema){
		DefaultWsdl11Definition def = new DefaultWsdl11Definition();
		def.setPortTypeName("CoursePort");
		def.setTargetNamespace("http://in28minutes.com/courses");
		def.setLocationUri("/ws");
		def.setSchema(schema);
		return def;
	}

}
