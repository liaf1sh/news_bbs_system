package com.lindong.utils.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class ShiroLogoutFilter extends LogoutFilter {

   /* @Autowired
    private DatabaseRealm databaseRealm;*/

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
        Session session = subject.getSession();
        System.out.println("============"+session.getStartTimestamp()+"========="+session.getTimeout());
        try {
            //清空缓存
            //databaseRealm.clearCached();
            System.out.println("========="+session.getId());
            session.removeAttribute("subject");
            subject.logout();
        } catch (SessionException e) {
            e.printStackTrace();
        }
        issueRedirect(request, response, redirectUrl);

        return false;
    }
}
