$(document).ready(function(){

    $("#menu-toggle").on('click', function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    $("#filterCustomerTable").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#customerTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $('#successModal').on('hide.bs.modal', function (e) {
        e.preventDefault();
        window.location.reload();
    });

    // INS
    $(".insCustomerForm").on('submit', function(e) {
        e.preventDefault();
        var formData = {};
        formData["name"] = $('.insCustomerForm').find('input[name="name"]').val();
        formData["lastName"] = $('.insCustomerForm').find('input[name="lastName"]').val();
        formData["date"] = $('.insCustomerForm').find('input[name="date"]').val();
        formData["email"] = $('.insCustomerForm').find('input[name="email"]').val();
        formData["password"] = $('.insCustomerForm').find('input[name="password"]').val();

        var jsonData = JSON.stringify(formData);
        //console.log(data);
        insertCustomer(jsonData);
    });

    $(".insVehicleForm").on('submit', function(e) {
        e.preventDefault();
        var formData = {};
        formData["uniqueId"] = $('.insVehicleForm').find('input[name="uniqueId"]').val();
        formData["manufacturer"] = $('.insVehicleForm').find('input[name="manufacturer"]').val();
        formData["model"] = $('.insVehicleForm').find('input[name="model"]').val();
        formData["carRegistration"] = $('.insVehicleForm').find('input[name="carRegistration"]').val();
        formData["categoryId"] = $('.insVehicleForm').find('select[name="categoryId"]').val();

        var jsonData = JSON.stringify(formData);
        console.log(jsonData);
        insertVehicle(jsonData);
    });

    // DELETE
    $(".deleteCustomer").on('click', function(e) {
        var id = $(this).data('id');
        $("#deleteButton").data("id", id);
        $(".confirmModal").modal();
    });

    $("#deleteButton").on('click', function () {
        var id = $(this).data('id');
        deleteCustomer(id);
    });

    $(".deleteVehicle").on('click', function(e) {
        var id = $(this).data('id');
        $("#deleteButtonVehicle").data("id", id);
        $(".confirmModal").modal();
    });

    $("#deleteButtonVehicle").on('click', function () {
        var id = $(this).data('id');
        deleteVehicle(id);
    });

    //EDIT
    $(".editVehicleForm").on('submit', function (e) {
        e.preventDefault();
        var formData = {};
        formData["id"] = $('.editVehicleForm').find('input[name="id"]').val();
        formData["uniqueId"] = $('.editVehicleForm').find('input[name="uniqueId"]').val();
        formData["manufacturer"] = $('.editVehicleForm').find('input[name="manufacturer"]').val();
        formData["model"] = $('.editVehicleForm').find('input[name="model"]').val();
        formData["carRegistration"] = $('.editVehicleForm').find('input[name="carRegistration"]').val();
        formData["categoryId"] = $('.editVehicleForm').find('select[name="categoryId"]').val();

        var jsonData = JSON.stringify(formData);
        console.log(jsonData);
        updateVehicle(jsonData);

    });

    $(".editVehicleAjax").on('click', function (e) {
        e.preventDefault();
        var id = $(this).data('id');
        getVehicle(id);
    });

    $(".showBookingAjax").on('click', function (e) {
        e.preventDefault();
        var id = $(this).data('id');
        updateList(id);
    });

});


function insertCustomer(jsonData) {
    $.ajax({
        type: "POST",
        contentType : "application/json",
        url: 'user/saveCustomer',
        data: jsonData,
        success: function(data) {
            $('.insCustomerModal').modal('hide');
            $(".modalColorHeader").css("background-color", "green");
            $('.modalResponseTitle').text("Successo");
            $('.modalResponseBody').text("Customer inserito con successo!");
            $('.successModal').modal();
        }, error: function (request, status, error) {
            $('.insCustomerModal').modal('hide');
            $(".modalColorHeader").css("background-color", "red");
            $('.modalResponseTitle').text("Errore");
            $('.modalResponseBody').text("Customer non inserito correttamente!");
            $('.successModal').modal();
        }
    });
}

