$(document).ready(function() {
    table = $('#difusionesTable').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },

            bDestroy: true,
            order: [[ 0, "desc" ]]
        }
    );
    $('select').selectpicker();
    $('#cursos').change(function() {
        var url = "comunicaciones/difusiones/" + $(this).val();
        $('#difusiones').load(url);

        table.rows().remove().draw();

        var url2 = "comunicaciones/difusiones/new/{id}" + $(this).val

        $('#formNewDifusion').load(url2);
        
    })

} );

function confirmDeleteObservacion(id){
	$('#deleteModalObservacion').modal('show');
	$("#observacionIdHiddenInput").val(id);
}

function deleteObservacion(){
    window.location = "observador/eliminar/" + $("#observacionIdHiddenInput").val();
}