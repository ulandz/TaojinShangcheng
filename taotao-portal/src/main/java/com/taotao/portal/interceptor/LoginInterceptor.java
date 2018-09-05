package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.portal.service.impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 在handler执行之前处理
		//判断用户是否登陆
		//从cookie中取token
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		//根据token换取用户i洗脑洗，调用sso系统的接口
		TbUser user = userService.getUserByToken(token);
		//取不到用户信息
		if(null == user) {
			//跳转登录界面，把用户请求url作为参数传递给登陆界面
			response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_PAGE_LOGIN
					+ "?redirect=" + request.getRequestURI());
			return false;
		}
		//返回false
		//取到用户信息，放行
		// 返回值决定handler是否执行， true执行， false不执行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//在handler执行之后， 返回ModelAndView之前处理

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//  返回ModelAndView之后处理

	}

}
