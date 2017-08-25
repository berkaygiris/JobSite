<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TITLETITLETITLE</title>
<%@ include file="/WEB-INF/partials/style.jspf" %>
</head>
<body>
	  		  <div style="position: absolute; top: 35%; left: 40%;">
	  		 <a href=
                                "https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id=8657sei31n7odt&redirect_uri=http%3A%2F%2Flocalhost%3A8181%2Fauth%2Flinkedin&state=25EDUaG2Si&scope=r_basicprofile%20r_emailaddress" 
                                class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> 
                                	<span class="network-name"> 
                                	<img style="height: 20px; class="img-responsive" src="resources/img/linkedin.png" alt="">
                                	 LOGIN WITH LINKEDIN</span>
                                </a> 
	  		  
	  		  </div>
	  		  
	  		  
	  		  
	  		  
		  
<%@ include file="/WEB-INF/partials/scripts.jspf" %>
</body>
</html>