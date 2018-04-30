package com.xiaofei.test.session;

import com.xiaofei.test.common.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * shiro session管理
 */
@Slf4j
public class RedisSessionDAO extends AbstractSessionDAO {

    @Autowired
    private JedisUtil jedisUtil;

    private final String SESSION_PREFIX = "session:";

    private byte[] getKey(String key) {
        return (SESSION_PREFIX + key).getBytes();

    }

    private void  saveSession(Session session){
        if (session !=null && session.getId()!=null){
            byte[] key = getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            jedisUtil.set(key,value);
            jedisUtil.expire(key,600);
        }
    }

    protected Serializable doCreate(Session session) {
        log.info("doCreate session");
        //获取sessionid
        Serializable sessionId = generateSessionId(session);
        //将sessionid绑定给session
        assignSessionId(session,sessionId);
        saveSession(session);
        return sessionId;
    }

    /**
     * 获得session
     * @param sessionId
     * @return
     */
    protected Session doReadSession(Serializable sessionId) {
        log.info("doReadSession");
        if (sessionId == null)
            return null;
        byte[] key = getKey(sessionId.toString());
        byte[] value = jedisUtil.get(key);
        return  (Session)SerializationUtils.deserialize(value);
    }

    /**
     * 更新session
     * @param session
     * @throws UnknownSessionException
     */
    public void update(Session session) throws UnknownSessionException {
            saveSession(session);
    }

    public void delete(Session session) {
        if (session != null && session.getId() !=null){
            byte[] key = getKey(session.getId().toString());
            jedisUtil.del(key);
        }
    }

    /**
     * 获取指定存活的session
     * @return
     */
    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys= jedisUtil.keys(SESSION_PREFIX);
        if (keys == null)
            return null;
        Set<Session> sessions = new HashSet<Session>();
        for (byte[] key:keys) {
            sessions.add((Session) SerializationUtils.deserialize(jedisUtil.get(key)));
        }
        return sessions;
    }
}
