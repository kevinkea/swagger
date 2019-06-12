package com.zyqt.platform.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Profile({"dev", "pro", "test"})// 设置 pro 环境开启
public class ShiroConfig {

	/**
	 * 项目根路径
	 */
	@Value("${server.servlet.path}")
	private String projectRootPath;
	@Value("${management.context-path}")
	private String managementContextpath;
	@Value("${swaggger.Permission}")
	private String swagggerPermission;
	@Value("${swaggger.role}")
	private String swagggerRole;

	/**
	 * 过滤器默认权限表 {anon=anon, authc=authc, authcBasic=authcBasic, logout=logout,
	 * noSessionCreation=noSessionCreation, perms=perms, port=port,
	 * rest=rest, roles=roles, ssl=ssl, user=user}
	 * <p>
	 * anon, authc, authcBasic, user 是第一组认证过滤器
	 * perms, port, rest, roles, ssl 是第二组授权过滤器
	 * <p>
	 * user 和 authc 的不同：当应用开启了rememberMe时, 用户下次访问时可以是一个user, 但绝不会是authc,
	 * 因为authc是需要重新认证的, user表示用户不一定已通过认证, 只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe
	 * 以前的一个用户登录时开启了rememberMe, 然后他关闭浏览器, 下次再访问时他就是一个user, 而不会authc
	 * +",roles["+swagggerRole+"]"
	 *
	 * @param securityManager 初始化 ShiroFilterFactoryBean 的时候需要注入 SecurityManager
	 */
//    @Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 设置拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		//配置记住我或认证通过可以访问的地址
//        filterChainDefinitionMap.put("/**", "user");
		//开放权限flowCallBack
		filterChainDefinitionMap.put(projectRootPath + "/static/**", "anon");
		filterChainDefinitionMap.put(projectRootPath + "/login", "anon");
		filterChainDefinitionMap.put(projectRootPath + "/debug/login", "anon");
		filterChainDefinitionMap.put(projectRootPath + "/" + managementContextpath + "/**", "anon");
		filterChainDefinitionMap.put(projectRootPath + "/sys/sysUserController/userInfo", "anon");
		filterChainDefinitionMap.put(projectRootPath + "/sys/sysUserController/version", "anon");
		filterChainDefinitionMap.put(projectRootPath + "/sys/sysUserController/isLeaderVersion", "anon");
		filterChainDefinitionMap.put(projectRootPath + "/sys/QrCodeLoginController/**", "anon");
		//swagger2 只有admin有查询权限
//        filterChainDefinitionMap.put(projectRootPath+"/swagger-ui.html",swagggerPermission);
//        filterChainDefinitionMap.put(projectRootPath+"/doc.html",swagggerPermission+",roles["+swagggerRole+"]");
//        filterChainDefinitionMap.put(projectRootPath+"/swagger/**",swagggerPermission+",roles["+swagggerRole+"]");
//        filterChainDefinitionMap.put(projectRootPath+"/webjars/**", swagggerPermission+",roles["+swagggerRole+"]");
//        filterChainDefinitionMap.put(projectRootPath+"/swagger-resources/**",swagggerPermission+",roles["+swagggerRole+"]");
//        filterChainDefinitionMap.put(projectRootPath+"/v2/**",swagggerPermission+",roles["+swagggerRole+"]");
		//配置退出
		filterChainDefinitionMap.put(projectRootPath + "/logout", "logout");
		//其余接口一律拦截，如果没有登录会跳到登录页面,admin开放所有权限
//        filterChainDefinitionMap.put(projectRootPath+"/**",swagggerPermission+",roles["+swagggerRole+"]");
		filterChainDefinitionMap.put("/**", "user");
		//需要登录跳转
		shiroFilterFactoryBean.setLoginUrl(projectRootPath + "/needLogin");
//        shiroFilterFactoryBean.setLoginUrl("/#/login");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		System.out.println("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}

	/**
	 * cookie对象;
	 * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
	 *
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		//System.out.println("ShiroConfiguration.rememberMeCookie()");
		//这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberme");
		//<!-- 记住我cookie生效时间7天 ,单位秒;-->
		simpleCookie.setMaxAge(7 * 24 * 60 * 60);
		return simpleCookie;
	}

	/**
	 * cookie管理对象;
	 * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
	 *
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		//System.out.println("ShiroConfiguration.rememberMeManager()");
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		//rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		cookieRememberMeManager.setCipherKey(Base64.decode("2AvHhdsgUs0F0A3SDFAPa*=="));
		return cookieRememberMeManager;
	}

	/**
	 * 密码校验规则HashedCredentialsMatcher
	 * 这个类是为了对密码进行编码的 ,
	 * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
	 * 这个类也负责对form里输入的密码进行编码
	 * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
	 */
	@Bean("hashedCredentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		//指定加密方式为MD5
		credentialsMatcher.setHashAlgorithmName("MD5");
		//加密次数
		credentialsMatcher.setHashIterations(1024);
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		System.out.println("密码匹配处理器注入成功");
		return credentialsMatcher;
	}

	/**
	 * 自定义authRealm
	 *
	 * @return
	 */
	@Bean("authRealm")
	public CustomRealm authRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
		System.out.println("自定义身份验证注入1");
		CustomRealm authRealm = new CustomRealm();
		authRealm.setAuthorizationCachingEnabled(false);
		authRealm.setCredentialsMatcher(matcher);
		authRealm.setCacheManager(new MemoryConstrainedCacheManager()); // 开启内存缓存
		System.out.println("自定义身份验证注入2");
		return authRealm;
	}

	/**
	 * 自定义sessionManager
	 *
	 * @return
	 */
	@Bean
	public SessionManager sessionManager() {
		ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
		//shiroSession失效时间30分钟
		shiroSessionManager.setGlobalSessionTimeout(30 * 60 * 1000);
//        这里可以不设置。Shiro有默认的session管理。如果缓存为Redis则需改用Redis的管理
		shiroSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
//        //redisShiroSession失效时间30分钟
//        shiroSessionManager.setSessionDAO(new RedisShiroSessionDAO(60*30));
		return shiroSessionManager;
	}

	/**
	 * 安全管理
	 *
	 * @return
	 */
	@Bean(name = "securityManager")
	public SecurityManager securityManager(CustomRealm authRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//注入记住我管理器
		securityManager.setRememberMeManager(rememberMeManager());
		// 配置 缓存管理类 cacheManager，这个cacheManager必须要在前面执行，因为setRealm 和 setSessionManage都有方法初始化了cachemanager,看下源码就知道了
//        securityManager.setCacheManager(cacheManager());
		//自定义登陆与权限管理
		securityManager.setRealm(authRealm);
		//自定义session管理
		securityManager.setSessionManager(sessionManager());

		return securityManager;
	}

	/**
	 * Spring的一个bean , 由Advisor决定对哪些类的方法进行AOP代理 .
	 *
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	/**
	 * 配置shiro跟spring的关联 开启shiro aop注解支持
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
	(@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}
