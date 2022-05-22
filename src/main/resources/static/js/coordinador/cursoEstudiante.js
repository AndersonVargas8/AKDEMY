$(document).ready(function() {
    $('#tablaCursoEstudiantes').DataTable(
        {
            "order": [[2, "asc"]],
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            pageLength: 25,
        }
    );
} );