$(document).ready(function() {
    let table = $('#observadorTable').DataTable(
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
        var url = "observador/estudiantes/" + $(this).val();
        $('#estudiantes').load(url);

        table.rows().remove().draw();
        
    })

    $('#estudiantesSelect').change(function() {

        var url = "observador/" + $(this).val();
        $('#observador').load(url, function(){
            
        });

        var url2 = "observador/new/" + $(this).val();

        $('#formNewObservador').load(url2);

    })
} );

function confirmDeleteObservacion(id){
	$('#deleteModalObservacion').modal('show');
	$("#observacionIdHiddenInput").val(id);
}

function deleteObservacion(){
    window.location = "observador/eliminar/" + $("#observacionIdHiddenInput").val();
}