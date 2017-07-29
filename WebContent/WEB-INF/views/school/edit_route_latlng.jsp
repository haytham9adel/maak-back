<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 m-side">
				
			<form method="post" action="school/editlatlng"  id="namei" name="namei">
			<input type="hidden" name="route_id" id="route_id" value="${route_id}">
<input type="hidden" name="lat" id="lat" value="">
<input type="hidden" name="lng" id="lng" value="">
<button type="button" onclick="add();">Save Route</button>
</form>	
             <div class="new-student-form">
             
            <p><button class="btn btn-primary" type="button" onclick="resetMarkers();">Reset Markers</button></p>
              <div id="map_canvas" style="width:800px; height:600px;">
</div>
<script src="http://maps.googleapis.com/maps/api/js?sensor=false&libraries=places&v=3.14"></script>
<script type="text/javascript">
   var latiArr=[<c:forEach items="${latlong}" var="latlng">${latlng.lat},</c:forEach>];
  var longiArr=[<c:forEach items="${latlong}" var="latlng">${latlng.lng},</c:forEach>]; 
 
  var sourceLat;
  var sourceLng;
  var destLat;
  var destLng;
function init() {

    var markers = [],
        segments = [],
        myOptions = {
            zoom: 12,
            center: new google.maps.LatLng("${route.source_lat}", "${route.source_lng}"),
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            disableDoubleClickZoom: true,
            draggableCursor: "crosshair"
        },
        alphas = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split(''),
    	alphaIdx = 0,
        map = new google.maps.Map(document.getElementById("map_canvas"), myOptions),
        service = new google.maps.DirectionsService(),
        poly = new google.maps.Polyline({
            map: map,
            strokeColor: '#00FFFF',
            strokeOpacity: 0.6,
            strokeWeight: 5
    });

var markers1 = [
    ["${route.source}", "${route.source_lat}", "${route.source_lng}"],
    ["${route.destination}","${route.destination_lat}", "${route.destination_lng}"]
];

  var infowindow = new google.maps.InfoWindow(), marker, i;
    for (i = 0; i < markers1.length; i++) {  
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(markers1[i][1], markers1[i][2]),
            map: map,
            icon: 'resources/front/Images/source.png'
        });
        google.maps.event.addListener(marker, 'click', (function(marker, i) {
            return function() {
                infowindow.setContent(markers1[i][0]);
                infowindow.open(map, marker);
            }
        })(marker, i));
    }

    window.resetMarkers = function () {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        alphaIdx = 0;
        segments = [];
        markers = [];
        poly.setPath([]);
    };
    function getSegmentsPath() {
        var a, i,
            len = segments.length,
            arr = [];
        for (i = 0; i < len; i++) {
            a = segments[i];
            if (a && a.routes) {
                arr = arr.concat(a.routes[0].overview_path);
            }
        }
        return arr;
    }

    function addSegment(start, end, segIdx) {

        service.route(
            {
                origin: start,
                destination: end,
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            },
            function (result, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    //store the entire result, as we may at some time want
                    //other data from it, such as the actual directions
                    segments[segIdx] = result;

                    poly.setPath(getSegmentsPath());
                }
   
            }
        );
    }


    for(var p=0;p<latiArr.length;p++)
     { 
      if (alphaIdx > 100) {
            return;
        }
      
        var evtPos =new google.maps.LatLng(latiArr[p],longiArr[p]) ,
            c = alphas[alphaIdx++],
            marker = new google.maps.Marker({
                map: map,
                position: evtPos,
                draggable: true,
                //icon: 'http://www.google.com/mapfiles/marker'+ c +'.png'
        });
      
         marker.segmentIndex = markers.length - 1;
         marker.iconChar = c;//just storing this for good measure, may want at some time
        function updateSegments() {
            var start, end, inserts, i,
                idx = this.segmentIndex,
                segLen = segments.length, //segLen will always be 1 shorter than markers.length
                myPos = this.getPosition();
            if (segLen === 0) { //nothing to do, this is the only marker
                return;
            }
            if (idx == -1) { //this is the first marker
                start = [myPos];
                end = [markers[1].getPosition()];
                inserts = [0];
               
            } else if (idx == segLen - 1) { //this is the last marker
                start = [markers[markers.length - 2].getPosition()];
                end = [myPos];
                inserts = [idx];
            } else {
            //there are markers both behind and ahead of this one in the 'markers' array
                start = [markers[idx].getPosition(), myPos];
                end = [myPos, markers[idx + 2].getPosition()];
                inserts = [idx, idx + 1];
            }
          
          
           latiArr.splice(idx+1,1);
           latiArr.splice(idx+1, 0,markers[idx+1].getPosition().lat());
           longiArr.splice(idx+1,1);
           longiArr.splice(idx+1, 0,markers[idx+1].getPosition().lng());
           console.log(start.length);
            for (i = 0; i < start.length; i++) {
                addSegment(start[i], end[i], inserts[i]);
            }
        }

    
        //google.maps.event.addListener(marker, 'drag', updateSegments);
       

        google.maps.event.addListener(marker, 'dragend', updateSegments);
        markers.push(marker);

        if (markers.length > 1) {

             var infowindow = new google.maps.InfoWindow({
             //content: "latlong"+e.latLng
  });

            addSegment(markers[markers.length - 2].getPosition(), evtPos, marker.segmentIndex);
          //  infowindow.open(map,markers[markers.length - 2]);
        }
   }


    google.maps.event.addListener(map, "click", function (e) {
        var latlongi=e.latLng;
       
        if (alphaIdx > 100) {
            return;
        }
        var evtPos = e.latLng,
            c = alphas[alphaIdx++],
            marker = new google.maps.Marker({
                map: map,
                position: evtPos,
                draggable: true,
                //icon: 'http://www.google.com/mapfiles/marker'+ c +'.png'
        });
            console.log("evtPos="+e.latLng);
         latiArr.push(latlongi.lat());
         longiArr.push(latlongi.lng());
         // console.log(latiArr);
         marker.segmentIndex = markers.length - 1;
         marker.iconChar = c;//just storing this for good measure, may want at some time
        function updateSegments() {
            var start, end, inserts, i,
                idx = this.segmentIndex,
                segLen = segments.length, //segLen will always be 1 shorter than markers.length
                myPos = this.getPosition();
            if (segLen === 0) { //nothing to do, this is the only marker
                return;
            }
            if (idx == -1) { //this is the first marker
                start = [myPos];
                end = [markers[1].getPosition()];
                inserts = [0];
               
            } else if (idx == segLen - 1) { //this is the last marker
                start = [markers[markers.length - 2].getPosition()];
                end = [myPos];
                inserts = [idx];
            } else {
            //there are markers both behind and ahead of this one in the 'markers' array
                start = [markers[idx].getPosition(), myPos];
                end = [myPos, markers[idx + 2].getPosition()];
                inserts = [idx, idx + 1];
            }
          
          
           latiArr.splice(idx+1,1);
           latiArr.splice(idx+1, 0,markers[idx+1].getPosition().lat());
           longiArr.splice(idx+1,1);
           longiArr.splice(idx+1, 0,markers[idx+1].getPosition().lng());
            for (i = 0; i < start.length; i++) {
                addSegment(start[i], end[i], inserts[i]);
            }
        }
       
        //google.maps.event.addListener(marker, 'drag', updateSegments);
       

        google.maps.event.addListener(marker, 'dragend', updateSegments);
        markers.push(marker);

         
     

        if (markers.length > 1) {
          console.log(markers);
             var infowindow = new google.maps.InfoWindow({
           //  content: evtPos
              /*  infowindow.setContent('');
                infowindow.open(map, marker);*/

  });

            addSegment(markers[markers.length - 2].getPosition(), evtPos, marker.segmentIndex);
          infowindow.open(map,markers[markers.length - 1]);
        }
    });
}
init();
function addLatLong(start)
{
  
}
function add()
{
    $("#lat").val(latiArr);
    $("#lng").val(longiArr);
    var lat= $("#lat").val();
    var lng=  $("#lng").val();
    var route_id= $("#route_id").val();
    document.getElementById("namei").submit();

}   
</script>
  </div> 
     </div>
         
<script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#staff").validate({
          rules: {
        	  driver_fname: "required",
        	  driver_lname:"required",
        	  address: "required",
        	  dept_id: "required",
        	  d_email:{
						required: true,
						email: true
					},
					contact_number: "required",
          },
          messages: {
        	  driver_fname: "Please enter first name",
        	  driver_lname: "Please enter last name",
        	  d_email:
			   	  {
				 		required: "Please enter email address",
				 		email: "Please enter valid email address"
			          	},
		     contact_number: "Please enter contact number",
			 address:"Please enter address",
			  },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>

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
  var map = new google.maps.Map(document.getElementById('map_canvas1'),
    mapOptions);
  }
  google.maps.event.addDomListener(window, 'load', initialize);

$("#source").geocomplete({
    
      });
$("#destination").geocomplete({

});
</script>
 
          
    <jsp:include page="footer.jsp" />      