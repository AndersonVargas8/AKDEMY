$(document).ready(function () {
    $('#tablaEstudiantes').DataTable(
        {
            "order": [[1, "asc"]],
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );
    $('.search_select_box select').selectpicker();
    $("#tablaEstudiantes").prop("hidden",false);
});

function verificaUsername(valor) {
    if (valor == null || valor == "") {
        $("#verificandoLabel").prop("hidden", true);
        return;
    }
    $.ajax({
        type: 'post',
        url: '/user/verificarUsuario',
        data: valor,
        dataType: "text",
        contentType: "text/plain",
        success: function (r) {
            $("#verificandoLabel").prop("hidden", true);
            $("#disponibleLabel").prop("hidden", false);
            $("#bsubmit").prop("disabled", false);
        },
        error: function (jqXHR) {
            if (jqXHR.status && jqXHR.status == 406) {
                $("#verificandoLabel").prop("hidden", true);
                $("#noDisponibleLabel").prop("hidden", false);
                $("#bsubmit").prop("disabled", true);
            } else {
                alert("Error :(");
            }
        }
    })
}

let controladorTiempo = "";
$("#username").on("keyup", function () {
    $("#verificandoLabel").prop("hidden", false);
    $("#disponibleLabel").prop("hidden", true);
    $("#noDisponibleLabel").prop("hidden", true);
    $("#errorUsername").prop("hidden", true);
    clearTimeout(controladorTiempo);
    controladorTiempo = setTimeout(function () {
        verificaUsername($("#username").val());
    }, 500);
});

function editarEstudiante(id) {
    // let mes = document.getElementById("selMesCalendar").value;
    // $('#modalLoading').modal({
    //     backdrop: "static", //remove ability to close modal with click
    //     keyboard: false, //remove option to close with keyboard
    //     show: true //Display loader!
    //   });

    var url = "/coordinador/estudiantes/" + id;
    $("#formEditarEstudiantes").load(url, function () {
        // $('#modalLoading').modal('hide');
        $("#modalFormEditarEstudiantes").modal();
    });


}

function activarModalNuevo(errorCrear, errorEditar) {
    if (errorCrear == true) {
        $("#modalFormEstudiantes").modal();
    }
    if (errorEditar == true) {
        $("#modalFormEditarEstudiantes").modal();
    }
}

function confirmDeleteEstudiante(id) {
    $('#deleteModalEstudiante').modal();
    $("#estudianteIdHiddenInput").val(id);
}

function deleteEstudiante() {
    let id = $("#estudianteIdHiddenInput").val();
    var url = "/coordinador/eliminarEstudiante/" + id;
    $("#listaEstudiantes").load(url);

    $('#deleteModalEstudiante').modal('hide');

    // window.location = "/coordinador/eliminarEstudiante/" + ;
}