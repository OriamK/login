<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="/public/lib/bootstrap-3.3.6-dist/css/bootstrap.min.css" />
</head>
<body>

	<nav class="navbar navbar-default navbar-inverse">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value='/'/>"><spring:message code="home" /></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
				<li><a href="#">Link</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>			
			<ul class="nav navbar-nav navbar-right">			
				<sec:authorize access="isAnonymous()">
					<li><a href='<c:url value="/signup"/>'> <span class="glyphicon glyphicon-list-alt" aria-hidden="true"> </span> <spring:message code="signup"/></a></li>  
					<li><a href="/login"> <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> <spring:message code="signin" /></a></li> 
		
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> <span class="glyphicon glyphicon-user" aria-hidden="true"></span> <sec:authentication							
							property="principal.user.name" />
							<%-- principal = userDetailsImpl --%><span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="/admin"> <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> Administrador</a></li> 
						<li><a href="/user/<sec:authentication
							property="principal.user.id" />"> <span class="glyphicon glyphicon-user" aria-hidden="true"></span> Cuenta</a></li>
						<li><c:url var="logoutUrl" value="/logout" /> <form:form
								id="logoutForm" action="${logoutUrl}" method="post"></form:form>
							<a href="#"
							onclick="document.getElementById('logoutForm').submit()"> 
							<span class="glyphicon glyphicon-log-out" aria-hidden="true">
							</span> Sign out</a></li>									
					</ul>
				</li>
				</sec:authorize>
			</ul>
			<form class="navbar-form navbar-right" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<c:if test="${not empty flashMessage}">
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="alert alert-dismissable alert-${flashKind}">
							<strong>Well done!</strong>${flashMessage}</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>