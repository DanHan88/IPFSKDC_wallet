window.initMap = function () {  
  const mapOptions = {
	 zoom: 7,
	 center: { lat: 36.3398175, lng: 127.3940486 },
	 mapTypeControl: false,
	 streetViewControl:false,
  }
  const map = new google.maps.Map(document.getElementById("map"), mapOptions);
  


const nanoDCs = [
    { label: "A", name: "Seoul", lat: 37.4758418, lng: 127.0460608 },
    { label: "B", name: "Gwangju", lat: 35.1249807, lng: 126.7559051 },
    //{ label: "C", name: "Bupyung", lat: 37.5188278, lng: 126.7122507 }
  ];
  
  //const bounds = new google.maps.LatLngBounds();
  const infowindow = new google.maps.InfoWindow();
  
  nanoDCs.forEach(({ label, name, lat, lng }) => {
    const marker = new google.maps.Marker({
      position: { lat, lng },
      label,
      map,
    });
  //bounds.extend(marker.position);
  marker.addListener("click", () => {
	  map.panTo(marker.position);
      infowindow.setContent(name);
      infowindow.open({
        anchor: marker,
        map,
      });
    });
  });
  //map.fitBounds(bounds);
};
