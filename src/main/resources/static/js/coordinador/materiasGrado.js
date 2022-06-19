$(document).ready(function () {
    datatable = createDatatable();

    //Guardar materia grado
    $("#materiasGradoForm").submit(function (e) {
        e.preventDefault();
        valid = verificateForm($("#materia"))
        if (!valid) {
            return;
        }
        activateLoadingButton($("#btnSubmit"), true);

        $.ajax({
            type: "post",
            url: "/savemateriaGrado",
            data: $(this).serialize(),
            success: function () {
                toastr.options.positionClass = 'toast-bottom-right';
                toastr.success("Materia guardada");
                desactivateLoadingButton($("#btnSubmit"), "Guardar", false);
                closeModal($("#modalFormMateriaGrado"));
                cargarMaterias();
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
    datatable = $('#materiasGrado').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            drawCallback: function () {
                $("#materiasGrado").prop("hidden", false);
            }
        },
    );
    return datatable;
}

function cargarMaterias() {
    activateLoadingTable($("#materiasGrado"));
    let url = "/cargarMateriasGrado";
    $("#materiasGrado").load(url, { limit: 25 },
        function (textStatus) {
            if (textStatus == "error") {
                defaultErrorNotify();
            } else {
                $("#name").val("");
                datatable.destroy();
                datatable = createDatatable();
                desactivateLoadingTable($("#materiasGrado"));
            }
        })
}

function resetForm() {
    document.getElementById("materiasGradoForm").reset();
    resetSelectPicker($("#materia"));
}

function verificateForm(select) {
    let materiaId = select.val();
    if (materiaId == 0) {
        select.selectpicker('toggle');
        toastr.options.positionClass = 'toast-top-right';
        toastr.warning("Seleccione la materia", '', { timeOut: 2000 });
        return false;
    }

    return true;
}

function editarMateriaGrado(id) {
    $("#div-loading").prop("hidden", false);
    $("#editarMateriasGradoForm").remove();
    $("#modalFormEditarMateriasGrado").modal();

    var url = "/coordinador/materiasGrado/" + id;

    $("#formEditarMateriasGrado").load(url, function () {
        $("#div-loading").prop("hidden", true);
    });
}

function confirmDelete(id) {
    $('#deleteModal').modal('show');
    $("#materiaGradoIdHiddenInput").val(id);
    setTimeout(function () {
        $("#btnEliminar").focus();
    }, 500);
}


function deleteMateriaGrado() {
    activateLoadingButton($("#btnEliminar"), true);
    $("#btnCancelar").prop("hidden", true);

    $.ajax({
        type: 'post',
        url: "/coordinador/eliminarMateriasGrado/" + $("#materiaGradoIdHiddenInput").val(),
        success: function () {
            $('#deleteModal').modal('hide');
            desactivateLoadingButton($("#btnEliminar"), "Eliminar materia grado", false);
            $("#btnCancelar").prop("hidden", false);
            toastr.options.positionClass = 'toast-bottom-right';
            toastr.success("Materia eliminada");
            cargarMaterias();
        },
        error: function () {
            defaultErrorNotify();
            desactivateLoadingButton($("#btnEliminar"), "Eliminar materia", false);
            $("#btnCancelar").prop("hidden", false);
        }
    });
}