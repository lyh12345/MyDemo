<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/stat/js/core/jquery-2.1.4.min.js"></script>
<link href="/stat/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/stat/bootstrap/css/bootstrap-select.min.css">
<script src="/stat/bootstrap/js/bootstrap.min.js"></script>
<script src="/stat/bootstrap/js/bootstrap-select.min.js"></script>
</head>
<body>
<!-- <ul class="nav nav-tabs"> -->
<%-- <s:iterator value="#request.action" var="action"> --%>
<%--   <li role="presentation" class="#"><a href="#" onclick="aclick(this)" requrl="<s:property value="#action.requestUrl" />" ><s:property value="#action.name"/></a></li> --%>
<%-- </s:iterator>   --%>

<select class="selectpicker" multiple>
    <option value="1">广东省</option>
    <option value="2">广西省</option>
    <option value="3">福建省</option>
    <option value="4">湖南省</option>
    <option value="5">山东省</option>                            
</select>
</ul> 
</body>
<script type="text/javascript">
function aclick(obj)
{
	
	var url = $(obj).attr("requrl");
	var req = "http://";
	var i = url.lastIndexOf('/');
	var path = url.substring(0,i);
	var servername = url.substring(i+1)+"/";
	var req = "http://"+servername+path;
	
	window.location.href=req;
   // window.location.href="http://api.37wan.5ypy.com/query/worldServer/getDau";
}

function tclick()
{
		
    window.location.href="http://api.ring.gm99.com/query/worldServer/getDau";
}
</script>
</html>