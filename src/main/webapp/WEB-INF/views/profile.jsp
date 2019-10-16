<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/parts/_header.jsp"/>
    <title>Profile</title>
</head>
<body>
<!-- Wrapper -->
<div class="d-flex" id="wrapper">

    <!-- Sidebar jsp include -->
    <jsp:include page="parts/_sidebar.jsp"></jsp:include>

    <!-- Page Content Wrapper-->
    <div id="page-content-wrapper">
        <!-- Navbar jsp include -->
        <jsp:include page="parts/_nav-super.jsp"></jsp:include>

        <!-- Page Content-->
        <div class="container-fluid">
            <div class="row pt-4 text-center">
                <h3>Modifica profilo</h3>
            </div>

            <div class="row pt-4">
                <form:form cssClass="form-signin"  action="saveCustomer" modelAttribute="user" method="post">
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
                    <button class="btn btn-lg btn-success btn-block mt-2" type="submit">Modifica profilo</button>
                    <div class="col-sm-12 text-center mt-2">
                        <a href="${pageContext.request.contextPath}/home">Indietro</a>
                    </div>
                </form:form>
            </div>
            <!-- /Page Content-->

        </div>
        <!-- /Page Content Wrapper-->

    </div>
    <!-- /Wrapper -->

    <div class="modal fade successModal" tabindex="-1" role="dialog" aria-labelledby="success-modal" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header modalColorHeader" style="background-color: forestgreen; color: white">
                    <h5 class="modal-title modalResponseTitle" >Successo</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body modalResponseBody">
                    <h4>Utente registrato con successo!</h4>
                </div>
            </div>
        </div>
    </div>


    <jsp:include page="/WEB-INF/views/parts/_footer.jsp"></jsp:include>
    <script src="<c:url value="/resources/js/admin.js"/>"></script>


</body>
</html>
