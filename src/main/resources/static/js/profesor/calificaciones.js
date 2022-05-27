$(document).ready(function() {
    table = $('#estudiantesTable').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },

            bDestroy: true,
            order: [[ 1, "asc" ]]
        }
    );
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
        $("#listaEstudiantes").prop('hidden',true);
    });
    
});

$("#materiasSelect").change(function(){
    let curso = $("#cursos").val();
    let url = "/profesor/calificaciones/estudiantesCurso/" + curso;
    $("#listaEstudiantes").load(url);
    $("#listaEstudiantes").prop("hidden",false);
});


function evaluarNota(id){
    let valor = $("#nota"+id).val();
    if(valor < 0 || valor > 5 || valor == ""){
        $("#nota"+id).val("");
        $("#check"+id).prop("hidden",true);
        $("#x"+id).prop("hidden",true);
        return;
    }

    if(valor >= 3 ){
        $("#check"+id).prop("hidden",false);
        $("#x"+id).prop("hidden",true);
    }else{
        $("#x"+id).prop("hidden",false);
        $("#check"+id).prop("hidden",true);
    }
}