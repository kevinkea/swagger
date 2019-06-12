package com.zyqt.platform.config.shiro;

import com.zyqt.platform.util.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RedisShiroSessionDAO extends EnterpriseCacheSessionDAO {

	private static String REDIS_SHIRO_HEAD="shiro:";
	private static int REDIS_TIME=3600;

	public RedisShiroSessionDAO() {}

	public RedisShiroSessionDAO(int time) {
		REDIS_TIME=time;
	}

//	//创建session
//	@Override
//	protected Serializable doCreate(Session session) {
//		Serializable sessionId = super.doCreate(session);
//		final String key =REDIS_SHIRO_HEAD+ DigestUtils.md5Hex(sessionId.toString());
//		setShiroSession(key, session);
//		return sessionId;
//	}
//
//	//获取session
//	@Override
//	protected Session doReadSession(Serializable sessionId) {
//		Session session = super.doReadSession(sessionId);
//		if(session == null){
//			final String key =REDIS_SHIRO_HEAD+DigestUtils.md5Hex(sessionId.toString());
//			session = getShiroSession(key);
//		}
//		return session;
//	}
//
//	//更新session
//	@Override
//	protected void doUpdate(Session session) {
//		super.doUpdate(session);
//		final String key = REDIS_SHIRO_HEAD+DigestUtils.md5Hex(session.getId().toString());
//		setShiroSession(key, session);
//	}
//
//	//删除session
//	@Override
//	protected void doDelete(Session session) {
//		super.doDelete(session);
//		final String key = REDIS_SHIRO_HEAD+DigestUtils.md5Hex(session.getId().toString());
//		RedisUtil.serDel(key);
//	}

	private Session getShiroSession(String key) {
		Session session =(Session) RedisUtil.serGet(key);
		return session;
	}

	private void setShiroSession(String key, Session session){
		RedisUtil.serSet(key, session);
		//60分钟过期
		RedisUtil.expire(key, REDIS_TIME);
	}
}
