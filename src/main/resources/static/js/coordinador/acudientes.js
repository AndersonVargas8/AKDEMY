$(document).ready(function() {
    datatable = createDatatable();

    //Guardar acudiente
    $("#acudientesForm").submit(function (e) {
        e.preventDefault();
        valid = verificateForm($("#selectuser"))
        if (!valid) {
            return;
        }
        activateLoadingButton($("#btnSubmit"), true);

        $.ajax({
            type: "post",
            url: "/saveacudiente",
            data: $(this).serialize(),
            success: function () {
                toastr.options.positionClass = 'toast-bottom-right';
                toastr.success("Acudiente guardado");
                desactivateLoadingButton($("#btnSubmit"), "Guardar", false);
                closeModal($("#modalFormAcudiente"));
                cargarAcudientes();
                resetForm();
            },
            error: function () {
                desactivateLoadingButton($("#btnSubmit"), "Guardar", false);
                defaultErrorNotify();
            }
        })
    });

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
} );

function createDatatable() {
    datatable = $('#tableAttendees').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            drawCallback: function () {
                $('#tableAttendees').prop("hidden", false);
            }
        }
    );
    return datatable;
}

function verificateForm(select) {
    if(select.prop("required") == false){
        return true;
    }
    let profesorId = select.val();
    if (profesorId == 0) {
        select.selectpicker('toggle');
        toastr.options.positionClass = 'toast-top-right';
        toastr.warning("Seleccione el usuario", '', { timeOut: 2000 });
        return false;
    }

    return true;
}

function cargarAcudientes() {
    activateLoadingTable($("#tableAttendees"));
    let url = "/cargarAcudientes";
    $("#tableAttendees").load(url, { limit: 25 },
        function (textStatus) {
            if (textStatus == "error") {
                defaultErrorNotify();
            } else {
                datatable.destroy();
                datatable = createDatatable();
                desactivateLoadingTable($("#tableAttendees"));
            }
        })
}

function resetForm() {
    document.getElementById("acudientesForm").reset();
    resetSelectPicker($("#selectuser"));
    $(".search_select_box").load(location.href + " .search_select_box");
    setTimeout(function(){
        $("#selectuser").selectpicker("refresh");
    },500);
}

function editarAcudiente(id){
    $("#div-loading").prop("hidden", false);
    $("#editarAcudientesForm").remove();
    $("#modalFormEditarAcudientes").modal();

    var url = "/coordinador/acudientes/" + id;
    $("#formEditarAcudientes").load(url, function(){
        $("#div-loading").prop("hidden", true);
    });

}

function confirmDeleteAcudiente(id){
    $('#deleteModalAcudiente').modal();
    $("#acudienteIdHiddenInput").val(id);
    setTimeout(function () {
        $("#btnEliminar").focus();
    }, 500)
}



function deleteAcudiente() {
    activateLoadingButton($("#btnEliminar"), true);
    $("#btnCancelar").prop("hidden", true);

    $.ajax({
        type: 'post',
        url: "/coordinador/eliminarAcudiente/" + $("#acudienteIdHiddenInput").val(),
        success: function () {
            $('#deleteModalAcudiente').modal('hide');
            desactivateLoadingButton($("#btnEliminar"), "Eliminar acudiente", false);
            $("#btnCancelar").prop("hidden", false);
            toastr.options.positionClass = 'toast-bottom-right';
            toastr.success("Acudiente eliminado");
            cargarAcudientes();
            resetForm();
        },
        error: function () {
            defaultErrorNotify();
            desactivateLoadingButton($("#btnEliminar"), "Eliminar acudiente", false);
            $("#btnCancelar").prop("hidden", false);
        }
    });
}

function verEstudiantes(id){
    window.location="/coordinador/acudientes/estudiantes/"+id;
}

function vertodosEstudiantes(id){
    window.location="/coordinador/acudientes/estudiantes/listadoEstudiantes/"+id;
}

function crearUsuario() {
    $("#selectuser").val(0);
    $("#selectUser").prop("hidden", true);
    $("#createUser").prop("hidden", false);
    $("#username").prop("required", true);
    $("#selectuser").prop("required", false);
};

function seleccionarUsuario() {
    $("#btnSubmit").prop("disabled", false);
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
            $("#btnSubmit").prop("disabled", false);
        },
        error: function (jqXHR) {
            if (jqXHR.status && jqXHR.status == 406) {
                $("#verificandoLabel").prop("hidden", true);
                $("#noDisponibleLabel").prop("hidden", false);
                $("#btnSubmit").prop("disabled", true);
            } else {
                defaultErrorNotify();
            }
        }
    })
};

