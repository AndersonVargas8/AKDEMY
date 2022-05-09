$(document).ready(function() {
    table = $('#cursosTable').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },

            bDestroy: true,
            order: [[ 0, "desc" ]]
        }
    );
});