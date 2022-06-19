$(document).ready(function(){
    $("#label-loading").prop("hidden",true);
})

function activateLoadingButton(button,disabled){
    button.html("<i class='fa fa-spinner fa-spin'></i>");
    button.prop("disabled",disabled);
}

function desactivateLoadingButton(button,text,disabled){
    button.html(text);
    button.prop("disabled",disabled);
}

function defaultErrorNotify(){
    toastr.options.positionClass = 'toast-top-right';
    toastr.error('Inténtalo nuevamente', 'Ocurrió un error');
}

function closeModal(modalElement){
    modalElement.modal('hide');
}

function activateLoadingTable(table){
    table.addClass("loading");
}

function desactivateLoadingTable(table){
    table.prop("hidden",false);
    table.removeClass("loading");
}

function resetSelectPicker(select){
    select.prop("selectedIndex", 0);
    select.selectpicker("refresh");
}