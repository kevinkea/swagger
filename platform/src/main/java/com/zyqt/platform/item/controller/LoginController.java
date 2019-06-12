package com.zyqt.platform.item.controller;

import com.zyqt.platform.item.entity.LoginUser;
import com.zyqt.platform.item.entity.SysUser;
import com.zyqt.platform.util.BaseForm;
import com.zyqt.platform.util.security.LoginConstant;
import com.zyqt.platform.util.security.SHA1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.java.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//@slf4j
@Api(value = "登陆类" , tags = "登陆类接口" )
@Controller
public class LoginController {
	/**
	 * 登陆
	 *
	 * @param userName 用户名
	 * @param password 密码
	 */
//	@LogInfo
	@CrossOrigin
	@ApiOperation(value = "登录接口(调试用)" )
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successful — 请求已完成" , responseContainer = "test" , reference = "test2" )})
	@RequestMapping(value = "/debug/login" , method = RequestMethod.GET)
	@ResponseBody
	public BaseForm<SysUser> login(LoginUser loginUser, HttpServletRequest request) throws Exception {
		System.out.println("loginUser：" + loginUser);
		BaseForm result = new BaseForm();
		// 从SecurityUtils里边创建一个 subject
		Subject subject = SecurityUtils.getSubject();
		System.out.println("subject：" + subject);
		// 在认证提交前准备 token（令牌）2019.2.18添加web端记住我功能,保存7天
		UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUserName(), loginUser.getPassword(),loginUser.getIsRememberMe());
		// 执行认证登陆
		subject.login(token);
		//前后端分离传递用户信息
		SysUser sysUser = (SysUser) subject.getPrincipal();
		sysUser.setAuthToken((String) subject.getSession().getId());
		result.setData(sysUser);
		result.setStatus(BaseForm.Status.LOGINSUCCESSFUL);
		return result;
	}

//	@Log(description='前端登录接口:login')
	@CrossOrigin
	@ApiOperation(value = "登录接口" )
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successful — 请求已完成" , responseContainer = "test" , reference = "test2" )})
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	@ResponseBody
	public BaseForm<SysUser> loginByCheck(LoginUser loginUser, HttpServletRequest request) throws Exception {
		BaseForm result = new BaseForm();
		if(verification(loginUser)) {
			// 从SecurityUtils里边创建一个 subject
			Subject subject = SecurityUtils.getSubject();
			System.out.println("subject：" + subject);
			// 在认证提交前准备 token（令牌）2019.2.18添加web端记住我功能,保存7天
			UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUserName(), loginUser.getPassword(),true);
			// 执行认证登陆
			subject.login(token);
			//前后端分离传递用户信息
			SysUser sysUser = (SysUser) subject.getPrincipal();
			sysUser.setAuthToken((String) subject.getSession().getId());
			result.setData(sysUser);
			result.setStatus(BaseForm.Status.LOGINSUCCESSFUL);
		}else {
			result.setStatus(BaseForm.Status.VERIFICATION_FAILURE);
		}
		return result;
	}

	/**
	 * 验签与时间戳验证
	 * @author zhouchen
	 * @date  2018/11/19 16:48
	 * @param [login]
	 * @return boolean
	 */
	private boolean verification(LoginUser login) {
		boolean result = false;
		long timeStamp = login.getTimestamp();
		StringBuilder sign = new StringBuilder(login.getUserName());
		sign.append(LoginConstant._secretKey).append(login.getPassword()).append(LoginConstant._secretKey).append(timeStamp);
		try{
			String signature = SHA1.getHexSha1(sign.toString());
			result = signature.equalsIgnoreCase(login.getSignature());
			if(result){
				long nowTime=System.currentTimeMillis();
//                long timeStampMil=BaseTool.getStrToMil(login.getTimestamp());
				long timeStampMil=login.getTimestamp();
				//当前时间与时间戳不超过2分钟
				if(nowTime-timeStampMil>120000 || nowTime-timeStampMil<-120000){
//					log.info( "登陆人"+ login.getUserName()+"时间戳超时!");
					result=false;
				}
			}else {
//				log.info("verification signature fail ! " + login.getUserName() + "service signature = " + signature);
			}
		}catch(Exception e){
//			log.error("sha1 signature fail ! ",e);
		}
		return result;
	}

}
