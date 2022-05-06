$(document).ready(function() {
    $('select').selectpicker();
    $('#cursos').change(function() {
        var url = "profesor/observador/estudiantes"
        $('#estudiantes').load("/profesor/observador/estudiantes");
        
    })
} );

