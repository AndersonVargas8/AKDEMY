$(document).ready(function () {
    datatable = createDatatable();
    //Guardar curso
    $("#cursosForm").submit(function (e) {
        e.preventDefault();
        valid = verificateForm($("#director"))
        if (!valid) {
            return;
        }
        activateLoadingButton($("#btnSubmit"), true);

        $.ajax({
            type: "post",
            url: "/savecurso",
            data: $(this).serialize(),
            success: function () {
                toastr.options.positionClass = 'toast-bottom-right';
                toastr.success("Curso guardado");
                desactivateLoadingButton($("#btnSubmit"), "Guardar", false);
                closeModal($("#modalFormCurso"));
                cargarCursos();
                resetForm();
            },
            error: function () {
                desactivateLoadingButton($("#btnSubmit"), "Guardar", false);
                defaultErrorNotify();
            }
        })
    });

});
function createDatatable() {
    datatable = $('#courses').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            drawCallback: function () {
                $("#courses").prop("hidden", false);
            }
        },
    );
    return datatable;
}

function verificateForm(select) {
    let cursoId = select.val();
    if (cursoId == 0) {
        select.selectpicker('toggle');
        toastr.options.positionClass = 'toast-top-right';
        toastr.warning("Seleccione el director", '', { timeOut: 2000 });
        return false;
    }

    return true;
}

function cargarCursos() {
    activateLoadingTable($("#courses"));
    let url = "/cargarCursos";
    $("#courses").load(url, { limit: 25 },
        function (textStatus) {
            if (textStatus == "error") {
                defaultErrorNotify();
            } else {
                datatable.destroy();
                datatable = createDatatable();
                desactivateLoadingTable($("#courses"));
            }
        })
}

function resetForm() {
    document.getElementById("cursosForm").reset();
    resetSelectPicker($("#director"));
}

function verEstudiantes(id) {
    window.location = "/coordinador/cursos/estudiantes/" + id;
}

function editarCurso(id) {
    $("#div-loading").prop("hidden", false);
    $("#editarCursosForm").remove();
    $("#modalFormEditarCursos").modal();

    var url = "/coordinador/cursos/" + id;

    $("#formEditarCursos").load(url, function () {
        $("#div-loading").prop("hidden", true);
    });


}

function confirmDelete(id) {
    $('#deleteModal').modal('show');
    $("#courseIdHiddenInput").val(id);
    setTimeout(function () {
        $("#btnEliminar").focus();
    }, 500)
}


function deleteCurso() {
    activateLoadingButton($("#btnEliminar"), true);
    $("#btnCancelar").prop("hidden", true);

    $.ajax({
        type: 'post',
        url: "/coordinador/eliminarCursos/" + $("#courseIdHiddenInput").val(),
        success: function () {
            $('#deleteModal').modal('hide');
            desactivateLoadingButton($("#btnEliminar"), "Eliminar curso", false);
            $("#btnCancelar").prop("hidden", false);
            toastr.options.positionClass = 'toast-bottom-right';
            toastr.success("Curso eliminado");
            cargarCursos();
        },
        error: function () {
            defaultErrorNotify();
            desactivateLoadingButton($("#btnEliminar"), "Eliminar curso", false);
            $("#btnCancelar").prop("hidden", false);
        }
    });
}