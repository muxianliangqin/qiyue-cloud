package com.qiyue.user.dao.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class UserEntityManager {
//	@PersistenceContext(unitName="user")
	@Autowired
	private EntityManager user ;

	private Query query ;

	public List<String> findAllNavi() {
		String sql = "SELECT UNI_URL FROM USER_NAVIGATION_INFO";
		query = user.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}
}
