$(document).ready(function(){

    $("#filterBookingTable").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#bookingTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#filterVehicleTable").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#vehicleTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    // INS Form
    $(".insBookingForm").on('submit', function(e) {
        e.preventDefault();
        var formData = {};
        formData["startDate"] = $('.insBookingForm').find('input[name="startDate"]').val();
        formData["endDate"] = $('.insBookingForm').find('input[name="endDate"]').val();
        formData["userEmail"] = $('.insBookingForm').find('input[name="userEmail"]').val();
        formData["vehicleId"] = $('.insBookingForm').find('select[name="vehicleId"]').val();

        var jsonData = JSON.stringify(formData);
        console.log(jsonData);
        insertBooking(jsonData);
    });

    //EDIT Form

    $(".editBooking").on('click', function (e) {
        e.preventDefault();
        var id = $(this).data('id');

        getBooking(id);
    })

    //DELETE Modal

    $(".deleteBooking").on('click', function(e) {
        var id = $(this).data('id');
        $("#deleteButtonBooking").data("id", id);
        $(".confirmModal").modal();
    });

    $("#deleteButtonBooking").on('click', function () {
        var id = $(this).data('id');
        deleteBooking(id);
    });

});

//INSERT
function insertBooking(jsonData) {
    $.ajax({
        type: "POST",
        contentType : "application/json",
        url: 'booking/save',
        data: jsonData,
        success: function(data) {
            $('.insBookingModal').modal('hide');
            $(".modalColorHeader").css("background-color", "green");
            $('.modalResponseTitle').text("Successo");
            $('.modalResponseBody').text("Prenotazione effettuata con successo!");
            $('.successModal').modal();
        }, error: function (request, status, error) {
            $('.insBookingModal').modal('hide');
            $(".modalColorHeader").css("background-color", "red");
            $('.modalResponseTitle').text("Errore");
            $('.modalResponseBody').text("Prenotazione non effettuata!");
            $('.successModal').modal();
        }
    });
}

//EDIT

function getBooking(id){
    $.ajax({
        type: "GET",
        url: 'booking/edit/' + id,
        success: function(data) {
            var startDate = getFormattedDate(new Date(data.startDate));
            var endDate = getFormattedDate(new Date(data.endDate));
            console.log(data.id);
            $('.editBookingForm').find('input[name="id"]').val(data.id);
            $('.editBookingForm').find('input[name="startDate"]').val(startDate);
            $('.editBookingForm').find('input[name="endDate"]').val(endDate);
            $('.editBookingForm').find('input[name="userEmail"]').val(data.userEmail);
            $('.editBookingForm').find('select[name="vehicleId"]').val(data.vehicleId);
            $('.editBookingModal').modal();
        }, error: function (request, status, error) {
            $(".modalColorHeader").css("background-color", "red");
            $('.modalResponseTitle').text("Errore");
            $('.modalResponseBody').text("Impossibile modificare la prenotazione!");
            $('.successModal').modal();
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

//DELETE
function deleteBooking(id) {
    $.ajax({
        type: "delete",
        url: 'booking/delete/'+id,
        success: function(data) {
            $('.confirmModal').modal('hide');
            $(".modalColorHeader").css("background-color", "green");
            $('.modalResponseTitle').text("Successo");
            $('.modalResponseBody').text("Prenotazione eliminata!");
            $('.successModal').modal();
            window.location.reload();
        }, error: function (request, status, error) {
            $('.confirmModal').modal('hide');
            $(".modalColorHeader").css("background-color", "red");
            $('.modalResponseTitle').text("Errore");
            $('.modalResponseBody').text("Prenotazione non eliminata!");
            $('.successModal').modal();
        }
    });
}
