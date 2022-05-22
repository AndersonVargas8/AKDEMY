$(document).ready(function () {
    $("#selCurso").change(function () {
        let idCurso = document.getElementById("selCurso").value;
        let url = "/coordinador/consultaHorario/" + idCurso;
        $("#seccionHorario").load(url);
    });
    $("#agregarMatHoras").change(function () {
        let horas = $("#agregarMatHoras").val();
        let horaInicio = $("#agregarMatHorIni").val().substring(0,2);
        let horaFin = 0 + parseInt(horaInicio) + parseInt(horas);
        if(horaFin<10){
            horaFin = "0" + horaFin;
        }
        $("#agregarMatHorFin").val(horaFin + ":00:00");
    });
});

function nuevoHorario(curso,dia,hora) {
    try {
        let id = document.getElementById(dia+hora).value;
    } catch {
        $("#agregarMatCurso").val(curso);
        $("#agregarMatDia").val(dia);
        $("#agregarMatHorIni").val(hora+":00:00");
        let horaFin = 0 + parseInt(hora) + parseInt(1);
        if(horaFin<10){
            horaFin = "0" + horaFin;
        }
        $("#agregarMatHorFin").val(horaFin + ":00:00");
        $("#agregarMatMateria").get(0).selectedIndex = 0;
        $("#agregarMatProfesor").get(0).selectedIndex = 0;
        $("#agregarMatHoras").val(1);
        $('#modalAgregarMateria').modal();
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

function guardarHorario(){
    let idCurso = $("#selCurso").val();
    let dia = $("#agregarMatDia").val();
    let horaInicio = $("#agregarMatHorIni").val();
    let horaFin = $("#agregarMatHorFin").val();
    let idMateria = $("#agregarMatMateria").val();
    let idProfesor = $("#agregarMatProfesor").val();

    
    if(idProfesor == ""){    
        $("#agregarMatProfesor").focus();
    }
    
    if(idMateria == ""){    
        $("#agregarMatMateria").focus();
    }
    if(idMateria != "" && idProfesor != ""){
        let url = "/coordinador/agregarHorario/" + idCurso + '/' + dia + '/' + horaInicio + '/' + horaFin + '/' + idMateria + '/' + idProfesor;
        $("#seccionHorario").load(url);
        $('#modalAgregarMateria').modal('hide');
    }


}