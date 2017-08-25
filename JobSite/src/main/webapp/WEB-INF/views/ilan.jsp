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
				               	İlanın Verilme Tarihi: ${advert.getStart() }
				            </p>
			            </span>	
			            <span>
				            <p class="text-danger">
				                İlanın Kapanma Tarihi: ${advert.getEnd() }
				            </p>
			            </span>
                	</div>
                	<div class="col-md-3">
                		<a class="btn btn-danger psvbtn" href="#">İlanı Pasifleştir <span class="glyphicon glyphicon-remove-sign"></span></a>
                	</div>
                	
                </div>
            </div>
            <div class="col-md-1">	</div>
            <div class="col-md-4 box">
            	<br>
                <c:if test = "${advert.getCount() ge 1}" > 	</c:if>
			                	<center><h3><span class="label 
			                	<c:if test = "${advert.getCount() ge 1}" >label-success	</c:if>
			                	<c:if test = "${advert.getCount() lt 1}" >label-default	</c:if>">
			                	<span class="badge">${advert.getCount()}</span> başvuru</span></h3></center>
               	<br>
            <!-- =============================================================== -->
            <!-- member list -->
            <ul class="friend-list">
             	<c:if test="${advert.getCount() lt 1}">
					<div class="text-muted text-center"> Herhangi bir başvuru bulunmamaktadır.</div>
				</c:if>
				
				<c:if test="${advert.getCount() ge 1}">
					<c:forEach items="${applicants}" var="applicant">
						<li <c:if test="${applicant.getStatus().get(advert.getId()) == 'Kabul Edildi'}">class = "kabul_app"</c:if>
							<c:if test="${applicant.getStatus().get(advert.getId()) == 'Reddedildi'}">class = "red_app"</c:if>
						>	
	                		<img src="${applicant.getPicture() }" alt="" class="img-circle">
	                		<a href="/adminProfile/${applicant.getId() }">
		                		<div class="friend-name" href="ilanlar">	
		                			<strong>${applicant.getFullname() }</strong>
		                		</div>
		                		<div class="last-message text-muted">${applicant.getHeadline() }</div>
		                		<div class="yetenek text-muted">Yetenekler
		                			<span class="badge white-bdg">${applicant.getSkills().size() }</span>
		                		</div>
	                		</a>
	                		<div class="dropdown drp-btn">
							  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							    <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							  	<c:if test="${ applicant.getBlacklisted() == false }">
							    <li class="list-group-item-success"><a href="/accept/${applicant.getId()}/${advert.getId() }">Kabul</a></li>
							    <li class="list-group-item-warning"><a href="/deny/${applicant.getId()}/${advert.getId() }">Red</a></li>
							    <li><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#blackListModal${applicant.getId() }">Kara Listeye Al</button></li>
							   	</c:if>
							   	<c:if test="${applicant.getBlacklisted()}">
							   		 <li class="list-group-item-warning">Kişi Kara Listede!</li>
							    </c:if>
							  </ul>
							</div>  
		                </li> 
		                <hr>
					</c:forEach>
				</c:if>
                
            </ul>
            
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
        </div>
        <!-- /.row -->

        <hr>
        




    </div>
    <!-- /.container -->		    		  
		  
<c:forEach items="${applicants}" var="applicant">	  		  
<!--  Black List Modal -->
<div id="blackListModal${applicant.getId() }" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Kara Listeye Al</h4>
      </div>
      <div class="modal-body">
      	<form method="POST" action="/addBlackList">
                		<div class='text-right'>
                			Kara listeye alma sebebi<br>
                			<input type='text' name='reason' class='form-control' />
                			<input type="hidden" name="profileId" value="${applicant.getId() }">
		                	<input type='submit' class='btn btn-primary' value='Submit' />
		                </div>
		                </form>
      </div>
      				
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>	  	  		  
</c:forEach>  		  
	  		  
	  		  
		  
<%@ include file="/WEB-INF/partials/scripts.jspf" %>
</body>
</html>