   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <label class="col-sm-3 control-label">Stop Address:</label>
			    <div class="col-sm-9">
			      <input type="text" name="address" value="${address.address}"  id="address" class="form-control">
</div>
   
   <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&libraries=places&v=3.14"></script>

<script type="text/javascript" src="resources/front/js/jquery.geocomplete.min.js"></script>


<script type='text/javascript'>
  function initialize() {
  var mapOptions = {
    center: { lat: 51.5072, lng: 0.1275},
    zoom: 12,
    mapTypeControl: false,
    streetViewControl: false,
    panControl: false,
    scrollwheel: false,
    zoomControl: true,
    zoomControlOptions: {
    style: google.maps.ZoomControlStyle.SMALL,
    position: google.maps.ControlPosition.LEFT_TOP}
  };
  var map = new google.maps.Map(document.getElementById('map_canvas'),
    mapOptions);
  }
  google.maps.event.addDomListener(window, 'load', initialize);

$("#address").geocomplete({
    
      });



</script>
  