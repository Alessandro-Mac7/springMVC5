<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="it">
<head>
    <jsp:include page="/WEB-INF/views/parts/_header.jsp"/>
    <title>Login</title>
</head>
<body class="login-body">

<div class="container">
    <form:form cssClass="form-signin"  action="login" modelAttribute="user" method="post">
        <img class="mb-4" src="<c:url value="/resources/img/logo.png"/>" alt="" width="300">
        <form:label path="email" cssClass="sr-only"/>
        <form:input path="email" cssClass="form-control" placeholder="Email"/>
        <form:label path="password" cssClass="sr-only"/>
        <form:password  path="password" cssClass="form-control" placeholder="Password" />
        <button class="btn btn-lg btn-success btn-block" type="submit">Entra</button>
        <div class="col-sm-12 text-center mt-2">
            <a href="user/register">Crea un nuovo account</a>
        </div>
    </form:form>
</div>

<jsp:include page="/WEB-INF/views/parts/_footer.jsp"></jsp:include>

</body>
</html>
