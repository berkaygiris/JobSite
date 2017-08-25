<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TITLETITLETITLE</title>
<%@ include file="/WEB-INF/partials/style.jspf" %>
<link rel="stylesheet" href="../resources/css/ilanlar.css" />
</head>
<body>
    <%@ include file="/WEB-INF/partials/navbar.jspf" %>

<div class="headspace"></div>
    
<!-- Page Content -->
    <div class="container">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">İlanlar</h1>
            </div>
        </div>
        <!-- /.row -->
		<c:if test="${empty advertList}">
			İlan yok!??!?
		</c:if>
		
		<c:if test="${!empty advertList}">
			<c:forEach items="${advertList}" var="advert" varStatus="loop">
				<c:if test="${loop.index % 3 == 0 }"> 
					<div class="row">
			            <div class="col-md-4 description box">
			                		<center><h3>${advert.getTitle() }</h3></center>
			                		<p class="text-overflow">
			                			${advert.getText() }
			                		</p>
			                		<span style=" right: auto; left: 20px;">
			                			<p class="text-info">
				                			İlanın Verilme Tarihi: ${advert.getStart() }
				                		</p>
			                		</span>
			                		
			                		<span>
				                		<p class="text-danger">
				                			İlanın Kapanma Tarihi: ${advert.getEnd() }
				                		</p>
			                		</span>
			            </div>
				</c:if>
				<c:if test="${loop.index % 3 == 1 }"> 
			            <div class="col-md-4 description box">
			                		<center><h3>${advert.getTitle() }</h3></center>
			                		<p class="text-overflow">
			                			${advert.getText() }
			                		</p>
			                		<span style=" right: auto; left: 20px;">
			                			<p class="text-info">
				                			İlanın Verilme Tarihi: ${advert.getStart() }
				                		</p>
			                		</span>
			                		
			                		<span>
				                		<p class="text-danger">
				                			İlanın Kapanma Tarihi: ${advert.getEnd() }
				                		</p>
			                		</span>
			            </div>
				</c:if>
				<c:if test="${loop.index % 3 == 2 }"> 
			        
			            <div class="col-md-4 description box">
			                		<center><h3>${advert.getTitle() }</h3></center>
			                		<p class="text-overflow">
			                			${advert.getText() }
			                		</p>
			                		<span style=" right: auto; left: 20px;">
			                			<p class="text-info">
				                			İlanın Verilme Tarihi: ${advert.getStart() }
				                		</p>
			                		</span>
			                		
			                		<span>
				                		<p class="text-danger">
				                			İlanın Kapanma Tarihi: ${advert.getEnd() }
				                		</p>
			                		</span>
			            </div>
			        </div>
			         <hr>
			      </c:if>
				 
			</c:forEach>
		</c:if>

      

        <!-- Pagination -->
        <div class="row text-center">
            <div class="col-lg-12">
                <ul class="pagination">
                    <li>
                        <a href="#">&laquo;</a>
                    </li>
                    <li class="active">
                        <a href="#">1</a>
                    </li>
                    <li>
                        <a href="#">2</a>
                    </li>
                    <li>
                        <a href="#">3</a>
                    </li>
                    <li>
                        <a href="#">4</a>
                    </li>
                    <li>
                        <a href="#">5</a>
                    </li>
                    <li>
                        <a href="#">&raquo;</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- /.row -->

        <hr>



    </div>
    <!-- /.container -->		  
		  
<%@ include file="/WEB-INF/partials/scripts.jspf" %>
</body>
</html>