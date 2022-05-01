$(document).ready(function() {
    $('#materias').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );
    
} );

function editarMateria(id){
    // let mes = document.getElementById("selMesCalendar").value;
    $('#modalLoading').modal({
        backdrop: "static", //remove ability to close modal with click
        keyboard: false, //remove option to close with keyboard
        show: true //Display loader!
      });
    var url = "/coordinador/materias/" + id;
    $("#formEditarMaterias").load(url, function(){
        $('#modalLoading').modal('hide');
        $("#modalFormEditarMaterias").modal();
    });
    
    
}

function confirmDelete(id){
	$('#deleteModal').modal('show');
	$("#materiaIdHiddenInput").val(id);
}

function deleteMateria(){
    window.location = "/coordinador/eliminarMaterias/" + $("#materiaIdHiddenInput").val();
}