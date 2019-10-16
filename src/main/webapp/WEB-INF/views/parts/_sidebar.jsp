<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="pagina" value="${requestScope['javax.servlet.forward.request_uri']}" />

<!-- Sidebar -->
<div class="bg-side border-right" id="sidebar-wrapper">
    <div class="sidebar-heading">Car Rental </div>
    <div class="list-group list-group-mine">
        <a href="${pageContext.request.contextPath}/home" class="list-group-item list-group-item-action ${pagina.endsWith('/home') ? 'active' : ''}">Dashboard</a>
        <a href="${pageContext.request.contextPath}/vehicle/list" class="list-group-item list-group-item-action ${pagina.endsWith('/vehicles') ? 'active' : ''}">Parco auto</a>
    </div>
</div>
<!-- /#sidebar-wrapper -->
