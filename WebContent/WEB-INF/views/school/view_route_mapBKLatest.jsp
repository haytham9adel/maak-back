<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3&sensor=true&libraries=drawing"></script>
 <style type="text/css">
      #map, html, body {
        padding: 0;
        margin: 0;
        height: 100%;
      }

      #panel {
        width: 200px;
        font-family: Arial, sans-serif;
        font-size: 13px;
        float: right;
        margin: 10px;
      }

      #color-palette {
        clear: both;
      }

      .color-button {
        width: 14px;
        height: 14px;
        font-size: 0;
        margin: 2px;
        float: left;
        cursor: pointer;
      }

      #delete-button {
        margin-top: 5px;
      }
    </style>
    <script type="text/javascript">
      var drawingManager;
      var selectedShape;
      var colors = ['#1E90FF', '#FF1493', '#32CD32', '#FF8C00', '#4B0082'];
      var selectedColor;
      var colorButtons = {};

      function clearSelection() {
        if (selectedShape) {
          selectedShape.setEditable(false);
          selectedShape = null;
        }
      }

      function setSelection(shape) {
        clearSelection();
        selectedShape = shape;
        shape.setEditable(true);
        selectColor(shape.get('fillColor') || shape.get('strokeColor'));
      }

      function deleteSelectedShape() {
        if (selectedShape) {
          selectedShape.setMap(null);
        }
        // To show:
         drawingManager.setOptions({
           drawingControl: true
         });
      }

      function selectColor(color) {
        selectedColor = color;
        for (var i = 0; i < colors.length; ++i) {
          var currColor = colors[i];
          colorButtons[currColor].style.border = currColor == color ? '2px solid #789' : '2px solid #fff';
        }

        // Retrieves the current options from the drawing manager and replaces the
        // stroke or fill color as appropriate.
        var polylineOptions = drawingManager.get('polylineOptions');
        polylineOptions.strokeColor = color;
        drawingManager.set('polylineOptions', polylineOptions);

        var rectangleOptions = drawingManager.get('rectangleOptions');
        rectangleOptions.fillColor = color;
        drawingManager.set('rectangleOptions', rectangleOptions);

        var circleOptions = drawingManager.get('circleOptions');
        circleOptions.fillColor = color;
        drawingManager.set('circleOptions', circleOptions);

        var polygonOptions = drawingManager.get('polygonOptions');
        polygonOptions.fillColor = color;
        drawingManager.set('polygonOptions', polygonOptions);
      }

      function setSelectedShapeColor(color) {
        if (selectedShape) {
          if (selectedShape.type == google.maps.drawing.OverlayType.POLYLINE) {
            selectedShape.set('strokeColor', color);
          } else {
            selectedShape.set('fillColor', color);
          }
        }
      }

      function makeColorButton(color) {
        var button = document.createElement('span');
        button.className = 'color-button';
        button.style.backgroundColor = color;
        google.maps.event.addDomListener(button, 'click', function() {
          selectColor(color);
          setSelectedShapeColor(color);
        });

        return button;
      }

       function buildColorPalette() {
         var colorPalette = document.getElementById('color-palette');
         for (var i = 0; i < colors.length; ++i) {
           var currColor = colors[i];
           var colorButton = makeColorButton(currColor);
           colorPalette.appendChild(colorButton);
           colorButtons[currColor] = colorButton;
         }
         selectColor(colors[0]);
       }

      function initialize() {
        var directionsService = new google.maps.DirectionsService;
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var map = new google.maps.Map(document.getElementById('map_canvas'), {
          zoom: 10,
          center: new google.maps.LatLng(22.344, 75.525),
          mapTypeId: google.maps.MapTypeId.ROADMAP,
          disableDefaultUI: false,
          zoomControl: true
        });

        var polyOptions = {
          strokeWeight: 0,
          fillOpacity: 0.45,
          editable: true
        };
        // Creates a drawing manager attached to the map that allows the user to draw
        // markers, lines, and shapes.
        drawingManager = new google.maps.drawing.DrawingManager({
          drawingMode: google.maps.drawing.OverlayType.POLYGON,
          drawingControlOptions: {
            drawingModes: [
              google.maps.drawing.OverlayType.POLYGON
            ]
          },
          markerOptions: {
            draggable: true
          },
          polylineOptions: {
            editable: true
          },
          rectangleOptions: polyOptions,
          circleOptions: polyOptions,
          polygonOptions: polyOptions,
          map: map
        });

        google.maps.event.addListener(drawingManager, 'overlaycomplete', function(event) {
          if (event.type == google.maps.drawing.OverlayType.CIRCLE) {
            var radius = event.overlay.getRadius();
          }
        });
        google.maps.event.addListener(drawingManager, 'overlaycomplete', function(e) {
            if (e.type != google.maps.drawing.OverlayType.MARKER) {
            // Switch back to non-drawing mode after drawing a shape.
            drawingManager.setDrawingMode(null);
            // To hide:
            drawingManager.setOptions({
              drawingControl: false
            });

            // Add an event listener that selects the newly-drawn shape when the user
            // mouses down on it.
            var newShape = e.overlay;
            newShape.type = e.type;
            google.maps.event.addListener(newShape, 'click', function() {
              setSelection(newShape);
            });
            setSelection(newShape);
          }
        });

        // Clear the current selection when the drawing mode is changed, or when the
        // map is clicked.
        google.maps.event.addListener(drawingManager, 'drawingmode_changed', clearSelection);
        google.maps.event.addListener(map, 'click', clearSelection);
        google.maps.event.addDomListener(document.getElementById('delete-button'), 'click', deleteSelectedShape);

       	 buildColorPalette();

          directionsDisplay.setMap(map);


    directionsDisplay.suppressMarkers = true;
  // document.getElementById('submit').addEventListener('click', function() {
      calculateAndDisplayRoute(directionsService, directionsDisplay);
      }
      google.maps.event.addDomListener(window, 'load', initialize);


      function calculateAndDisplayRoute(directionsService, directionsDisplay) {
    	  var waypts = [];
    	  //var checkboxArray = document.getElementById('waypoints');
    	   var latiArr=[<c:forEach items="${latlong}" var="latlng">${latlng.lat},</c:forEach>];
    	   var longiArr=[<c:forEach items="${latlong}" var="latlng">${latlng.lng},</c:forEach>]; 
    	   
    	  
    	  for (var i = 0; i < latiArr.length; i++) {
    	   
    	      waypts.push({
    	        location: new google.maps.LatLng(latiArr[i],longiArr[i]),
    	        stopover: true
    	      });
    	   
    	  }
    		
    	  var start = "${route.source_lat}, ${route.source_lng}";
    	    var end = "${route.destination_lat},${route.destination_lng}";
    	  directionsService.route({
    		  
    		 
    	    origin: start,
    	    destination: end,
    	    waypoints: waypts,
    	    optimizeWaypoints: true,
    	    travelMode: google.maps.TravelMode.DRIVING
    	  }, function(response, status) {
    	    if (status === google.maps.DirectionsStatus.OK) {
    	      directionsDisplay.setDirections(response);
    	     
    	   
    	      var infowindow = new google.maps.InfoWindow();

    	        var marker, i;
    	        
    	    
        	    
    	        
    	      // For each route, display summary information.
    	      for (var i = 0; i < latiArr.length; i++) {
    	       
    	    	    marker = new google.maps.Marker({
    	                position: new google.maps.LatLng(latiArr[i], longiArr[i]),
    	                map: map
    	              });
    	    	  	
    	    	
    	    	    
    	          google.maps.event.addListener(marker, 'click', (function(marker, i) {
    	              return function() {
    	                infowindow.setContent(longiArr[i]);
    	                infowindow.open(map, marker);
    	              }
    	            })(marker, i));
    	    	  
    	      }
                 
    	    
    	      
    	    } else {
    	      window.alert('Directions request failed due to ' + status);
    	    }
    	  });
    	}
    </script>
<body >
<div class="col-md-6 m-side" >
				

             <div class="map">
             
            
              <div id="map_canvas" style="width:100%; height:600px;">
</div>

 </div>
   </div>
<div class="col-md-3" >
	<div id="panel" >
      <div id="color-palette"></div>
      <div>
        <button id="delete-button">Delete Selected Shape</button>
      </div>
    </div>
</div> 
   
  </body>       
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
	
 
          
    <jsp:include page="footer.jsp" />      