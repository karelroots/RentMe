function submitRefresh() { // lehekülje refresh pärast mingit tegevust

        //location.reload(true);
        setTimeout(function(){window.location.reload();},10);
}

function fallback() { // kasutame kohalikke resursse kui CDN failid pole kättesaadavad
        if (! $.fn.modal) {
                document.write('<script src="/resources/bootstrap.min.js"></script>');
                document.write('<link rel="stylesheet" href="/resources/bootstrap.min.css">');
        }
}