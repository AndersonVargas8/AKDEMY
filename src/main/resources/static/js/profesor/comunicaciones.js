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

    tableChat = $('#chatsTable').DataTable(
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

        var url2 = "comunicaciones/difusiones/new/" + $(this).val();

        $('#formNewDifusion').load(url2);
        
    })

    $('#cursosChat').change(function() {
        var url = "comunicaciones/estudiantes/" + $(this).val();
        $('#estudiantes').load(url);
        
    })


    $('#estudiantesSelect').change(function() {

       alert("change")

    })

} );

function confirmDeleteDifusion(id, curso){
	$('#deleteModalDifusion').modal('show');
	$("#difusionIdHiddenInput").val(id);
    $("#cursoIdHiddenInput").val(curso);
}

function deleteDifusion(){
    window.location = "comunicaciones/difusiones/delete/" + $("#difusionIdHiddenInput").val() + "/" + $("#cursoIdHiddenInput").val();
}