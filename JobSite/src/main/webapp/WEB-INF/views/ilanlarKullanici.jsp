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
</style>
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

		<div class="container">
    	<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">İlanlar</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" id="dev-table-filter" data-action="filter" data-filters="#dev-table" placeholder="Arama" />
					</div>
					<table class="table table-hover" id="dev-table">
						<thead>
							<tr>
								<th>Başlık</th>
								<th>Gerekli Yetenekler</th>
								<th>İlan veriliş tarihi</th>
								<th>İlan sona eriş tarihi</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${advertList}" var="advert" varStatus="loop">
									<tr data-href="ilanKullanici/${advert.getId() }">
										<td>${advert.getTitle() }</td>
										<td><c:forEach items="${advert.getSkills()}" var="skill" varStatus="loop">
											<c:if test="${loop.index == 4 }"> 
												<span class="label label-default">${advert.getSkills().size() - 4 }...</span>
											</c:if>
											<c:if test="${loop.index lt 4 }">
												<span class="label label-success">${skill}</span>
											</c:if>
											</c:forEach>
										</td>
										<td>${advert.stringStart() }</td>
										<td>${advert.stringEnd() }</td>
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