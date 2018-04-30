package com.xiaofei.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {

    SimpleAccountRealm simpleAccount = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccount.addAccount("xiaofei", "123456","admin");
    }

    @Test
    public void test1() {
        //1.创建securitymanager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccount);
        //主体提交
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("xiaofei", "123456");

        //登入认证
        subject.login(token);
        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        //授权检验
        subject.checkRole("admin");
    }
}
