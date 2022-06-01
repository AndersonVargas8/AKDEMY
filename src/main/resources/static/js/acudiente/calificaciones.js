$(document).ready(function() {
    $('select').selectpicker();
    $('#califEstu').hide();
    $('#estudiantesSelect').change(function() {

        var url = "calificaciones/" + $(this).val();
        $('#califEstu').load(url, function(){
            $('#califEstu').show();
        });
    })
} );