<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="parts/_header.jsp"></jsp:include>
    <title>Parco auto</title>
</head>
<body>

<jsp:include page="parts/_nav-user.jsp"></jsp:include>
<!-- Page Content -->
<div class="container">

    <div class="row pt-4">
        <div class="col-sm-6">
            <h4>Parco auto</h4>
        </div>
    </div>

    <div class="row pt-4">
        <c:if test="${not empty vehicles}">
            <input type="text" class="form-control" id="filterVehicleTable">
            <table class="table table-hover" id="vehicleTable">
                <thead>
                <tr>
                    <th scope="col">Targa</th>
                    <th scope="col">Casa costruttrice</th>
                    <th scope="col">Modello</th>
                    <th scope="col">Anno immatricolazione</th>
                    <th scope="col">Categoria</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${vehicles}" var="vehicle">
                    <tr>
                        <td>${vehicle.uniqueId}</td>
                        <td>${vehicle.manufacturer}</td>
                        <td>${vehicle.model}</td>
                        <td>${vehicle.carRegistration}</td>
                        <td>${vehicle.category.category}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${ empty vehicles}">
            <div class="col-sm-12 text-center">
                <h4>Nessun veicolo registrato!</h4>
            </div>
        </c:if>
    </div>
    <!-- /.row -->

</div>
<!-- /.container -->
<!-- Scripts -->
<jsp:include page="parts/_footer.jsp"></jsp:include>

</body>
</html>
