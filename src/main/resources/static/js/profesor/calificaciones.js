$(document).ready(function () { 
    try{
        table = $('#estudiantesTable').DataTable(
            {
                order: [[1,'asc']],
                language: {
                    "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
                },
                bDestroy: true,
                "bPaginate":false
            }
        );
    }catch{}

    $('select').selectpicker();

    $('#cursos').change(function () {
        $("#periodos").prop('hidden', true);
        if ($(this).val() == 0) {
            $("#materias").prop('hidden', true);
            return;
        }

        var url = "/profesor/calificaciones/materiasCurso/" + $(this).val();
        $('#materias').load(url, function () {
            $("#materias").prop('hidden', false);
            $("#listaEstudiantes").prop('hidden', true);
        });

    });

});

function evaluarNota(id) {
    $("#cerrarNotasBtn").prop("disabled",true);
    $("#guardarBtn").prop("disabled",false);
    $("#guardadoLabel").prop("hidden",true);
    $("#cambiosLabel").prop("hidden",false);

    let valor = $("#nota" + id).val();
    if (valor < 0 || valor > 5 || valor == "") {
        $("#nota" + id).val("");
        $("#check" + id).prop("hidden", true);
        $("#x" + id).prop("hidden", true);
        return;
    }

    if (valor >= 3) {
        $("#check" + id).prop("hidden", false);
        $("#x" + id).prop("hidden", true);
    } else {
        $("#x" + id).prop("hidden", false);
        $("#check" + id).prop("hidden", true);
    }
}

$("#calificacionesForm").submit(function(e){
    $("#guardadoLabel").prop("hidden",true);
    $("#cambiosLabel").prop("hidden",true);
    $("#guardandoLabel").prop("hidden",false);
    e.preventDefault();
    $.ajax({
        type: 'post',
        url: '/profesor/calificaciones',
        data: $(this).serialize(),
        success: function(r){
            guardadoExitosamente();
        },
        error: function(){
            alert("Error :(");
        }
    });
})

function guardadoExitosamente(){
    //alert("Todo bien!");
    cargarListaGuardado();
}

function confirmCerrarNotas(){
    $("#cerrarCalModal").modal();
}

function cerrarNotas(){
    $("#guardadoLabel").prop("hidden",true);
    $("#cambiosLabel").prop("hidden",true);
    $("#guardandoLabel").prop("hidden",false);
    $("#cerrarCalModal").modal('hide');
    $.ajax({
        type: 'post',
        url: '/profesor/calificaciones/cerrar',
        data: $("#calificacionesForm").serialize(),
        success: function(r){
            cargarListaCerrado();
        },
        error: function(){
            alert("Error :(");
        }
    });
}

function cargarListaGuardado(){
    let curso = $("#cursos").val();
    let materia = $("#materiasSelect").val();
    let periodo = $("#periodosSelect").val();
    let url = "/profesor/calificaciones/estudiantesCalificaciones/" + curso + "/" + materia + "/" + periodo;
    $("#listaEstudiantes").load(url,function(){
        table.rows().remove().draw();
        $("#listaEstudiantes").prop("hidden", false);$("#cerrarNotasBtn").prop("disabled",false);
        $("#guardandoLabel").prop("hidden",true);
        $("#guardarBtn").prop("disabled",true);

    });
}

function cargarListaCerrado(){
    let curso = $("#cursos").val();
    let materia = $("#materiasSelect").val();
    let periodo = $("#periodosSelect").val();
    let url = "/profesor/calificaciones/estudiantesCalificaciones/" + curso + "/" + materia + "/" + periodo;
    $("#listaEstudiantes").load(url,function(){
        table.rows().remove().draw();
        $("#listaEstudiantes").prop("hidden", false);
        $("#cerrarNotasBtn").prop("hidden",true);
        $("#guardandoLabel").prop("hidden",true);
        $("#guardarBtn").prop("hidden",true);

    });
}