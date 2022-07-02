
$('#red').on('click', function () {
     toastr.options.closeButton = true;
     toastr.options.positionClass = 'toast-bottom-right';
     toastr.options.progressBar = true;
     toastr.options.timeOut = 3000;
     $.ajax({
          type: "post",
          url: "/guardarDatos",
          data: "Hola",
          dataType: "text",
          contentType: "text/plain",
          success:function(response){
               let data = JSON.parse(response);
               consulta(response);
               toastr.success('Dato guardado');
               $("#red").html(data.nombres);
          },
          error:function(response){
               toastr.error('Ocurrió un error','');
               $("#red").html("error");
          }
     })
     
     //
    });

function consulta(dato){
     console.log(dato);

     $.ajax({
          type: "POST",
          url: "/leeDatos",
          data: dato,
          success:function(response){
               console.log(response);
               toastr.success("Todo salió bien");
          },
          error:function(){
               toastr.error('Ocurrió un error','');
          }
     })
}