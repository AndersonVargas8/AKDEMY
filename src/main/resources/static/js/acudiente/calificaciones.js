$(document).ready(function() {
    $('select').selectpicker();
    $('#califEstu').hide();
    $('#estudiantesSelect').change(function() {

        var url = "calificaciones/" + $(this).val();
        $('#califEstu').load(url, function(){
            $('#califEstu').show();
        });
    });

    let unicoEstudiante = $("#unicoEstudiante").val();
    if (unicoEstudiante > 0) {
        $("#estudiantesSelect").prop("selectedIndex", 1);
        $("#estudiantesSelect").selectpicker("refresh");

        var url = "calificaciones/" + unicoEstudiante;
        $('#califEstu').load(url, function(){
            $('#califEstu').show();
        });
    }
});