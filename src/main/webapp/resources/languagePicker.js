$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        var pathName = window.location.pathname;
        if (selectedOption != ''){
            window.location.replace(pathName+ '?lang=' + selectedOption);
        }
    });
});
