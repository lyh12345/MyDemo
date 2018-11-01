package com.taobao.rigel.rap.reqforward.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class ReqForwardDao extends HibernateDaoSupport{

	public int getActionIdByPage(String domain,String url)
	{
		String sql="select a_p.action_id from tb_page join tb_action_and_page a_p on tb_page.id =a_p.page_id join tb_action on a_p.action_id = tb_action.id where tb_page.introduction = ? and tb_action.request_url = ? ";
		Query query = currentSession().createSQLQuery(sql).setString(0, domain).setString(1, url);
		int id = (int) query.uniqueResult();
		return id;
	}
	
	public List getPageBydomain(String domain)
	{
		String sql = "select id from tb_page where introduction=?";
		Query query = currentSession().createSQLQuery(sql).setString(0, domain);
		return query.list();
	}
}
