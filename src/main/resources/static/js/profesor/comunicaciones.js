$(document).ready(function() {
    let table = $('#difusionesTable').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },

            bDestroy: true,
            order: [[ 0, "desc" ]],
            columnDefs: [
                {
                    targets: -1,
                    className: 'text-center'
                }
              ]
        }
    );

    let tableChat = $('#chatsTable').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },

            bDestroy: true,
            order: [[ 0, "desc" ]],
            columnDefs: [
                {
                    targets: -1,
                    className: 'text-center'
                }
              ]
        
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
        // Set value on hidden input for form
       $('#estudianteid').val($(this).val());
       
       // Find acudientes estudiantes
       var url = "comunicaciones/acudientes/" + $(this).val();

       $('#acudientes').load(url);

    })

    $('#acudientesSelect').change(function() {
        // Set value on hidden input for form
       $('#acudienteid').val($(this).val());
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

function openChat(id){
    window.location.replace("./comunicaciones/chat/" + id)
}

function goComunicaciones(){
    window.location.replace("../../comunicaciones")
}