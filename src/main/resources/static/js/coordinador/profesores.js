$(document).ready(function() {
    $('#teachers').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );
    
} );

function editarProfesor(id){
    $('#modalLoading').modal({
        backdrop: "static", //remove ability to close modal with click
        keyboard: false, //remove option to close with keyboard
        show: true //Display loader!
      });
    var url = "/coordinador/profesores/" + id;
    $("#formEditarProfesor").load(url, function(){
        $('#modalLoading').modal('hide');
        $("#modalFormEditarProfesor").modal();});
    
    
}

function confirmDeleteProfesor(id){
	$('#deleteModalProfesor').modal('show');
	$("#profesorIdHiddenInput").val(id);
}

function deleteProfesor(){
    window.location = "/coordinador/eliminarProfesor/" + $("#profesorIdHiddenInput").val();
}