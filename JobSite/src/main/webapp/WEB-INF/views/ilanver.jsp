<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>İlan Oluştur</title>
<%@ include file="/WEB-INF/partials/style.jspf" %>
<link rel="stylesheet" href="../resources/css/addAdvert.css" />
<link rel="stylesheet" href="../resources/css/datepicker.css" />
<link rel="stylesheet" href="../resources/webjars/tokenize2.min.css" />
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.6/summernote.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/WEB-INF/partials/adminNavbar.jspf" %>

<div class="headspace"></div>

<!-- Page Content -->
    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">İş ilanı oluştur</h1>
            </div>
        </div>
        <!-- Heading/.row -->
		<div class='container'>
		    <div class='row'>
		        <div class='col-sm-10 col-sm-offset-1'>
		            <div class='well'>
		                <form method="POST" action="/addAdvert" modelAttribute="advert">
		                    <div class='row'>
		                        <div class='col-sm-5'>
		                            <div class='form-group'>
		                                <label for='fname'>İlan Başlığı</label>
		                                <input type='text' name='title' class='form-control' />
		                            </div>
		                            <div class="form-group"> <!-- Date input -->
								        <label class="control-label" for="date">Başlangıç Tarihi</label>
								        <input class="datepicker" path="start" name="start" placeholder="MM/DD/YYY" type="text"/>
								      </div>
								    <div class="form-group"> <!-- Date input -->
								        <label class="control-label" for="date">Sona Erme Tarihi</label>
								        <input class="datepicker" path="end" name="end" placeholder="MM/DD/YYY" type="text"/>
								      </div>
								    <br>&nbsp;&nbsp;
								    <input type="checkbox" name="active" id="active" checked="checked"/> Aktif
		                        </div>
		                        <div class='col-sm-7'>
		                            <div class='form-group'>
		                                <label for='text'>İş Tanımı</label><br>
		                                <textarea id="summernote" name="text" path="text" rows='10'></textarea>
		                            </div> <!-- Skill input -->
								       Gerekli yetenekler
								       <select path="skills" name="skills" class="tokenize-demo" multiple>
											<%@ include file="/WEB-INF/partials/skills.jspf" %>
										</select>
		                            <div class='text-right'>
		                                <input type='submit' class='btn btn-primary' value='Submit' />
		                            </div>
		                        </div>
		                    </div>
		                </form>
		            </div>
		        </div>
		    </div>
		</div>
    </div>
<!-- /.container -->
	  		  
		  
<%@ include file="/WEB-INF/partials/scripts.jspf" %>
<script src="../resources/webjars/bootstrap-datepicker.js"></script>
<script src="../resources/webjars/tokenize2.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.6/summernote.js"></script>
<script>
	$(document).ready(function() {
	  $('#summernote').summernote();
	});
</script>
<script>
	$(function(){
		$('.datepicker').datepicker();
	});

</script>
<script>$('.tokenize-demo').tokenize2();</script>
</body>
</html>