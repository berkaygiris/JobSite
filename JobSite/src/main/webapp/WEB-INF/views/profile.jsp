<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TITLETITLETITLE</title>
<%@ include file="/WEB-INF/partials/style.jspf" %>
<link rel="stylesheet" href="../resources/css/profile.css" />
<link rel="stylesheet" href="../resources/webjars/tokenize2.min.css" />
</head>
<body>
<%@ include file="/WEB-INF/partials/navbar.jspf" %>

	  <div class="container profilecard">
			<div class="row">
			<div class="col-sm-1 col-md-1"></div>
				<div class="col-sm-4 col-md-4 user-details">
		            <div class="user-image">
		                <img src="${profile.getPicture()}" alt="Picture" title="Picture" class="img-circle">
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
		                        	<button type="button" class="btn" data-toggle="modal" data-target="#addSkillModal">Yetenek ekle</button><br>
		                        	<table class="table" id="skills">
		                        		Yetenekler
									    <thead>
									    </thead>
									    <tbody>
									    <c:forEach items="${profile.getSkills()}" var="skill" varStatus="loop">
									    <c:if test="${loop.index % 2 == 0}"> <tr> </c:if>
									    	<td class="success">${skill }</td>
									    <c:if test="${loop.index % 2 == 1}"> </tr></c:if>
									    </c:forEach>
									    </tbody>
									  </table>
		                </div>
		            </div>
		        </div>
		        <div class="col-md-7 col-sm-7">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Başvurulan ilanlar</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" id="dev-table-filter" data-action="filter" data-filters="#dev-table" placeholder="Arama" />
					</div>
					<table class="table table-hover" id="dev-table">
						<thead>
							<tr>
								<th>İlan ismi</th>
								<th>Durum</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${profile.getApplications()}" var="application" varStatus="loop">
									<tr data-href="ilanKullanici/${application.key }">
										<td>${application.value }</td>
										<td>
											<c:if test="${profile.getStatus().get(application.key) == 'Beklemede' }"> 
												<span class="label label-default">${profile.getStatus().get(application.key) }</span>
											</c:if>
											<c:if test="${profile.getStatus().get(application.key) == 'Reddedildi' }"> 
												<span class="label label-danger">${profile.getStatus().get(application.key) }l</span>
											</c:if>
											<c:if test="${profile.getStatus().get(application.key) == 'Kabul Edildi' }"> 
												<span class="label label-success">${profile.getStatus().get(application.key) }</span>
											</c:if>
										</td>
									</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			</div>
		</div>		  
	  	
	  	
	  	<div class="container">
    	<div class="row">
			
		</div>
	</div>	  
	  		  
	  		  
<!-- Modal -->
<div id="addSkillModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Yetenek Ekle</h4>
      </div>
      <div class="modal-body">
      	<form method="POST" action="/addSkills">
                		<div class='text-right'>
                			<select path="skills" name="skills" class="tokenize-demo" multiple>
								<%@ include file="/WEB-INF/partials/skills.jspf" %>
							</select>
                			<input type="hidden" name="profileId" value="${profile.getId() }">
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
	  		  
	  		  
	  		  
		  
<%@ include file="/WEB-INF/partials/scripts.jspf" %>
<script src="../resources/webjars/tokenize2.min.js"></script>
<%@ include file="/WEB-INF/partials/scriptTableSrch.jspf" %>
<script>$('.tokenize-demo').tokenize2();</script>
<script>
$('tr[data-href]').on("click", function() {
    document.location = $(this).data('href');
});	

$('tr[data-href]').hover(function() {
    $(this).css('cursor','pointer');
});
</script>
</body>
</html>