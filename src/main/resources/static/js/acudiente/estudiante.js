$(document).ready(function() {
    $('#estudiantesAcudienteTable').DataTable(
        {   
            "order": [[1, "asc"]],
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );
} );

function generarCertificado(id) {
    var url = "/acudiente/estudiantes/certificado/" + id;
    window.open(url, '_blank')
}

