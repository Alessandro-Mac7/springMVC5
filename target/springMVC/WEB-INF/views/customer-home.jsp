<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="parts/_header.jsp"></jsp:include>
    <title>Home</title>
</head>
<body>

<jsp:include page="parts/_nav-user.jsp"></jsp:include>
<!-- Page Content -->
<div class="container">

    <!-- Jumbotron Header -->
    <header class="jumbotron text-center my-4">
        <h2>Ciao</h2>
        <p>${loggedUser}</p>
        <a href="${pageContext.request.contextPath}/logout" ><i class="fas fa-sign-out-alt"></i> Esci</a>
    </header>


    <div class="row pt-4">
        <div class="col-sm-6">
            <h4>Le mie prenotazioni</h4>
        </div>
        <div class="col-sm-6 text-right">
            <button type="button" class="btn btn-info" data-toggle="modal" data-target=".insBookingModal" style="width: 30%;margin: 1px;">
                <i class="fas fa-plus"></i>&ensp;Nuova</button>
        </div>
    </div>

    <div class="row pt-4">
        <div class="col-sm-12 text-center">

        <c:if test="${not empty bookings}">
            <input type="text" class="form-control" id="filterBookingTable">

            <table class="table table-hover" id="bookingTable">
                <thead>
                <tr>
                    <th scope="col">Data inizio</th>
                    <th scope="col">Data fine</th>
                    <th scope="col">Veicolo</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bookings}" var="booking">
                    <tr>
                        <td>${booking.startDate}</td>
                        <td>${booking.endDate}</td>
                        <td>${booking.vehicle.model}&ensp;${booking.vehicle.uniqueId}</td>
                        <td><a href="" class="editBooking" data-id=${booking.id} data-toggle="tooltip" data-placement="top" title="Modifica prenotazione"  style="cursor:pointer; "><i class="fas fa-edit"></i></a>&nbsp;
                            <a class="deleteBooking" href="javascript:void(0);"  data-id=${booking.id} data-toggle="tooltip" data-placement="top" title="Elimina prenotazione" style="cursor:pointer; "><i class="fas fa-trash-alt"></i></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${ empty bookings}">
            <div class="col-sm-12 text-center">
                <h4 style="color: gray">Nessun Prenotazione presente!</h4>
            </div>
        </c:if>
    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<div class="modal fade insBookingModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Nuova prenotazione</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form class="form-signin insBookingForm" >
                    <input type="date" class="form-control" name="startDate"  required autofocus>
                    <input type="date" class="form-control" name="endDate"  required>
                    <input type="text" class="form-control" name="userEmail" value="${loggedUser}" disabled>
                    <select class="form-control" name="vehicleId">
                        <c:forEach items="${vehicles}" var="vehicle">
                            <option value="${vehicle.id}">${vehicle.manufacturer}&ensp;${vehicle.model}&ensp;(${vehicle.category.category})</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-lg btn-success btn-block" type="submit">Prenota</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade editBookingModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Modifica veicolo</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form class="form-signin editBookingForm" action="" method="post">
                    <input type="text" class="form-control" name="id" disabled>
                    <input type="date" class="form-control" name="startDate"  required autofocus>
                    <input type="date" class="form-control" name="endDate"  required>
                    <input type="text" class="form-control" name="userEmail" value="${loggedUser}" disabled>
                    <select class="form-control" name="vehicleId">
                        <c:forEach items="${vehicles}" var="vehicle">
                            <option value="${vehicle.id}">${vehicle.manufacturer}&ensp;${vehicle.model}&ensp;(${vehicle.category.category})</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-lg btn-success btn-block" type="submit">Prenota</button>                </form>
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
                <h4>Prenotazione inserito correttamente!</h4>
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
                <p>Vuoi eliminare la prenotazione?</p>
            </div>
            <div class="modal-footer">
                <button id="deleteButtonBooking" type="button " class="btn btn-primary" data-id="">Elimina</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
            </div>
        </div>
    </div>
</div>


<!-- Scripts -->
<jsp:include page="parts/_footer.jsp"></jsp:include>
<script src="<c:url value="/resources/js/customer.js"/>"></script>

s
</body>
</html>
