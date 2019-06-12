package com.zyqt.platform.config.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * Description:shiro框架 自定义session获取方式
 * 可自定义session获取规则。这里采用ajax请求头authToken携带sessionId的方式
 *
 * @author zhouchen
 * @date 2018-08-27 15:42
 */
public class ShiroSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "authToken" ;

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request" ;

    public ShiroSessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //System.out.println("id：" + id);
        if (StringUtils.isEmpty(id)) {
            //如果没有携带id参数则按照父类的方式在cookie进行获取
            //System.out.println("super：" + super.getSessionId(request, response));
            return super.getSessionId(request, response);
        } else {
            //如果请求头中有 authToken 则其值为sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }
    }
}