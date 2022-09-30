package es.library.databaseinspanish.api.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaticApplicationContext implements ApplicationContextAware{

	private static ApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}

	public static ApplicationContext getContext(){
		return applicationContext;
	}

}