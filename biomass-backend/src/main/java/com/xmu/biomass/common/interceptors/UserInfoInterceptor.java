package com.xmu.biomass.common.interceptors;

import com.xmu.biomass.common.anotations.IgnoreAuth;
import com.xmu.biomass.common.context.UserContext;
import com.xmu.biomass.common.utils.JwtUtl;
import com.xmu.biomass.sys.user.entity.SysUser;
import com.xmu.biomass.sys.user.service.SysUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/3/26  下午3:36
 */
@Component
@RequiredArgsConstructor
public class UserInfoInterceptor implements HandlerInterceptor {

    private final JwtUtl jwtUtl;
    private final SysUserService sysUserService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-Token");
        if(!Objects.isNull(token)){
            if(handler instanceof HandlerMethod handlerMethod){
                IgnoreAuth ignoreAuth = handlerMethod.getMethodAnnotation(IgnoreAuth.class);
                if(Objects.isNull(ignoreAuth)){
                    String subject = jwtUtl.parseToken(token);
                    SysUser sysUser = sysUserService.findUserById(Integer.valueOf(subject));
                    UserContext.setUser(sysUser);
                }
            }

        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
