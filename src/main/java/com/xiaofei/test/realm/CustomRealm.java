package com.xiaofei.test.realm;

import com.xiaofei.test.mapper.PrivMapper;
import com.xiaofei.test.mapper.RoleMapper;
import com.xiaofei.test.mapper.StaffMapper;
import com.xiaofei.test.model.StaffDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PrivMapper privMapper;

    @Autowired
    private StaffMapper staffMapper;

    {
        super.setName("CustomRealm");
    }
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
       //根据用户名查询角色
        Set<String> roleNames = roleMapper.queryRoleNameByStaffCode(userName);
        log.info("{}拥有的角色:{}",userName,roleNames);
        //根据用户名查询权限
        Set<String> permissionList = privMapper.queryPrivUrlByStaffCode(userName);
        log.info("{}拥有的权限:{}",userName,permissionList);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNames);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        //通过用户名查询用户信息
        StaffDO staffDO = staffMapper.queryByStaffCode(userName);
        log.info("用户信息:{}",staffDO);
        if (staffDO==null){
            log.warn("用户名错误");
            return null;
        }
        String staffDOPassword = staffDO.getPassword();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(staffDO.getUserName(), staffDOPassword,this.getName());
        return info;
    }
}
