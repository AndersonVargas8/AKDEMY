$(document).ready(function() {
    $('#cursoTabke').DataTable(
        {   
            "order": [[1, "asc"]],
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );
    $('.search_select_box select').selectpicker();
} );