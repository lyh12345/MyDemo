package com.taobao.rigel.rap.mockindex.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.taobao.rigel.rap.project.bo.Action;
public class MockIndexDao extends HibernateDaoSupport{

	public List<Action> getActionListByProject(int projectId) {
        List<Action> list = new ArrayList<Action>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id ");
        sql.append("FROM tb_project p ");
        sql.append("JOIN tb_module m ON m.project_id = p.id ");
        sql.append("JOIN tb_page ON tb_page.module_id = m.id ");
        sql.append("JOIN tb_action_and_page anp ON anp.page_id = tb_page.id ");
        sql.append("JOIN tb_action a ON a.id = anp.action_id ");
        sql.append("WHERE p.id = :projectId ");
        SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
        		
        		Session session = sf.openSession();
        System.out.println("session"+session);
        Query query = session.createSQLQuery(sql.toString());
        query.setInteger("projectId", projectId);
        List result = query.list();
        List<Integer> ids = new ArrayList<Integer>();
        for (Object r : result) {
            ids.add((Integer) r);
        }
        for (Integer id : ids) {
            list.add((Action) session.get(Action.class, id));
        }
        
        Transaction t = session.beginTransaction();
        t.commit();
        session.close();
        
        return list;
        
    } 
}
