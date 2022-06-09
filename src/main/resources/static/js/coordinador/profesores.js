$(document).ready(function () {
    $('#teachers').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );

    $('#selectuser').selectpicker();

    let controladorTiempoa = "";
    $("#username").on("keyup", function () {
        $("#verificandoLabel").prop("hidden", false);
        $("#disponibleLabel").prop("hidden", true);
        $("#noDisponibleLabel").prop("hidden", true);
        $("#errorUsername").prop("hidden", true);
        clearTimeout(controladorTiempoa);
        controladorTiempoa = setTimeout(function () {
            verificaUsername($("#username").val());
        }, 500);
    });

});

function editarProfesor(id) {
    /*$('#modalLoading').modal({
        backdrop: "static", //remove ability to close modal with click
        keyboard: false, //remove option to close with keyboard
        show: true //Display loader!
      });*/

    var url = "/coordinador/profesores/" + id;
    $("#formEditarProfesor").load(url, function () {
        $('#modalLoading').modal('hide');
        $("#modalFormEditarProfesor").modal();
    });

};

function confirmDeleteProfesor(id) {
    $('#deleteModalProfesor').modal('show');
    $("#profesorIdHiddenInput").val(id);
};

function deleteProfesor() {
    window.location = "/coordinador/eliminarProfesor/" + $("#profesorIdHiddenInput").val();
};

function crearUsuario() {
    $("#selectuser").val(0);
    $("#selectUser").prop("hidden", true);
    $("#createUser").prop("hidden", false);
    $("#username").prop("required", true);
    $("#selectuser").prop("required", false);
};

function seleccionarUsuario() {
    $("#bsubmit").prop("disabled", false);
    $("#disponibleLabel").prop("hidden", true);
    $("#noDisponibleLabel").prop("hidden", true);
    $("#username").val(null);
    $("#selectuser").prop("required", true);
    $("#selectuser").prop("selectedIndex", 0);
    $("#selectuser").selectpicker("refresh");
    $("#selectUser").prop("hidden", false);
    $("#createUser").prop("hidden", true);
    $("#username").prop("required", false);
};

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
};

