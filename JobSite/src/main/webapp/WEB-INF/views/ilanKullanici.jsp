<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>İlan x</title>
<%@ include file="/WEB-INF/partials/style.jspf" %>
<link rel="stylesheet" href="../resources/css/ilan.css" />
</head>
<body>
<%@ include file="/WEB-INF/partials/adminNavbar.jspf" %>
	  		  
	<div class="headspace"></div>
    
<!-- Page Content -->
    <div class="container">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${advert.getTitle()}</h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Project One -->
        <div class="row">
            <div class="col-md-7 box description">
                		<p>
                			${advert.getText()}
                		</p>
                		<br>
                		<hr>
                <div class="row">
                	<div class="col-md-9">
                		<span style=" right: auto; left: 20px;">
			                <p class="text-info">
				               	İlanın Verilme Tarihi: ${advert.stringStart() }
				            </p>
			            </span>	
			            <span>
				            <p class="text-danger">
				                İlanın Kapanma Tarihi: ${advert.stringEnd() }
				            </p>
			            </span>
                	</div>
                	<div class="col-md-3">
                	<c:if test="${not empty profile}">   
                	<form method="POST" action="/addApplicant">
                		<div class='text-right'>
                			
                			<input type="hidden" name="advertId" value="${advert.getId() }">
                			<input type="hidden" name="profileId" value="${profile.getId() }">
		                	<input type='submit' class='btn btn-primary' value='Başvur' />
		                </div>
		                </form>
		                </c:if>
                	</div>
                	
                </div>
            </div>
        <hr>
        




    </div>
    <!-- /.container -->		    		  
	  		  
	  		  
	  		  
	  		  
	  		  
	  		  
		  
<%@ include file="/WEB-INF/partials/scripts.jspf" %>
</body>
</html>