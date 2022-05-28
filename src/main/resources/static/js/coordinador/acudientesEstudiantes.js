$(document).ready(function() {
    $('#tablaAcudientesConEstudiantes').DataTable(
        {
            "order": [[1, "asc"]],
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );
} );