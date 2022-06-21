$(document).ready(function () {
    $('.search_select_box select').selectpicker();
    $("#selCurso").change(function () {
        $("#tabla-horarios").addClass("loading");
        let idCurso = document.getElementById("selCurso").value;
        let url = "/coordinador/consultaHorario/" + idCurso;
        $("#seccionHorario").load(url, { limit: 25 },
            function (responseText, textStatus, req) {
                if (textStatus == "error") {
                    console.log("Error cargando el horario");
                    toastr.error('Inténtalo nuevamente', 'Ocurrió un error');
                } else {
                    $("#tabla-horarios").removeClass("loading");
                }
            });
    });

    $("#agregarMatHoras").on("keyup change", function () {
        let horas = $("#agregarMatHoras").val();
        if (horas != "" && horas < 1) {
            $("#agregarMatHoras").val(1);
            horas = 1;
        }
        let horaInicio = $("#agregarMatHorIni").val().substring(0, 2);
        let horaFin = 0 + parseInt(horaInicio) + parseInt(horas);
        if (horaFin < 10) {
            horaFin = "0" + horaFin;
        }
        $("#agregarMatHorFin").val(horaFin + ":00:00");
    })
});

function nuevoHorario(curso, dia, hora) {
    try {
        let id = document.getElementById(dia + hora).value;
    } catch {
        $("#btnSubmit").html("Guardar");
        $("#btnSubmit").prop("disabled", false);
        $("#agregarMatCurso").val(curso);
        $("#agregarMatDia").val(dia);
        $("#agregarMatHorIni").val(hora + ":00:00");
        let horaFin = 0 + parseInt(hora) + parseInt(1);
        if (horaFin < 10) {
            horaFin = "0" + horaFin;
        }
        $("#agregarMatHorFin").val(horaFin + ":00:00");
        $("#agregarMatMateria").prop("selectedIndex", 0);
        $("#agregarMatMateria").selectpicker("refresh");
        $("#agregarMatProfesor").prop("selectedIndex", 0);
        $("#agregarMatProfesor").selectpicker("refresh");
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
    $("#btnEliminar").html("<i class='fa fa-spinner fa-spin'></i>");
    $("#btnEliminar").prop("disabled", true);
    $("#btnCancelar").prop("hidden",true);
    
    let idHorario = document.getElementById("infoIdHorario").value;
    let url = "/coordinador/eliminarHorario/" + idHorario;
    $("#seccionHorario").load(url, { limit: 25 },
        function (responseText, textStatus, req) {
            if (textStatus != "error") {
                toastr.options.positionClass = 'toast-bottom-right';
                toastr.success("Se ha eliminado la materia", "Horario actualizado");
                $('#deleteModalHorario').modal('hide');
                $("#btnEliminar").html("Eliminar materia");
                $("#btnEliminar").prop("disabled", false);
                $("#btnCancelar").prop("hidden",false);
            } else {
                toastr.error("Inténtalo nuevamente", "Ocurrió un error");
                $("#btnEliminar").html("Eliminar materia");
                $("#btnEliminar").prop("disabled", false);
                $("#btnCancelar").prop("hidden",false);
            }
        });
        
}

function confirmarEliminarHorario() {
    $('#deleteModalHorario').modal();
    $('#modalInfoMaterias').modal('hide');
    setTimeout(function () {
        $("#btnEliminar").focus();
    }, 500)
}

function abrirInfoMateria() {
    $('#deleteModalHorario').modal('hide');
    $('#modalInfoMaterias').modal();
}

function guardarHorario() {

    let idCurso = $("#selCurso").val();
    let dia = $("#agregarMatDia").val();
    let horaInicio = $("#agregarMatHorIni").val();
    let horaFin = $("#agregarMatHorFin").val();
    let idMateria = $("#agregarMatMateria").val();
    let idProfesor = $("#agregarMatProfesor").val();

    if (idMateria == "") {
        $("#agregarMatMateria").focus();
        $("#agregarMatMateria").selectpicker('toggle');
        toastr.options.positionClass = 'toast-top-right';
        toastr.warning("Seleccione la materia", '', { timeOut: 2000 });
        return;
    }

    if (idProfesor == "") {
        $("#agregarMatProfesor").focus();
        $("#agregarMatProfesor").selectpicker('toggle');
        toastr.options.positionClass = 'toast-top-right';
        toastr.warning("Seleccione el profesor", '', { timeOut: 2000 });
        return;
    }

    if (idMateria != "" && idProfesor != "") {
        $("#btnSubmit").html("<i class='fa fa-spinner fa-spin'></i>");
        $("#btnSubmit").prop("disabled", true);
        let url = "/coordinador/agregarHorario/" + idCurso + '/' + dia + '/' + horaInicio + '/' + horaFin + '/' + idMateria + '/' + idProfesor;
        $("#seccionHorario").load(url, { limit: 25 },
            function (responseText, textStatus, req) {
                if (textStatus != "error") {
                    toastr.options.positionClass = 'toast-bottom-right';
                    toastr.success("Se ha agregado la materia","Horario actualizado");
                    $('#modalAgregarMateria').modal('hide');
                } else {
                    toastr.error("Inténtalo nuevamente", "Ocurrió un error");
                    $("#btnSubmit").html("Guardar");
                    $("#btnSubmit").prop("disabled", false);
                }
            });
    }


}

