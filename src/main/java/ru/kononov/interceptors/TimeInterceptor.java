package ru.kononov.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TimeInterceptor extends HandlerInterceptorAdapter {

	public static final Logger LOGGER = Logger.getLogger(TimeInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("currentTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Long time = (Long) request.getAttribute("currentTime");
		Thread.sleep(500);
		LOGGER.info(System.currentTimeMillis() - time);
	}
	
}
