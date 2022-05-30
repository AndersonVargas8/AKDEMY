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
    $('#cursoEstu').prop('hidden',true);
    $('#estudiantesSelect').change(function() {

        var url = "horarios/" + $(this).val();
        $('#horarioEst').load(url, function(){
            $('#cursoEstu').prop('hidden',false);
        });
    })
} );

function infoMateria(idHorario, nombreMateria, dia, horaInicio, horaFin, nombresProfesor, apellidosProfesor, descMateria,nombreCurso) {
    //alert(nombreCurso);
    $("#infoIdHorario").val(idHorario);
    $("#infoNombreMateria").html(nombreMateria);
    $("#infoDiaHora").html(dia + ' | ' + horaInicio + ' - ' + horaFin);
    $("#infoNombreProfesor").html(nombresProfesor + " " + apellidosProfesor);
    $("#infoDescMateria").html(descMateria);
    $("#infoCurso").html(nombreCurso);
    $('#modalInfoMaterias').modal();
}

