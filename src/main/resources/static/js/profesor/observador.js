$(document).ready(function() {
    $('select').selectpicker();
    $('#estudiantes').prop('disabled', true);
    $('#cursos').change(function() {
        var url = "/profesor/observador/estudiantes"
        $('#estudiantes').html(url);
        
    })
} );

