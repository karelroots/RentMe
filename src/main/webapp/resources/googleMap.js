function loadMap() {
    var latlng = new google.maps.LatLng(58.37822455, 26.71506136);
    var myOptions = {
        zoom: 18,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_container"),myOptions);

    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title:"Rent.me kontor"
    });

}