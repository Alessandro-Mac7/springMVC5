<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/webjars/font-awesome/5.10.1/css/fontawesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/springMvc.css" />" rel="stylesheet">
    <title>Home</title>
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
            <div class="row pt-4" style="display:flex;justify-content:space-around;">
                <button type="button" class="btn btn-info" data-toggle="modal" data-target=".insMezzoModal" style="width: 48%;margin: 1px;">
                    Inserisci mezzo
                </button>
                <button type="button" class="btn btn-info" data-toggle="modal" data-target=".insCustomerModal" style="width: 48%;margin: 1px;">
                    Inserisci customer
                </button>
            </div>

            <div class="row pt-4">
                <input type="text" class="form-control" id="filterCustomerTable">
                <table class="table table-hover" id="customerTable">
                    <thead>
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Cognome</th>
                        <th scope="col">Data di Nascita</th>
                        <th scope="col">Email</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${customers}" var="customer">
                        <tr>
                            <td>${customer.name}</td>
                            <td>${customer.lastName}</td>
                            <td>${customer.date}</td>
                            <td>${customer.email}</td>
                            <td>
                                <a href="" class="showBookingAjax" data-id="${customer.id}" data-toggle="tooltip" data-placement="top" title="Visualizza prenotazioni" style="cursor:pointer; "><i class="fas fa-car-side"></i></a>&nbsp;
                                <a href="${pageContext.request.contextPath}/user/edit/${customer.id}" data-toggle="tooltip" data-placement="top" title="Modifica utente" style="cursor:pointer; "><i class="fas fa-edit"></i></a>&nbsp;
                                <a class="deleteCustomer" href="javascript:void(0)" data-id=${customer.id} data-toggle="tooltip" data-placement="top" title="Elimina utente" style="cursor:pointer; "><i class="fas fa-trash-alt"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="row">
                <div id="collapsePrenotazione" class="collapse">

                </div>
            </div>

        </div>
        <!-- /Page Content-->

    </div>
    <!-- /Page Content Wrapper-->

</div>
<!-- /Wrapper -->

<!-- The Modal -->
<div class="modal fade insVehicleModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Inserisci mezzo</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form class="form-signin insVehicleForm" action="mezzi" method="post">
                    <input type="text" class="form-control" name="targa" placeholder="Targa" required autofocus>
                    <input type="text" class="form-control" name="casaCostruttrice" placeholder="Casa costruttrice" required>
                    <input type="text" class="form-control" name="modello" placeholder="modello" required>
                    <input type="date" class="form-control" name="annoImmatricolazione" placeholder="Anno immatricolazione" required>
                    <select class="form-control" name="categoria">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.category}</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-lg btn-success btn-block mt-2" type="submit">Inserisci mezzo</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade insCustomerModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Inserisci Customer</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form class="form-signin insCustomerForm" action="" method="post">
                    <input type="text" class="form-control" name="name" placeholder="Nome" required autofocus>
                    <input type="text" class="form-control" name="lastName" placeholder="Cognome" required>
                    <input type="date" class="form-control" name="date" placeholder="Data nascita" required>
                    <input type="email" class="form-control" name="email" placeholder="Email" required>
                    <input type="password" class="form-control" name="password" placeholder="Password" required>
                    <button class="btn btn-lg btn-success btn-block" type="submit">Registra Customer</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade showPrenotazioniModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Prenotazioni <span id="customerName"></span></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">

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
            <div class="modal-body">
                <p>Vuoi eliminare l'utente?</p>
            </div>
            <div class="modal-footer">
                <button id="deleteButton" type="button " class="btn btn-primary" data-id="">Elimina</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade showBookingModal" tabindex="-1" role="dialog" aria-labelledby="success-modal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header" >
                <h5 class="modal-title" >Prenotazioni <span id="customerId"></span></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body ">
                <div id="bookingList">
                    <ul class="list">
                        <li>
                            <h3 class="name">name1</h3>
                        </li>
                        <li>
                            <h3 class="name">name2</h3>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/views/parts/_footer.jsp"></jsp:include>
<script src="<c:url value="/resources/js/admin.js"/>"></script>


</body>
</html>