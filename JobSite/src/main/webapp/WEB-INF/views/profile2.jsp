<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/landing_page.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S A M P L E _ T E X T</title>
<style>
body {background: #EAEAEA;}
.user-details {position: relative; padding: 0;}
.user-details .user-image {position: relative;  z-index: 1; width: 100%; text-align: center;}
 .user-image img { clear: both; margin: auto; position: relative;}

.user-details .user-info-block {width: 100%; position: absolute; top: 55px; background: rgb(255, 255, 255); z-index: 0; padding-top: 35px;}
 .user-info-block .user-heading {width: 100%; text-align: center; margin: 10px 0 0;}
 .user-info-block .navigation {float: left; width: 100%; margin: 0; padding: 0; list-style: none; border-bottom: 1px solid #428BCA; border-top: 1px solid #428BCA;}
  .navigation li {float: left; margin: 0; padding: 0;}
   .navigation li a {padding: 20px 30px; float: left;}
   .navigation li.active a {background: #428BCA; color: #fff;}
 .user-info-block .user-body {float: left; padding-top: 5%; padding-bottom: 5%; width: 90%; margin-left: 5%;}
  .user-body .tab-content > div {float: left; width: 100%;}
  .user-body .tab-content h4 {width: 100%; margin: 10px 0; color: #333;}
  .profilecard{ margin-top: 230px;}
</style>
</head>
<body>
		<div class="container profilecard">
			<div class="row">
			<div class="col-sm-4 col-md-4"></div>
				<div class="col-sm-4 col-md-4 user-details">
		            <div class="user-image">
		                <img src="${profile.getPicture()}" alt="Karan Singh Sisodia" title="Karan Singh Sisodia" class="img-circle">
		            </div>
		            <div class="user-info-block">
		                <div class="user-heading">
		                    <h3>${profile.getFullname()} </h3>
		                    <span class="help-block">${profile.getHeadline()}</span>
		                </div>
		                <div class="user-body">
		                        	<a href="${profile.getLink() }"><center><h4><img style="height: 20px; class="img-responsive" src="resources/img/linkedin.png" alt="">
		                        		LinkedIn Page</h4> 
		                        	</a>
		                        	<p> Email: ${profile.getEmail()}</p></center>
		                </div>
		            </div>
		        </div>
			</div>
		</div>
</body>
</html>