 $(document).ready(function() {
    $('#courses').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
        }
    );
    
} );

function editarCurso(id){
    // let mes = document.getElementById("selMesCalendar").value;
    var url = "/coordinador/cursos/" + id;
    $("#formEditarCursos").load(url);
    
    $("#modalFormEditarCursos").modal();
}