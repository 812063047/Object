package com.xzq.bos.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzq.bos.dao.IFunctionDao;
import com.xzq.bos.dao.IUserDao;
import com.xzq.bos.domain.Function;
import com.xzq.bos.domain.User;

public class BOSRealm extends AuthorizingRealm {
	//��֤����
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IFunctionDao functionDao;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//System.out.println("�Զ��巽��������");
		UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
		String username=usernamePasswordToken.getUsername();
		User user=userDao.findUserByUsername(username);
		if(user ==null) {
			return null;
		}
		AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user, user.getPassword(),this.getName());
		return authenticationInfo;
		
		
	}
	
	//��Ȩ����
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		User user=(User) SecurityUtils.getSubject().getPrincipal();
		// ������Ҫ�޸�Ϊ���ݵ�ǰ��¼�û���ѯ���ݿ⣬��ȡʵ�ʶ�Ӧ��Ȩ��
		List<Function> list=null;
		if(user.getUsername().equals("admin")) {
			DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Function.class);
			list=functionDao.findByCriteria(detachedCriteria);
		}else {
			list=functionDao.findFunctionListByUserId(user.getId());
		}
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}
}