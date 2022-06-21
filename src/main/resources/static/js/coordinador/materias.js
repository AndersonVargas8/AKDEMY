$(document).ready(function () {
    datatable = createDatatable();


    //Guardar materia
    $("#materiasForm").submit(function (e) {
        activateLoadingButton($("#btnSubmit"), true);
        e.preventDefault();

        $.ajax({
            type: "post",
            url: "/savemateria",
            data: $(this).serialize(),
            success: function () {
                toastr.options.positionClass = 'toast-bottom-right';
                toastr.success("Materia guardada");
                desactivateLoadingButton($("#btnSubmit"), "Guardar", false);
                closeModal($("#modalFormMateria"));
                cargarMaterias();
            },
            error: function () {
                desactivateLoadingButton($("#btnSubmit"), "Guardar", false);
                defaultErrorNotify();
            }
        })
    });


});
function createDatatable() {
    datatable = $('#materias').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            drawCallback: function () {
                $("#materias").prop("hidden", false);
            }
        },
    );
    return datatable;
}

function cargarMaterias() {
    activateLoadingTable($("#materias"));
    let url = "/cargarMaterias";
    $("#materias").load(url, { limit: 25 },
        function (textStatus) {
            if (textStatus == "error") {
                defaultErrorNotify();
            } else {
                $("#name").val("");
                datatable.destroy();
                datatable = createDatatable();
                desactivateLoadingTable($("#materias"));
            }
        })
}

function crearMateria() {
    setTimeout(function () {
        $("#name").focus();
    }, 500)
}

function editarMateria(id) {
    $("#div-loading").prop("hidden", false);
    $("#editarMateriasForm").remove();
    var url = "/coordinador/materias/" + id;

    $("#modalFormEditarMaterias").modal();

    $("#formEditarMaterias").load(url, function () {
        $("#div-loading").prop("hidden", true);
    });
}

function confirmDelete(id) {
    $('#deleteModal').modal('show');
    $("#materiaIdHiddenInput").val(id);
    setTimeout(function () {
        $("#btnEliminar").focus();
    }, 500);
}

function deleteMateria() {
    activateLoadingButton($("#btnEliminar"), true);
    $("#btnCancelar").prop("hidden", true);

    $.ajax({
        type: 'post',
        url: "/coordinador/eliminarMaterias/" + $("#materiaIdHiddenInput").val(),
        success: function () {
            $('#deleteModal').modal('hide');
            desactivateLoadingButton($("#btnEliminar"), "Eliminar materia", false);
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
