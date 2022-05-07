$(document).ready(function() {
    $('#observadorTable').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },

            bDestroy: true
        }
    );
    $('select').selectpicker();
    $('#cursos').change(function() {
        var url = "observador/estudiantes/" + $(this).val();
        $('#estudiantes').load(url);
        
    })

    $('#estudiantesSelect').change(function() {

        var url = "observador/" + $(this).val();
        $('#observador').load(url);
    })
} );

