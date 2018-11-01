package com.taobao.rigel.rap.mockindex.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.taobao.rigel.rap.common.base.ActionBase;
import com.taobao.rigel.rap.project.bo.Action;
import com.taobao.rigel.rap.project.dao.impl.ProjectDaoImpl;

public class MockIndexAction extends ActionBase{
	@Autowired
	ProjectDaoImpl dao;
	
//	@Autowired
//	MockIndexDao dao;
//	public MockIndexDao getDao() {
//		return dao;
//	}
//
//	public void setDao(MockIndexDao dao) {
//		this.dao = dao;
//	}

	public String getActionList()
	{
		System.out.println("action");
		System.out.println("test:");
		List<Action> actionList = dao.getActionListOfProject(1);
		System.out.println("num:"+actionList);
		ActionContext.getContext().put("action", actionList); 
		return SUCCESS;
	}
	
	 
}
