$(document).ready(function() {
    $('#teachers').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"

            },
            "columnDefs": [ {
                "targets": -1,
                "data": null,
                "defaultContent": "<button type='button' class='btn btn-danger btn-sm'><span class='material-icons align-middle'>delete</span><span class='align-middle'>Eliminar</span></button>"
            },
            {
                "targets": -2,
                "data": null,
                "defaultContent": "<button type='button' class='btn btn-info btn-sm'><span class='material-icons align-middle'>edit</span><span class='align-middle'>Editar</span></button>"
            }
        ]
        }
    );
    
} );
