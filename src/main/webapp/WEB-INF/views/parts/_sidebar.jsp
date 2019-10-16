<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="pagina" value="${requestScope['javax.servlet.forward.request_uri']}" />

<!-- Sidebar -->
<div class="bg-side border-right" id="sidebar-wrapper">
    <div class="sidebar-heading">Car Rental </div>
    <div class="list-group list-group-mine">
        <a href="admin-home" class="list-group-item list-group-item-action ${pagina.endsWith('/admin-home') ? 'active' : ''}">Dashboard</a>
        <a href="mezzi" class="list-group-item list-group-item-action ${pagina.endsWith('/vehicles') ? 'active' : ''}">Parco auto</a>
        <a href="buonisconto" class="list-group-item list-group-item-action ${pagina.endsWith('/user') ? 'active' : ''}">Profilo Utente</a>
    </div>
</div>
<!-- /#sidebar-wrapper -->
