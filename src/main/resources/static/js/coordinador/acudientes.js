$(document).ready(function() {
    $('#tableAttendees').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );

    $('.search_select_box2 select').selectpicker();
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


function editarAcudiente(id){
    var url = "/coordinador/acudientes/" + id;
    $("#formEditarAcudientes").load(url, function(){
        $('#modalLoading').modal('hide');
        $("#modalFormEditarAcudientes").modal();
    });

}

function confirmDeleteAcudiente(id){
    $('#deleteModalAcudiente').modal();
    $("#acudienteIdHiddenInput").val(id);
}

function deleteAcudiente(){
    /* let id = $("#acudienteIdHiddenInput").val();
    var url = "/coordinador/eliminarAcudiente/" + id;
    $("#listaAcudientes").load(url);

    $('#deleteModalAcudiente').modal('hide'); */

    window.location = "/coordinador/eliminarAcudiente/" + $("#acudienteIdHiddenInput").val();

    // window.location = "/coordinador/eliminarEstudiante/" + ;
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

