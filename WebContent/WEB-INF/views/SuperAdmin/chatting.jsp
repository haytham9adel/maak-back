<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="resources.Assets"%>
<script>
$(document).ready(function(){
	getMessage("${school_admins[0].user_id}");
	$('.media-heading a').on('click', function(){
		$(this).parent().parent().parent().siblings().removeClass('active');
	    $(this).parent().parent().parent().addClass('active');
	});
	var abc=getJsonFromUrl();
	 
	getMessage(abc.u_id);
});
function getJsonFromUrl() {
	  var query = location.search.substr(1);
	  var result = {};
	  query.split("&").forEach(function(part) {
	    var item = part.split("=");
	    result[item[0]] = decodeURIComponent(item[1]);
	  });
	  return result;
	}
</script>
<style>
body{background:#fff;}
.active a {
	color: #fff;
}

.active {
	background: rgb(243, 190, 78) none repeat scroll 0 0;
	color: #fff !important;
}

header {
    display: none;
}

footer {
    display: none;
}

div#small_map_div,.side {
    display: none;
}
.chat_tb {
    width: 100%;
    height: 90vh;
    background: #fff;
    box-shadow: 0 0 6px rgba(0,0,0,0.4);
    overflow: hidden;
    top: 5vh;
}
.msg-wrap {
    height: 60vh;
    background-image: url(resources/dashboard/Images/msg_bg.png);
    background-repeat: repeat;
}
.ct_t {
    display: none;
}
section.desh-content {
    padding: 0;
}
.checkbox:after,.checkbox:before{display:none;}
/****************************/
.active a {
	color: #fff;
}

.active {
	background: rgb(243, 190, 78) none repeat scroll 0 0;
	color: #fff !important;
}

.conversation-wrap {
	padding: 0px 0;
	max-height: 85vh !important;
	overflow: auto;
	border-right: 1px solid rgb(241, 241, 241);
}

.conversation {
	border-bottom: 1px solid #f1f1f1;
	margin: 0;
}

.media.conversation:hover {
	background: rgb(243, 190, 78) none repeat scroll 0 0;
	color: #fff !important;
}

.media-body small.col-sm-12 {
	font-size: 14px;
}

.media.conversation:hover .media-heading a {
	color: #fff;
}

.media-body h5.media-heading {
	margin: 0;
}

.btn-panel.btn_enter_send {
	padding: 0 9px;
	background: transparent;
	border: 0;
	box-shadow: none;
}

.checkbox, .radio {
	display: inline-block;
}

.checkbox label, .radio label {
	padding-left: 0 !important;
}

.checkbox label:after, .radio label:after {
	content: '';
	display: table;
	clear: both;
}

.checkbox .cr, .radio .cr {
	position: relative;
	display: inline-block;
	border: 1px solid #a9a9a9;
	border-radius: .25em;
	width: 1.3em;
	height: 1.3em;
	float: left;
	margin-right: .5em;
}

.radio .cr {
	border-radius: 50%;
}

.checkbox .cr .cr-icon, .radio .cr .cr-icon {
	position: absolute;
	font-size: .8em;
	line-height: 0;
	top: 50%;
	left: 20%;
}

.radio .cr .cr-icon {
	margin-left: 0.04em;
}

.checkbox label input[type="checkbox"], .radio label input[type="radio"]
	{
	display: none;
}

.checkbox label input[type="checkbox"]+.cr>.cr-icon, .radio label input[type="radio"]+.cr>.cr-icon
	{
	transform: scale(3) rotateZ(-20deg);
	opacity: 0;
	transition: all .3s ease-in;
}

.checkbox label input[type="checkbox"]:checked+.cr>.cr-icon, .radio label input[type="radio"]:checked+.cr>.cr-icon
	{
	transform: scale(1) rotateZ(0deg);
	opacity: 1;
}

.checkbox label input[type="checkbox"]:disabled+.cr, .radio label input[type="radio"]:disabled+.cr
	{
	opacity: .5;
}

