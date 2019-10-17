<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="pagina" value="${requestScope['javax.servlet.forward.request_uri']}" />

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Car Rental</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link ${pagina.endsWith('/home') ? 'active' : ''}" href="${pageContext.request.contextPath}/home">Home
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${pagina.endsWith('/vehicle') ? 'active' : ''}" href="${pageContext.request.contextPath}/vehicle">Parco auto</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${pagina.endsWith('/user') ? 'active' : ''}" href="${pageContext.request.contextPath}/user">Profilo utente</a>
                </li>
            </ul>
        </div>
    </div>
</nav>