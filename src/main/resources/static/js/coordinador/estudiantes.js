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
    $('#modalLoading').modal({
        backdrop: "static", //remove ability to close modal with click
        keyboard: false, //remove option to close with keyboard
        show: true //Display loader!
      });

    var url = "/coordinador/estudiantes/" + id;
    $("#formEditarEstudiantes").load(url, function(){
        $('#modalLoading').modal('hide');
        $("#modalFormEditarEstudiantes").modal();
    });
    
    
}