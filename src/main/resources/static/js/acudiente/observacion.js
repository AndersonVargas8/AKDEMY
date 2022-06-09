$(document).ready(function() {
    table = $('#observacionTable').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },

            bDestroy: true,
            order: [[ 0, "desc" ]]
        }
    );
    $('select').selectpicker();
    $('#estudiantesSelect').change(function() {

        var url = "observaciones/" + $(this).val();
        $('#observador').load(url, function(){
            
        });
    });

    
} );