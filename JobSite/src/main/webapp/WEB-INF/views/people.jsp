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
<style>
		.pic_list{
			width: 10%;
			position: relative;
		}
		.name_list{
			vertical-align:	middle !important;
			width: 20%;
			position: relative;
		}
		.skills_list{
			width: 30%;
			position: relative;
		}
		.app_list{
			width: 20%;
			position: relative;
		}
		.mail_list{
			vertical-align:	middle !important;
			width: 20%;
			position: relative;
		}
		
		.clickable{
		    cursor: pointer;   
		}

		.panel-heading div {
			margin-top: -18px;
			font-size: 15px;
		}
		.panel-heading div span{
			margin-left:5px;
		}
		.blacklisted_row{
			background-color:rgba(199, 0, 0, 0.2);
		}
</style>
</head>
<body>
    <%@ include file="/WEB-INF/partials/adminNavbar.jspf" %>

<div class="headspace"></div>
    
<!-- Page Content -->
    <div class="container">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Kişiler</h1>
            </div>
        </div>
        <!-- /.row -->

		<div class="container">
    	<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Kişiler</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" id="dev-table-filter" data-action="filter" data-filters="#dev-table" placeholder="Arama" />
					</div>
					<table class="table table-hover" id="dev-table">
						<thead>
							<tr>
								<th></th>
								<th>İsim</th>
								<th>Yetenekler</th>
								<th>Başvurular</th>
								<th>E-mail</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${profileList}" var="person" varStatus="loop">
									<tr data-href="/adminProfile/${person.getId() }" 
										<c:if test="${person.getBlacklisted() == true }"> class = "blacklisted_row"  </c:if>
									>
										<td class="pic_list"><img src="${person.getPicture() }" alt="" class="img-circle"></td>
										<td class="name_list">${person.getFullname() }</td>
										<td class="skills_list"><c:forEach items="${person.getSkills()}" var="skill" varStatus="loop">
											<c:if test="${loop.index == 8 }"> 
												<span class="label label-default">${person.getSkills().size() - 8 }...</span>
											</c:if>
											<c:if test="${loop.index lt 8 }">
												<span class="label label-success">${skill}</span>
											</c:if>
											</c:forEach></td>
										<td class="app_list">
										<c:choose>
										<c:when test="${person.getBlacklisted() == true}">
											Kişi kara listede
										</c:when>
										<c:otherwise>
										<c:forEach items="${person.getApplications()}" var="application" varStatus="loop">
											<c:if test="${loop.index == 5 }"> 
												<span class="label label-default">${person.getApplications().size() - 5 }...</span>
											</c:if>
											<c:if test="${loop.index lt 5 }">
												<p><span class="label label-info"> ${application.value }</span></p>
											</c:if>
											</c:forEach>
										</c:otherwise>
										</c:choose>
										</td>
										<td class="mail_list">${person.getEmail() }</td>
									</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>





    </div>
    <!-- /.container -->		  
		  
<%@ include file="/WEB-INF/partials/scripts.jspf" %>

<%@ include file="/WEB-INF/partials/scriptTableSrch.jspf" %>
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