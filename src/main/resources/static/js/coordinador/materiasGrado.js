$(document).ready(function() {
    $('#materiasGrado').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );
    
} );

function editarMateriaGrado(id){
    // let mes = document.getElementById("selMesCalendar").value;
    // $('#modalLoading').modal({
    //     backdrop: "static", //remove ability to close modal with click
    //     keyboard: false, //remove option to close with keyboard
    //     show: true //Display loader!
    //   });
    var url = "/coordinador/materiasGrado/" + id;
    $("#formEditarMateriasGrado").load(url, function(){
        $('#modalLoading').modal('hide');
        $("#modalFormEditarMateriasGrado").modal();
    });
    
    
}

function confirmDelete(id){
	$('#deleteModal').modal('show');
	$("#materiaGradoIdHiddenInput").val(id);
}

function deleteMateriaGrado(){
    window.location = "/coordinador/eliminarMateriasGrado/" + $("#materiaGradoIdHiddenInput").val();
}