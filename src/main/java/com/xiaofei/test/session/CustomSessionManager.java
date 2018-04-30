package com.xiaofei.test.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import java.io.Serializable;

public class CustomSessionManager extends DefaultWebSessionManager {

    /**
     * 重写获取session的方法
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        Session session = null;
        if (sessionKey instanceof WebSessionKey){
             request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if (request !=null && sessionId !=null){
            session  = (Session)request.getAttribute(sessionId.toString());
            if (session == null){
                session =  super.retrieveSession(sessionKey);
                request.setAttribute(sessionId.toString(),session);
            }
            return session;
        }
        return super.retrieveSession(sessionKey);
    }
}
