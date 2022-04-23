$(document).ready(function() {
    $('#tablaEstudiantes').DataTable(
        {   
            "order": [[1, "asc"]],
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );
    $('.search_select_box select').selectpicker();
} );

function editarEstudiante(id){
    // let mes = document.getElementById("selMesCalendar").value;
    var url = "/coordinador/estudiantes/" + id;
    $("#formEditarEstudiantes").load(url);
    
    $("#modalFormEditarEstudiantes").modal();
}