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

    $('#successModal').on('hidden.bs.modal', function (e) {
        e.preventDefault();
        window.location.reload();
    })

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
        formData["name"] = $('.insVehicleForm').find('input[name="uniqueId"]').val();
        formData["lastName"] = $('.insVehicleForm').find('input[name="manufacturer"]').val();
        formData["date"] = $('.insVehicleForm').find('input[name="model"]').val();
        formData["email"] = $('.insVehicleForm').find('input[name="carRegistration"]').val();
        formData["password"] = $('.insVehicleForm').find('select[name="category_id"]').val();

        var jsonData = JSON.stringify(formData);
        console.log(data);
        //insertVehicle(form);
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

function insertVehicle(form) {
    $.ajax({
        type: "POST",
        url: 'mezzi',
        data: form,
        success: function(data) {
            $('.insVehicleModal').modal('hide');
            $(".modalColorHeader").css("background-color", "green");
            $('.modalResponseTitle').text("Successo");
            $('.modalResponseBody').text("Mezzo inserito con successo!");
            $('.successModal').modal();
            window.location.reload();
        }, error: function (request, status, error) {
            $('.insVehicleModal').modal('hide');
            $(".modalColorHeader").css("background-color", "red");
            $('.modalResponseTitle').text("Errore");
            $('.modalResponseBody').text("Mezzo non inserito correttamente!");
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