<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/parts/_header.jsp"/>
    <title>Parco auto</title>
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
        <div class="container">
            <div class="row pt-4">
                <div class="col-sm-6">
                    <h4>Parco auto</h4>
                </div>
                <div class="col-sm-6 text-right">
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target=".insMezzoModal" style="width: 30%;margin: 1px;">
                        <i class="fas fa-plus"></i>&ensp;Nuovo</button>
                </div>
            </div>

            <c:if test="${not empty vehicles}">
                <input type="text" class="form-control" id="filterMezziTable">
                <table class="table table-hover" id="mezziTable">
                    <thead>
                    <tr>
                        <th></th>
                        <th scope="col">Targa</th>
                        <th scope="col">Casa costruttrice</th>
                        <th scope="col">Modello</th>
                        <th scope="col">Anno immatricolazione</th>
                        <th scope="col">Categoria</th>
                        <th></th>
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
                            <td><a href="" data-toggle="tooltip" data-placement="top" title="Modifica mezzo"  style="cursor:pointer; "><i class="fas fa-edit"></i></a>&nbsp;
                                <a class="deleteMezzo" href="javascript:void(0);"  data-id=${vehicle.id} data-toggle="tooltip" data-placement="top" title="Elimina mezzo" style="cursor:pointer; "><i class="fas fa-trash-alt"></i></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${ empty vehicles}">
                <div class="col-sm-12 text-center">
                    <h4 style="color: gray">Nessun veicolo registrato!</h4>
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target=".insVehicleModal" style="width: 30%;margin: 1px;">
                        Inserisci veicolo
                    </button>
                </div>
            </c:if>

            </div>

        </div>
        <!-- /Page Content-->
    </div>
    <!-- /Page Content Wrapper-->
</div>
<!-- /Wrapper -->

<div class="modal fade insVehicleModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Inserisci mezzo</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form class="form-signin insVehicleForm"  method="post">
                    <input type="text" class="form-control" name="uniqueId" placeholder="Targa" required autofocus>
                    <input type="text" class="form-control" name="manufacturer" placeholder="Casa costruttrice" required>
                    <input type="text" class="form-control" name="model" placeholder="modello" required>
                    <input type="date" class="form-control" name="carRegistration" placeholder="Anno immatricolazione" required>
                    <select class="form-control" name="category_id">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.category}</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-lg btn-success btn-block mt-2" type="submit">Inserisci veicolo</button>
                </form>
            </div>
        </div>
    </div>
</div>
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

<div class="modal fade confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirm-modal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" >Conferma</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body modalResponseBody">
                <p>Vuoi eliminare il mezzo?</p>
            </div>
            <div class="modal-footer">
                <button id="deleteButtonVehicle" type="button " class="btn btn-primary" data-id="">Elimina</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
            </div>
        </div>
    </div>
</div>


<!-- Scripts -->
<jsp:include page="parts/_footer.jsp"></jsp:include>
<script src="<c:url value="/resources/js/admin.js"/>"></script>


</body>
</html>
