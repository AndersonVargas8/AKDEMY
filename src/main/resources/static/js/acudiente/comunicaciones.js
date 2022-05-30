$(document).ready(function() {
    table = $('#difusionesTable').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },

            bDestroy: true,
            order: [[ 0, "desc" ]],
        }
    );

    tableChat = $('#chatsTable').DataTable(
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
    $('#estudiantes').change(function() {
        var url = "comunicaciones/difusiones/" + $(this).val();
        $('#difusiones').load(url);

        table.rows().remove().draw();
        
    })

    $('#cursosChat').change(function() {
        var url = "comunicaciones/estudiantes/" + $(this).val();
        $('#estudiantes').load(url);
        
    })


    $('#estudiantesSelect').change(function() {
        // Set value on hidden input for form
       $('#estudianteid').val($(this).val());

       var url = "comunicaciones/profesores/" + $(this).val();
       $('#profesores').load(url);

    

    })

    $('#profesoresSelect').change(function() {
        alert()
        // Set value on hidden input for form
       $('#profesorid').val($(this).val());
    })

} );


function openChat(id){
    window.location.replace("./comunicaciones/chat/" + id)
}

function goComunicaciones(){
    window.location.replace("../../comunicaciones")
}