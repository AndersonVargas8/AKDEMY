  // Import the functions you need from the SDKs you need

  import { initializeApp } from "https://www.gstatic.com/firebasejs/9.8.1/firebase-app.js";

  import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.8.1/firebase-analytics.js";
  import { getFirestore } from "https://www.gstatic.com/firebasejs/8.10.1/firebase-firestore.js";

  // TODO: Add SDKs for Firebase products that you want to use

  // https://firebase.google.com/docs/web/setup#available-libraries


  // Your web app's Firebase configuration

  // For Firebase JS SDK v7.20.0 and later, measurementId is optional

  const firebaseConfig = {

    apiKey: "AIzaSyD8Btm0KSfd1XkiS4Bdp7rB3dBr_59BQMk",

    authDomain: "akdemy-7d7bc.firebaseapp.com",

    databaseURL: "https://akdemy-7d7bc.firebaseio.com",

    projectId: "akdemy-7d7bc",

    storageBucket: "akdemy-7d7bc.appspot.com",

    messagingSenderId: "495875568042",

    appId: "1:495875568042:web:7d07737da34fd4aec7fbd3",

    measurementId: "G-2T84FML2RD"

  };


  // Initialize Firebase

  const app = initializeApp(firebaseConfig);

  const analytics = getAnalytics(app);

  // Initialize Cloud Firestore and get a reference to the service
  const db = getFirestore(app);






$(document).ready(function() {
    table = $('#difusionesTable').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },

            bDestroy: true,
            order: [[ 0, "desc" ]],
            columnDefs: [
                {
                    targets: -1,
                    className: 'text-center'
                }
              ]
        }
    );

    tableChat = $('#chatsTable').DataTable(
        {
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },

            bDestroy: true,
            order: [[ 0, "desc" ]],
            columnDefs: [
                {
                    targets: -1,
                    className: 'text-center'
                }
              ]
        
        }
    );

    $('select').selectpicker();
    $('#cursos').change(function() {
        var url = "comunicaciones/difusiones/" + $(this).val();
        $('#difusiones').load(url);

        table.rows().remove().draw();

        var url2 = "comunicaciones/difusiones/new/" + $(this).val();

        $('#formNewDifusion').load(url2);
        
    })

    $('#cursosChat').change(function() {
        var url = "comunicaciones/estudiantes/" + $(this).val();
        $('#estudiantes').load(url);
        
    })


    $('#estudiantesSelect').change(function() {
        // Set value on hidden input for form
       $('#estudianteid').val($(this).val());
       
       // Find acudientes estudiantes
       var url = "comunicaciones/acudientes/" + $(this).val();

       $('#acudientes').load(url);

    })

    $('#acudientesSelect').change(function() {
        // Set value on hidden input for form
       $('#acudienteid').val($(this).val());
    })

} );

function confirmDeleteDifusion(id, curso){
	$('#deleteModalDifusion').modal('show');
	$("#difusionIdHiddenInput").val(id);
    $("#cursoIdHiddenInput").val(curso);
}

function deleteDifusion(){
    window.location = "comunicaciones/difusiones/delete/" + $("#difusionIdHiddenInput").val() + "/" + $("#cursoIdHiddenInput").val();
}