function insertVehicle(jsonData) {
    $.ajax({
        type: "POST",
        contentType : "application/json",
        url: 'vehicle/save',
        data: jsonData,
        success: function(data) {
            $('.insVehicleModal').modal('hide');
            $(".modalColorHeader").css("background-color", "green");
            $('.modalResponseTitle').text("Successo");
            $('.modalResponseBody').text("Veicolo inserito con successo!");
            $('.successModal').modal();
            //window.location.reload();
        }, error: function (request, status, error) {
            $('.insVehicleModal').modal('hide');
            $(".modalColorHeader").css("background-color", "red");
            $('.modalResponseTitle').text("Errore");
            $('.modalResponseBody').text("Veicolo non inserito correttamente!");
            $('.successModal').modal();
        }
    });
}

function deleteCustomer(id) {
    $.ajax({
        type: "delete",
        url: 'user/delete/'+id,
        success: function(data) {
            $('.confirmModal').modal('hide');
            $(".modalColorHeader").css("background-color", "green");
            $('.modalResponseTitle').text("Successo");
            $('.modalResponseBody').text("Customer eliminato!");
            $('.successModal').modal();
            window.location.reload();
        }, error: function (request, status, error) {
            $('.confirmModal').modal('hide');
            $(".modalColorHeader").css("background-color", "red");
            $('.modalResponseTitle').text("Errore");
            $('.modalResponseBody').text("Customer non eliminato!");
            $('.successModal').modal();
        }
    });
}

function deleteVehicle(id) {
    $.ajax({
        type: "delete",
        url: '/vehicle/delete/'+id,
        success: function(data) {
            $('.confirmModal').modal('hide');
            $(".modalColorHeader").css("background-color", "green");
            $('.modalResponseTitle').text("Successo");
            $('.modalResponseBody').text("Veicolo eliminato!");
            $('.successModal').modal();
            window.location.reload();
        }, error: function (request, status, error) {
            $('.confirmModal').modal('hide');
            $(".modalColorHeader").css("background-color", "red");
            $('.modalResponseTitle').text("Errore");
            $('.modalResponseBody').text("Veicolo non eliminato!");
            $('.successModal').modal();
        }
    });
}

function getVehicle(id) {
    $.ajax({
        type: "get",
        url: 'vehicle/edit/'+id,
        success: function(data) {
            //var objJson = JSON.parse(data);
            var dateParse = new Date(data.carRegistration);
            var dateString = getFormattedDate(dateParse);
            console.log(dateString);
            $('.editVehicleForm').find('input[name="id"]').val(data.id);
            $('.editVehicleForm').find('input[name="uniqueId"]').val(data.uniqueId);
            $('.editVehicleForm').find('input[name="manufacturer"]').val(data.manufacturer);
            $('.editVehicleForm').find('input[name="model"]').val(data.model);
            $('.editVehicleForm').find('input[name="carRegistration"]').val(dateString);
            $('.editVehicleForm').find('select[name="categoryId"]').val(data.categoryId);
            $('.editVehicleModal').modal();
        }, error: function (request, status, error) {

        }
    });
}

function getFormattedDate(date) {
    var year = date.getFullYear();
    var month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
    var day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;
    return day + '-' + month + '-' + year;
}

function updateVehicle(jsonData) {
    $.ajax({
        type: "POST",
        contentType : "application/json",
        url: 'vehicle/save',
        data: jsonData,
        success: function(data) {
            $('.editVehicleModal').modal('hide');
            $(".modalColorHeader").css("background-color", "green");
            $('.modalResponseTitle').text("Successo");
            $('.modalResponseBody').text("Veicolo modificato con successo!");
            $('.successModal').modal();
        }, error: function (request, status, error) {
            $('.editVehicleModal').modal('hide');
            $(".modalColorHeader").css("background-color", "red");
            $('.modalResponseTitle').text("Errore");
            $('.modalResponseBody').text("Veicolo non modificato correttamente!");
            $('.successModal').modal();
        }
    });
}

function updateList(id) {

    $.ajax({
        type: "get",
        url: 'booking/list/'+id,
        success: function(data) {
            var startDate = getFormattedDate(new Date(data.startDate));
            var endDate = getFormattedDate(new Date(data.endDate));
            var html = "";
            $('#bookingList .list li').remove();
            $.each(data, function(index,result) {
                html += "<li> Inizio: " + startDate + " Fine: " + endDate + "</li>"
                $('#bookingList .list').append(html);
            });
            $('.showBookingModal').modal();
        }, error: function (request, status, error) {

        }
    });


}