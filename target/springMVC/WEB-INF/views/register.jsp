<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="it">
<head>
    <jsp:include page="/WEB-INF/views/parts/_header.jsp"/>
    <title>Register</title>
</head>
<body class="login-body">

<div class="container">
    <div class="row">
        <div class="col-sm-12 text-center">
            <h3>Registra utente</h3>
        </div>
    </div>
    <form:form cssClass="form-signin"  action="save" modelAttribute="user" method="post">
        <form:input path="email" cssClass="form-control" placeholder="Email"/>
        <form:password  path="password" cssClass="form-control" placeholder="Password" />
        <form:input  path="name" cssClass="form-control" placeholder="Nome" />
        <form:input  path="lastName" cssClass="form-control" placeholder="Cognome"/>
        <form:input  type="date" path="date" cssClass="form-control" />
        <div class="form-group form-check mt-3 ml-2">
            <label class="form-check-label">
                <form:checkbox path="admin" cssClass="form-check-input" /> Admin
            </label>
        </div>
        <button class="btn btn-lg btn-success btn-block mt-2" type="submit">Registra</button>
        <div class="col-sm-12 text-center mt-2">
            <a href="${pageContext.request.contextPath}/">Indietro</a>
        </div>
    </form:form>
</div>

<jsp:include page="/WEB-INF/views/parts/_footer.jsp"></jsp:include>
</body>
</html>
