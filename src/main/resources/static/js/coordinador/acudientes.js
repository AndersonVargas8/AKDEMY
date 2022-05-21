$(document).ready(function() {
    $('#tableAttendees').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );

} );


function editarAcudiente(id){


    var url = "/coordinador/acudientes/" + id;
    $("#formEditarAcudientes").load(url, function(){
        // $('#modalLoading').modal('hide');
        $("#modalFormEditarAcudientes").modal();
    });

}

function activarModalNuevo(errorCrear,errorEditar){
    if(errorCrear == true){
        $("#modalFormAcudientes").modal();
    }
    if(errorEditar == true){
        $("#modalFormEditarAcudientes").modal();
    }
}

function confirmDeleteAcudiente(id){
    $('#deleteModalAcudiente').modal();
    $("#acudienteIdHiddenInput").val(id);
}

function deleteAcudiente(){
    let id = $("#acudienteIdHiddenInput").val();
    var url = "/coordinador/eliminarAcudiente/" + id;
    $("#listaAcudientes").load(url);

    $('#deleteModalAcudiente').modal('hide');

    // window.location = "/coordinador/eliminarEstudiante/" + ;
}