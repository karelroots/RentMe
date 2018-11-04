function submitRefresh(time) { // lehekülje refresh pärast mingit tegevust

        //location.reload(true);
        setTimeout(function(){window.location.reload();},time);
}


function fallback() { // kasutame kohalikke resursse kui CDN failid pole kättesaadavad
        if (! $.fn.modal) {
                document.write('<script src="/resources/bootstrap.min.js"></script>');
                document.write('<link rel="stylesheet" href="/resources/bootstrap.min.css">');
        }
}

function formReset() { // tühjendame kasutaja täidetud väljad
    document.getElementById("frmProfiil").reset();
}

function loadTab() { // open #tab when loading page
    var url = document.location.toString();
    if (url.match('#')) {
        $('.nav-tabs a[href="#' + url.split('#')[1] + '"]').tab('show');
    }

    $('.nav-tabs a').on('shown.bs.tab', function (e) {
        window.location.hash = e.target.hash;
    })
    window.scrollTo(0, 0)
}
