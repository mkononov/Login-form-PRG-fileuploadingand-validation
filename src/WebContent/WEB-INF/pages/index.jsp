<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	</head>

	<body>
		<form:form class="box login" modelAttribute="userObj" method="POST" action="check-user">
			<fieldset class="boxBody">
				<span style="float: right">
					<a href="?lang=ru">ru</a>
					<a href="?lang=en">en</a>
				</span>
	  			<label><spring:message code="username" /></label>
	  			<form:input path="name"/>
	  			<form:errors path="name" cssClass="error" />
	  			<label><spring:message code="password" /></label>
	  			<form:password path="password"/>
	  			<form:errors path="password" cssClass="error"/>
			</fieldset>
			<footer>
				<form:checkbox path="admin" />
				<label><spring:message code="admin"/></label>
	  			<input type="submit" class="btnLogin" value=<spring:message code="login"/> tabindex="4">
			</footer>
		</form:form>
	</body>
</html>