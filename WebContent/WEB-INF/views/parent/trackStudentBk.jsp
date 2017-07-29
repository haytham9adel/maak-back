<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<!-- <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script> -->
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&libraries=places&v=3.14"></script>
<script>
    var removeMarker=[];
    var point;   
    var flightPlanCoordinates=new Array();
    var map;
    var bounds = new google.maps.LatLngBounds();

    function initialize()
    {
        
        var point;
        var flightPlanCoordinates=new Array();
        map = new google.maps.Map(document.getElementById("map-canvas"), {
        center: new google.maps.LatLng(18.9750, 72.8258),
        zoom: 8,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        mapTypeControl: false,
        mapTypeControlOptions: {
        style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR
        },
        navigationControl: true,
        navigationControlOptions: {
        style: google.maps.NavigationControlStyle.SMALL
        }
        });
        var latlongposA= new google.maps.LatLng(${students[0].lat},${students[0].lng});
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(${students[0].lat},${students[0].lng}),
                map: map,
                //animation: google.maps.Animation.DROP
            });
            marker.info = new google.maps.InfoWindow({
           // content: 'Mumbai, Maharashtra, India'
            });
          
            google.maps.event.addListener(marker, 'click', function() {
           // marker.info.open(map, marker);
            });
         /*   removeMarker.push(marker);*/
           bounds.extend(latlongposA);
           map.fitBounds(bounds); 

           var positionB= new google.maps.LatLng(${students[size_l].lat},${students[size_l].lng});
             marker1 = new google.maps.Marker({
            position: new google.maps.LatLng(${students[size_l].lat},${students[size_l].lng}),
            map: map,
            icon:"resources/dashboard/Images/bus.png"
            //animation: google.maps.Animation.DROP
        });
             marker1.info = new google.maps.InfoWindow({
         //   content: '${students[0].lat}'
            });

            google.maps.event.addListener(marker1, 'click', function() {
            //marker1.info.open(map, marker1);
            }); 
           removeMarker.push(marker1);
           bounds.extend(positionB);
           map.fitBounds(bounds); 
           
         <c:set var="count" value="0" scope="page" />
        	<c:forEach items="${students}" var="student">
            
                       point = new google.maps.LatLng(
                     ${student.lat},
                      ${student.lng});
                       flightPlanCoordinates[${count}]=point;
            <c:set var="count" value="${count + 1}" scope="page"/>
       	 </c:forEach>
       	
        var flightPath = new google.maps.Polyline({
        path: flightPlanCoordinates,
        strokeColor: "green",
        strokeOpacity: 3.0,
        strokeWeight: 6
        });

        flightPath.setMap(map);

       
    }
    google.maps.event.addDomListener(window, 'load', initialize);

function addRoute(data)
{
    var count=parseInt(data['size_l']);
    var bounds = new google.maps.LatLngBounds();
    var position= new google.maps.LatLng(data['details'][0].lat,data['details'][0].lng);
    var positionB= new google.maps.LatLng(data['details'][count].lat,data['details'][count].lng);
   /* var map = new google.maps.Map(document.getElementById("map-canvas"), {
    center: new google.maps.LatLng(data['details'][0].trip_lat,data['details'][0].trip_long),
    zoom: 7,
    mapTypeId: google.maps.MapTypeId.ROADMAP,
    mapTypeControl: false,
    mapTypeControlOptions: {
    style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR
    },
    });*/

      
    
        marker = new google.maps.Marker({
        position: new google.maps.LatLng(data['details'][0].lat,data['details'][0].lng),
        map: map,
        //animation: google.maps.Animation.DROP
    });
        marker.info = new google.maps.InfoWindow({
        // content: data['add'].trip_to
        });
        google.maps.event.addListener(marker, 'click', function() {
        marker.info.open(map, marker);
        });

        google.maps.event.addListener(marker, 'click', function() {
        marker.info.open(map, marker);
        });
     
        bounds.extend(position);
        map.fitBounds(bounds);   

        marker1 = new google.maps.Marker({
        position: new google.maps.LatLng(data['details'][count].lat,data['details'][count].lng),
        map: map,
        icon:"resources/dashboard/Images/bus.png"
        //animation: google.maps.Animation.DROP
    });
         marker1.info = new google.maps.InfoWindow({
         //content: data['add'].trip_from
        });
        google.maps.event.addListener(marker1, 'click', function() {
      //  marker1.info.open(map, marker1);
        
        });
        removeMarker.push(marker1);
        for(var i=0;i<parseInt(data['details'].length);i++)
        {
            point = new google.maps.LatLng(
            data['details'][i].lat,
            data['details'][i].lng);
            flightPlanCoordinates[i]=point;
        }

        bounds.extend(positionB);
        map.fitBounds(bounds);   


    var flightPath = new google.maps.Polyline({
    path: flightPlanCoordinates,
    strokeColor: "green",
    strokeOpacity: 3.0,
    strokeWeight: 6
    });

    flightPath.setMap(map);

   
}


</script>
<style>
    html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
    }
    .map_div {
        border: 1px solid #CCCCCC;
        border-radius: 4px;
        float: left;
        height: 500px;
        margin-left: 4px;
        margin-top: 10px;
        padding: 10px;
        width: 700px;
    }
</style>
<body onload="initialize();">
<div class = 'map_div'>    
<c:choose> 
  <c:when test="${!empty students}">
<!-- <button onclick="getNewLatLng();" type="button">Click Me</button> -->
    <div id="map-canvas"></div>
   </c:when>
    <c:otherwise>
        No Data Available
    </c:otherwise>
</c:choose>
</div>
</body>
<script>
function getNewLatLng()
{
	$.ajax({
	    url : 'getStudentTrackLatLng.html',
	    type:'post',
	    data:{"student_id":"${students[0].student_id}"},
	    success : function(data) {
		  	var latiLongi = $.parseJSON(data);
		  	 clearMarkers();
			addRoute(latiLongi);
	    }
	});
}
//Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
  setAllMap(null);
 
}
function removeOverlay(locations) {
    
            clearMarkers();

} 
function setAllMap(map) {
  for (var i = 0; i < removeMarker.length; i++) {
    removeMarker[i].setMap(map);
  }
}
</script>
<input type="hidden" value="${size_l+1}" id="oTCount"> 
<input type="hidden" value="" id="tTCount"> 
<script type="text/javascript">
$(document).ready(function(){
   
setInterval(
        function ()
        {
            $.post('trackSingleTripCount.html',{'student_id':"${students[0].student_id}"},function(data) {
            	var data = $.parseJSON(data);
            	
               $("#tTCount").val(data.size_l);
            });
            var old_count=$("#oTCount").val();
            var new_count=$("#tTCount").val();
            if(parseInt(new_count) > parseInt(old_count))
            {
            	getNewLatLng();
               $("#oTCount").val(new_count);

            }
           
        }, 6000); 
/*
    window.setInterval(function(){
    getRoute();
}, 5000);*/


});
</script>
</div>
<br>
<jsp:include page="footer.jsp" />