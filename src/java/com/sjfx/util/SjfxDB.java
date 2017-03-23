/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjfx.util;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lxq
 */
@Repository
public class SjfxDB {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	private final Logger logger = Logger.getLogger(getClass());

	public String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Session newSession() {
		return this.sessionFactory.openSession();
	}

	public Serializable insert(Object o) {
		return getCurrentSession().save(o);
	}

	public void delete(Object o) {
		getCurrentSession().delete(o);
	}

	public void update(Object o) {
		getCurrentSession().update(o);
	}

	public void saveOrUpdate(Object o) {
		getCurrentSession().saveOrUpdate(o);
	}

	public List find(String hql) {
		return getCurrentSession().createQuery(hql).list();
	}

	public int executeUpdate(String sql) {
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		return query.executeUpdate();
	}

	public int executeUpdateByHql(String hql) {
		Query query = getCurrentSession().createQuery(hql);
		return query.executeUpdate();
	}

	public JSONObject getPage(String sql, int yeXu, int maxResults) {
		JSONObject result = new JSONObject();
		int jiLuShu;
		if (sql.indexOf("Model") > 0) {
			jiLuShu = ((Long) find("select count(*) " + sql).get(0)).intValue();
		} else {
			jiLuShu = ((BigInteger) getCurrentSession().createSQLQuery("select count(*) " + sql).list().get(0)).intValue();
		}
		int zongYeShu = (jiLuShu + maxResults - 1) / maxResults;

		int dangQianYe = yeXu > zongYeShu ? zongYeShu : yeXu;
		if (dangQianYe < 1) {
			dangQianYe = 1;
		}
		result.put("jls", Integer.valueOf(jiLuShu));
		result.put("zys", Integer.valueOf(zongYeShu));
		result.put("yx", Integer.valueOf(dangQianYe));
		return result;
	}
    
//    public List getResult(String hql,Object obj){
//        List list = new ArrayList();
//        list = find(hql);
//        return list;
//    }
}
