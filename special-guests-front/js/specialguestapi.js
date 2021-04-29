$(document).ready(function(){
    
	// Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    let baseUrl = 'http://localhost:8081/'

    updateTable();

    function updateTable() {

        $.getJSON(baseUrl + 'specialguests', function(data) {

            $.each(data, function( index, element ) {
                let id = element.id;
                let name = element.name;
                let gender = element.gender;
                let base64image = element.base64image;
                $('.table tr:last').after('<tr><td>' + id +'</td><td>' + name +  '</td><td>' + gender +  '</td><td><img style=\'display:block; width:100px;height:100px;\' src="' + base64image + '" /></td><td><a href="#editSpecialGuestModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a href="#deleteSpecialGuestModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a> </td> </tr>');
            });
    
        });

    }

    $('#editSpecialGuestModal').on('show.bs.modal', function (event) {
        let a = $(event.relatedTarget);
        let row = a.parent().parent();

        let id = row.find("td:eq(0)").text();
        let name = row.find("td:eq(1)").text();
        let gender = row.find("td:eq(2)").text();

        let modalBody = $('#editSpecialGuestModal .modal-body');

        modalBody.find('input[name=id-disabled]').val(id);
        modalBody.find('input[name=id]').val(id);
        modalBody.find('input[name=name]').val(name);
        modalBody.find('select[name=gender]').val(gender);

    })

    $('#addSpecialGuestModal form').submit(function() {

        // Create an FormData object 
        var data = new FormData($(this)[0]);

        $.ajax({
            url: baseUrl + 'specialguests',
            type: 'POST',
            data: data,
            enctype: 'multipart/form-data',
            processData: false,  // Important!
            contentType: false,
            cache: false
        });

    });

    $('#editSpecialGuestModal form').submit(function() {

        // Create an FormData object 
        var data = new FormData($(this)[0]);

        let id = $(this)[0][1].value;

        $.ajax({
            url: baseUrl + 'specialguests/' + id,
            type: 'PUT',
            data: data,
            enctype: 'multipart/form-data',
            processData: false,  // Important!
            contentType: false,
            cache: false
        });

    });

    $('#deleteSpecialGuestModal').on('show.bs.modal', function (event) {
        let a = $(event.relatedTarget);
        let row = a.parent().parent();

        let id = row.find("td:eq(0)").text();

        $('#deleteSpecialGuestModal .modal-body p span').text(id);
      
    })

    $('#deleteSpecialGuestModal form').submit(function() {

        let id =  $('#deleteSpecialGuestModal .modal-body p span').text();

        $.ajax({
            url: baseUrl + 'specialguests/' + id,
            method: 'DELETE',
            contentType: "application/json"
        });

    });

});
