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
		.reason_list{
			width: 700%;
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
                <h1 class="page-header">Kara Liste</h1>
            </div>
        </div>
        <!-- /.row -->

		<div class="container">
    	<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Kara Liste</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" id="dev-table-filter" data-action="filter" data-filters="#dev-table" placeholder="Arama" />
					</div>
					<table class="table table-hover" id="dev-table">
						<thead>
							<tr>
								<th></th>
								<th>İsim</th>
								<th>Kara listeye alınma sebebi</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${profileList}" var="person" varStatus="loop">
									<tr data-href="/adminProfile/${person.getId() }">
										<td class="pic_list"><img src="${person.getPicture() }" alt="" class="img-circle"></td>
										<td class="name_list">${person.getFullname() }</td>
										<td class="reason_list">${ blacklist.getList().get(person.getId()) }</td>
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