$(document).ready(function() {
    $('#courses').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );
    
} );

function verEstudiantes(id){
    window.location="/coordinador/cursos/estudiantes/"+id;
}

function editarCurso(id){
    // let mes = document.getElementById("selMesCalendar").value;
    $('#modalLoading').modal({
        backdrop: "static", //remove ability to close modal with click
        keyboard: false, //remove option to close with keyboard
        show: true //Display loader!
      });
    var url = "/coordinador/cursos/" + id;
    $("#formEditarCursos").load(url, function(){
        $('#modalLoading').modal('hide');
        $("#modalFormEditarCursos").modal();
    });
    
    
}

function confirmDelete(id){
	$('#deleteModal').modal('show');
	$("#courseIdHiddenInput").val(id);
}

function deleteCurso(){
    window.location = "/coordinador/eliminarCursos/" + $("#courseIdHiddenInput").val();
}