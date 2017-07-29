<jsp:include page="header.jsp" />
    <style>
       
    </style>
    
    <div class="section top_bread clearfix" >
    	<div class="container">
			<div class="row">
				<div class="col-md-12">
                	<p class="align-right"><span class="">Home</span>/<span  class="yellow">${heading}</span></p>
                </div>
    		</div>
        </div>
    </div>
	<div class="section clearfix">
    	<div class="container bg-body" >
			<div class="row">
				<div class="col-md-12">
                	<h2>${content.page_name}</h2>
                </div>
    		</div>
        </div>
    </div>
    <div class="container  bg-body   bottom-margin ">
			<div class="row">
				<div class="col-md-12">
                	<div class="col-md-12 pad0">
                     
                    <p>${content.page_desc}</p>
				    </div>
                    
                </div>
    		</div>
        </div>
 <jsp:include page="footer.jsp" />