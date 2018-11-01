package com.taobao.rigel.rap.reqforward.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;

import com.taobao.rigel.rap.common.base.ActionBase;
import com.taobao.rigel.rap.reqforward.dao.ReqForwardDao;

public class ReqForwardAction extends ActionBase{

	@Autowired
	ReqForwardDao dao;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String path;
	String reqUri = request.getRequestURI();
	private Integer projectId;
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String forward()
	{
		System.out.println(request.getServerName()+" "+request.getRequestURI()+request.getQueryString());
		String ipName = request.getServerName();
		String param = request.getQueryString();
		if(param != null)
		{
			reqUri += "?"+param; 
		}
		System.out.println(reqUri);
		int actionId = dao.getActionIdByPage(ipName, reqUri);
		//System.out.println("通过页面获取的actionid"+actionId);
		
		
		path = "/mockjsdata/"+actionId+reqUri;
		System.out.println(path);
		return SUCCESS;
	}
}