.chat_tb {
	box-shadow: 0 0 1px 0 rgba(0, 0, 0, 0.2);
}

.ct_t {
	font-size: 20px;
	padding: 0 0 10px;
}

.message-wrap.col-sm-9 {
	background: rgba(0, 0, 0, 0.01) none repeat scroll 0 0;
}

.media.conversation.active a small.notify {
	background: rgba(0, 0, 0, 0.4) none repeat scroll 0 0;
	border-radius: 50%;
	color: rgb(255, 255, 255);
	float: right;
	font-size: 13px;
	font-weight: bold;
	height: 15px;
	line-height: 15px;
	text-align: center;
	width: 15px;
}

.media.conversation a small.notify {
	background: rgba(0, 0, 0, 0.4) none repeat scroll 0 0;
	border-radius: 50%;
	color: rgb(255, 255, 255);
	float: right;
	font-size: 13px;
	font-weight: bold;
	height: 15px;
	line-height: 15px;
	text-align: center;
	width: 15px;
}

#msg-wrap>img {
	width: 100%;
}

.b-hide {
	display: none;
}
</style>
<div class="col-sm-9 ct_t">Chat</div>
<div class="col-sm-9 col-xs-12 chat_tb" id="chat_school">

	<div class="">
		<div class="row">
			<div class="col-sm-3" style="padding: 0;">
				<div class="btn-panel btn-panel-conversation ">
					<select class="form-control" id="s_id" name="s_id"
						onchange=getSchoolAdminBySchoolId(this.value);>
						<option value="0">All</option>
						<c:if test="${!empty schools}">
							<c:forEach items="${schools}" var="school">
								<option value=<c:out value="${school.s_id}"/>><c:out
										value="${school.school_name}" /></option>
							</c:forEach>
						</c:if>
					</select>

				</div>
			</div>
		</div>
		<div class="row">

			<div class="conversation-wrap col-sm-3" id="user-list">

				<c:forEach items="${school_admins}" var="school_admin">
					<div class="media conversation">
						<div class="media-body">
							<h5 class="media-heading clearfix">

								<a class="pull-left scrDown" href="javascript:void(0);"
									onclick="return getMessage(this.id);"
									id="${school_admin.user_id}">
									${fn:toUpperCase(school_admin.first_name)}
									${fn:toUpperCase(school_admin.last_name)} <c:if
										test="${school_admin.p_status > 0}">
										<small class="notify" id="notify-${school_admin.user_id}">${school_admin.p_status}</small>
									</c:if>
								</a>
							</h5>
						</div>
					</div>
				</c:forEach>
			</div>



			<div class="message-wrap col-sm-9">
				<div class="msg-wrap" id="msg-wrap">
				
				<img src="resources/dashboard/Images/chat.jpg">
				
				
				</div>

				<div class="send-wrap b-hide">

					<textarea onkeypress="isTyping('true'); timer=5;" onkeyup="isTyping('false')" id="send-msg-details" class="form-control send-message"
						rows="3" placeholder="Write a reply..."></textarea>
					<input type="hidden" id="user_id"> <input type="hidden"
						id="total_count">

				</div>
				<div class="btn-panel btn_enter_send b-hide">
				<div id="typing_on" style="display:none;"><img src="resources/dashboard/Images/typing.gif"></div>
					<div class="checkbox">
						<label> <input type="checkbox" value="" id="send_check">
							<span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
							Press Enter to send
						</label>
					</div>
					<a href="javascript:void(0);" onclick="sendMessage();"
						id="send-msg"
						class=" col-sm-4 text-right btn   send-message-btn pull-right"
						role="button">Send Message</a>
				</div>
			</div>
		</div>
	</div>

