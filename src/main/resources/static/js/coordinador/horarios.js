$(document).ready(function () {
    $("#selCurso").change(function () {
        let idCurso = document.getElementById("selCurso").value;
        let url = "/coordinador/consultaHorario/" + idCurso;
        $("#seccionHorario").load(url);
    });
});

function nuevoHorario(diaHora) {
    try {
        let id = document.getElementById(diaHora).value;
    } catch {
        alert("nuevo horario " + diaHora);
    }
}

function infoMateria(idHorario, nombreMateria, dia, horaInicio, horaFin, nombresProfesor, apellidosProfesor, descMateria) {
    //alert(idHorario)
    $("#infoIdHorario").val(idHorario);
    $("#infoNombreMateria").html(nombreMateria);
    $("#infoDiaHora").html(dia + ' | ' + horaInicio + ' - ' + horaFin);
    $("#infoNombreProfesor").html(nombresProfesor + " " + apellidosProfesor);
    $("#infoDescMateria").html(descMateria);
    $('#modalInfoMaterias').modal();
}

function eliminarHorario() {
    let idHorario = document.getElementById("infoIdHorario").value;
    let url = "/coordinador/eliminarHorario/" + idHorario;
    $("#seccionHorario").load(url);
    $('#deleteModalHorario').modal('hide');
}

function confirmarEliminarHorario() {
    $('#deleteModalHorario').modal();
    $('#modalInfoMaterias').modal('hide');
}

function abrirInfoMateria() {
    $('#deleteModalHorario').modal('hide');
    $('#modalInfoMaterias').modal();
}
