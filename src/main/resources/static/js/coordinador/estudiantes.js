$(document).ready(function () {
    datatable = createDatatable();
    $('.search_select_box select').selectpicker();

    //Guardar estudiante
    $("#estudiantesForm").submit(function (e) {
        e.preventDefault();
        activateLoadingButton($("#btnSubmit"), true);

        $.ajax({
            type: "post",
            url: "/coordinador/estudiantes",
            data: $(this).serialize(),
            success: function () {
                toastr.options.positionClass = 'toast-bottom-right';
                toastr.success("Estudiante guardado");
                desactivateLoadingButton($("#btnSubmit"), "Guardar", false);
                closeModal($("#modalFormEstudiantes"));
                cargarEstudiantes();
                resetForm();
            },
            error: function (jqXHR) {
                if (jqXHR.status && jqXHR.status == 406) {
                    desactivateLoadingButton($("#btnSubmit"), "Guardar", false);
                    defaultErrorNotify();
                    $("#errorDoc").prop("hidden",false);
                }else if (jqXHR.status && jqXHR.status == 400) {
                    alert("user");
                }else{
                    defaultErrorNotify();
                }
            }
        })
    });
});

function createDatatable() {
    datatable = $('#tablaEstudiantes').DataTable(
        {
            "order": [[1, "asc"]],
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            drawCallback: function () {
                $('#tablaEstudiantes').prop("hidden", false);
            }
        }
    );
    return datatable;
}
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
            $("#btnSubmit").prop("disabled", false);
        },
        error: function (jqXHR) {
            if (jqXHR.status && jqXHR.status == 406) {
                $("#verificandoLabel").prop("hidden", true);
                $("#noDisponibleLabel").prop("hidden", false);
                $("#btnSubmit").prop("disabled", true);
            } else {
                alert("Error :(");
            }
        }
    })
}

controladorTiempo = "";
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

function cargarEstudiantes() {
    activateLoadingTable($("#tablaEstudiantes"));
    let url = "/cargarEstudiantes";
    $("#listaEstudiantes").load(url, { limit: 25 },
        function (textStatus) {
            if (textStatus == "error") {
                defaultErrorNotify();
            } else {
                datatable.destroy();
                datatable = createDatatable();
                desactivateLoadingTable($("#tablaEstudiantes"));
            }
        })
}

function resetForm() {
    document.getElementById("estudiantesForm").reset();
    resetSelectPicker($("#selectEPS"));
    $("#errorDoc").prop("hidden",true);
    $("#disponibleLabel").prop("hidden",true);
    $("#noDisponibleLabel").prop("hidden",true);
}

function editarEstudiante(id) {
    $("#div-loading").prop("hidden", false);
    $("#editarEstudiantesForm").remove();
    $("#modalFormEditarEstudiantes").modal();

    var url = "/coordinador/estudiantes/" + id;
    $("#formEditarEstudiantes").load(url, function () {
        $("#div-loading").prop("hidden", true);
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
    setTimeout(function () {
        $("#btnEliminar").focus();
    }, 500)
}


function deleteEstudiante() {
    activateLoadingButton($("#btnEliminar"), true);
    $("#btnCancelar").prop("hidden", true);
    
    let id = $("#estudianteIdHiddenInput").val();
    $.ajax({
        type: 'post',
        url: "/coordinador/eliminarEstudiante/" + id,
        success: function () {
            $('#deleteModalEstudiante').modal('hide');
            desactivateLoadingButton($("#btnEliminar"), "Eliminar estudiante", false);
            $("#btnCancelar").prop("hidden", false);
            toastr.options.positionClass = 'toast-bottom-right';
            toastr.success("Estudiante eliminado");
            cargarEstudiantes();
            resetForm();
        },
        error: function () {
            defaultErrorNotify();
            desactivateLoadingButton($("#btnEliminar"), "Eliminar estudiante", false);
            $("#btnCancelar").prop("hidden", false);
        }
    });
}