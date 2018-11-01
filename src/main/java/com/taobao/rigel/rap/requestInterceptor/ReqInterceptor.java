package com.taobao.rigel.rap.requestInterceptor;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.taobao.rigel.rap.reqforward.dao.ReqForwardDao;

public class ReqInterceptor extends AbstractInterceptor{

	@Autowired
	ReqForwardDao dao;
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入拦截器");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String domain = request.getServerName();
		List list = dao.getPageBydomain(domain);
		if(list.size() >0 )
		{
			/*String reqUri = request.getRequestURI();
			String param = request.getQueryString();
			if(param != null)
			{
				reqUri += "?"+param; 
			}
			System.out.println(reqUri);
			int actionId = dao.getActionIdByPage(domain, reqUri);
			
			String path = "/mockjsdata/"+actionId+reqUri;
			System.out.println("地址:"+path);
			request.getRequestDispatcher(path).forward(request, response);*/
			
			return arg0.invoke();
		}
		
		return arg0.invoke();
	}

}