</div>
<!-- chat school  -->
<script>
function getSchoolAdminBySchoolId(school_id)
{
	$.ajax({
        url : 'getSchoolAdminBySchoolId.html',
        type:'post',
        data:{"school_id":school_id},
        success : function(data) {
        	$("#user_id").val('');
        	var json = $.parseJSON(data);
        	var newHtml='';
        	if(json.school_admin.length>0)
        	{
        	 for (i = 0; i < json.school_admin.length; i++)
        	 { 
        		  newHtml+='<div class="media conversation">';
        		  newHtml+='<div class="media-body"><h5 class="media-heading">';
        		  newHtml+='<a href="javascript:void(0);" onclick="return getMessage(this.id);" id="'+json.school_admin[i].user_id+'" class="pull-left scrDown">'+json.school_admin[i].first_name+' '+json.school_admin[i].last_name;
        		  if(json.school_admin[i].p_status>0){
        		  newHtml+='<small class="notify" id="notify-'+json.school_admin[i].user_id+'">'+json.school_admin[i].p_status+'</small>';
        		  }
        		  newHtml+='</a></h5></div></div>';
        	 }
        	 $("#user-list").html(newHtml);
        	 $("#user-list").css('text-transform','uppercase');
    		}else
    			{
    		  newHtml+='<div class="media conversation">';
      		  newHtml+='<div class="media-body"><h5 class="media-heading">';
      		  newHtml+='No Data Available</h5></div></div>';
      		  $("#user-list").html(newHtml);
              $("#user-list").css('text-transform','uppercase');
    	     }
        }
    });
	
}

/*Function for get message by id*/
function getMessage(id)
{
	$(".b-hide").show();
	$.ajax({
		url:'getMessageByUserId.html',
		type:'post',
		data:{'user_id':id},
		success:function(data){
			
			var oldscrollHeight = ($("#msg-wrap").scrollTop() + 470);
			$("#user_id").val(id);
			
			var json=$.parseJSON(data);
			$("#total_count").val(json.count);
			var newHtml='';
			if(json.message.length>0)
        	{
        	 for (i = 0; i < json.message.length; i++)
        	 { 
        		 newHtml+='<div class="media msg ">';
        		 newHtml+='	<a class="pull-left" href="javascript:void(0);">';
        		 if(json.message[i].sender==0)
        		{
        			 newHtml+='<img class="media-object" style="width: 32px; height: 32px;" src="resources/dashboard/uploads/school/'+json.logo+'">';
            	}
        	   else
        	   {
        			 newHtml+='<img class="media-object" style="width: 32px; height: 32px;" src="resources/dashboard/Images/desh-logo.jpg">';	 
               }
        		 newHtml+='</a>';
        		

        		 var t = json.message[i].time.split(/[- :]/);
        		 var d = new Date(Date.UTC(t[0], t[1]-1, t[2], t[3], t[4], t[5]));
        			var options = {
        		    weekday: "long", year: "numeric", month: "short",
        		    day: "numeric", hour: "2-digit", minute: "2-digit"
        		};
        			
        		 
        		 newHtml+='<div class="media-body"><small class="pull-right time"><i class="fa fa-clock-o"></i>	'+d.toLocaleDateString("en-us", options)+'</small>';
        		 if(json.message[i].sender==0)
         		{	
        		 newHtml+='<h5 class="media-heading">'+json.first_name+' '+json.last_name+'</h5>';
        		}
        	   else
        	   {  
        		   
        		 newHtml+='<h5 class="media-heading">'+json.logged_f_name+' '+json.logged_l_name+'</h5>';
               }
        		 newHtml+='<small class="col-sm-12">'+json.message[i].msg+'</small>';
        		 newHtml+='</div></div>';
				$("#msg-wrap").html(newHtml);
				
			 }
        	 
        	}else
    			{
    		    newHtml+='<div class="media msg ">';
				newHtml+='	<a class="pull-left" href="javascript:void(0);">';
				newHtml+='</a>';
				newHtml+='<div class="media-body"><small class="pull-right time">';
				newHtml+='<h5 class="media-heading">No Data Available</h5>';
				newHtml+='<small class="col-sm-12"></small>';
				newHtml+='</div></div>';
			    $("#msg-wrap").html(newHtml);
        	 }
		
			var d = $('#msg-wrap');
			
			var newscrollHeight = $("#msg-wrap").prop("scrollHeight");
			
			$("#msg-wrap").animate({ scrollTop: $('#msg-wrap')[0].scrollHeight }, 0);
			 if(newscrollHeight < (oldscrollHeight + 150)) {
		            var height = $('#msg-wrap')[0].scrollHeight;
		            $('#msg-wrap').scrollTop(height);
		        } 
			$("#notify-"+id).hide();
		}
	});
}

