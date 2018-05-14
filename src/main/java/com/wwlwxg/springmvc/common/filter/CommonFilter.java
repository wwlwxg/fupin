package com.wwlwxg.springmvc.common.filter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wwlwxg.springmvc.common.bean.SysLog;
import com.wwlwxg.springmvc.common.bean.SysUser;
import com.wwlwxg.springmvc.common.util.Constants;
import com.wwlwxg.springmvc.common.util.UrlUtil;

public class CommonFilter implements  HandlerInterceptor {
	protected Log log = LogFactory.getLog(getClass());
	private static final String DEFAULT_CHARSET = "UTF-8";
	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
	 * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//记录url日志
		String IP = request.getHeader("x-forwarded-for"); 
		if (request.getHeader("x-forwarded-for") == null) {
			 IP =  request.getRemoteAddr();  
		}
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		Long loginUser = null;
		if(sysUser!=null)loginUser =sysUser.getId().longValue();
		String agent = request.getHeader("User-Agent").toLowerCase();
		if(agent.indexOf("msie 6")>0||agent.indexOf("msie 7")>0||agent.indexOf("msie 8")>0||agent.indexOf("msie 9")>0){
			response.sendRedirect("browser.jsp"); 
		}
		request.setCharacterEncoding(DEFAULT_CHARSET);//post提交使用UTF-编码
		response.setCharacterEncoding(DEFAULT_CHARSET);//post返回使用UTF-编码
		response.setContentType("text/html;charset="+DEFAULT_CHARSET);//json返回使用utf-8格式
		//这一行影响编码
		log.info(JSONObject.toJSONString(new SysLog(UrlUtil.getUrl(request),UrlUtil.getUrlPara(request),loginUser,IP,new Date())));
		return true;
	}
	/**
	 * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
	 * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操
	 * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像，
	 * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
	 * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setCharacterEncoding(DEFAULT_CHARSET);//post提交使用UTF-编码
		response.setCharacterEncoding(DEFAULT_CHARSET);//post返回使用UTF-编码
		response.setContentType("text/html;charset="+DEFAULT_CHARSET);//json返回使用utf-8格式
	}

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
	 * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)throws Exception {
	}
}