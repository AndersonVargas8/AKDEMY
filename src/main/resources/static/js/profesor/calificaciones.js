$(document).ready(function() {
    $('select').selectpicker();
});

$('#cursos').change(function() {
    if($(this).val() == 0){
        $("#materias").prop('hidden',true);
        return;
    }

    var url = "/profesor/calificaciones/materiasCurso/" + $(this).val();
    $('#materias').load(url,function(){
        $("#materias").prop('hidden',false);
    });
    
})