/*Function for send message */
function sendMessage()
{
	var user_id=$("#user_id").val();
	var message=$("#send-msg-details").val();
	if(message!='')
		{
	$.ajax({
		url:'sendMessageByUserId.html',
		type:'post',
		data:{'reciever_id':user_id,'msg':message,'sender':"1",'status':"0",'sender_id':<%=session.getAttribute("user_id")%>},
		success:function(data){
			$("#send-msg-details").val('');
			getMessage(user_id);
			var d = $('#msg-wrap');
			d.scrollTop(d.prop("scrollHeight"));
		}
	});
	
		}
	
	
	
}

$(document).ready(function(){
	
	setInterval(function(){ 
		
		var user_id=$("#user_id").val();
		if(user_id!='')
		{
			
			$.ajax({
				url:'getMessageByUserId.html',
				type:'post',
				data:{'user_id':user_id},
				success:function(data){
					
					var json=$.parseJSON(data);
					var total_count=$("#total_count").val();
					var total_new_count=json.count;
					console.log(total_new_count);
					if(parseInt(total_new_count)>parseInt(total_count))
					{
						getMessage(user_id);
						$("#total_count").val(total_new_count);
					}
					
				}	
					
			});
				
		
			
			
		}
		
		
		
	}, 3000);
	
	 $(".scrDown").click(function(){
		setTimeout(function(){ 
		var d = $('#msg-wrap');
		d.scrollTop(d.prop("scrollHeight"));
		}, 100);
	});
	 setInterval(function(){ 
			
			var user_id=$("#user_id").val();
			if(user_id!='')
			{
		$.ajax({
	   		 type:"post",
	   		 url:"checkUserTypingStatus.html",
	   		 data:{typing_user:user_id},
	   		 success:function(response)
	   		 {
	   			 
	   			var json=$.parseJSON(response);
				var status=json.status;
				if(status==0)
					{
						$("#typing_on").hide();
					}else
						{
						$("#typing_on").show();
						}
				
	   		 }
	   		});
			}
		}, 200);
	
});
</script>
<script type="text/javascript">
var wage = document.getElementById("send-msg-details");
wage.addEventListener("keydown", function (e) {
    if (e.keyCode === 13) {
    	
    	
    	 var chkPassport = document.getElementById("send_check");
         if (chkPassport.checked) {
        	 var message=$("#send-msg-details").val();
     		if(message!='')
     		{
     	 	sendMessage();
     		}
         } 
    	
     }
});



</script>
<script type="text/javascript" language="javascript">
    var timer = 0;

    function reduceTimer() {
        timer = timer - 1;
        isTyping(true);
    }

    function isTyping(val) {
        if (val == 'true') {
        	
        	var user_id=$("#user_id").val();
        	$.ajax({
        		url:"parentToSchoolAdminTyping.html",
        		type:'post',
				data:{'typing_reciever':user_id},
				success:function(data){
					
					
				}	
        	});
         
        } else {

            if (timer <= 0) {
            	var user_id=$("#user_id").val();
            	$.ajax({
            		url:"parentToSchoolAdminTypingUpdate.html",
            		type:'post',
    				data:{'typing_reciever':user_id},
    				success:function(data){
    					
    					
    				}	
            	});
            	
            	
               // document.getElementById('typing_on').innerHTML = "No one is typing -blank space.";
            } else {
                setTimeout("reduceTimer();", 500);
            }
        }
    }
   
  
    
    setTimeout(function()
    		   { 
    		   	
    		   }, 3000);
    
</script>
<jsp:include page="footer.jsp" />
