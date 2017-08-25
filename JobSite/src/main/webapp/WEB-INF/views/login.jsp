<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
<h1>Giriş yapınız</h1>
<div id="login-error">
	${error}
</div>

<form action="<c:url value="/j_spring_security_check"></c:url>" method="post" >
	<p>
	 <label for="username">Kullanıcı adı</label>
	 <input id="username" name="username" type="text" />
	</p>
	<p>
	 <label for="password">Şifre</label>
	 <input id="password" name="password" type="password" />
	</p>
	<input  type="submit" value="Login"/>        
	  
</form>
</body>
</html>