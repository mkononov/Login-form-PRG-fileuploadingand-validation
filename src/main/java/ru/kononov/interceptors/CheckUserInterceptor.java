package ru.kononov.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ru.kononov.objects.User;

@Component
public class CheckUserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (request.getRequestURI().contains("check-user")) {
			User user = (User) modelAndView.getModel().get("user");
			if (user == null || !user.getAdmin()) {
				response.sendRedirect(request.getContextPath() + "/failed");
			}
		}
	}
	
}
