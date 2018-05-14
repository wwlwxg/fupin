package com.wwlwxg.springmvc.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

@Controller
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext ctx = null;

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
			SpringContextUtil.ctx = ctx;
	}
	
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

}