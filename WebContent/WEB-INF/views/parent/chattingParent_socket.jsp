<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="resources.Assets"%>
<%@page pageEncoding="UTF-8"%>

<script>
$(document).ready(function(){
	getMessage("${school_admin.user_id}");
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
.active a {
	color: #fff;
}

.active {
	background: rgb(243, 190, 78) none repeat scroll 0 0;
	color: #fff !important;
}
body {
    background: #fff;
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
    height: 65vh;
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

			<div class="conversation-wrap col-sm-3" id="user-list">

				
					<div class="media conversation active" id="left_user_${school_admin.user_id}">
						<div class="media-body">
							<h5 class="media-heading clearfix">
								<a class="pull-left scrDown" href="javascript:void(0);"
									onclick="return getMessage(this.id);"
									id="${school_admin.user_id}">
									${fn:toUpperCase(school_admin.first_name)}
									${fn:toUpperCase(school_admin.last_name)}
								<c:if	test="${school_admin.p_status > 0}">
								<small class="notify" id="notify-${school_admin.user_id}">${school_admin.p_status}</small>
								</c:if>
								</a>
							</h5>
						</div>
					</div>
			
			</div>



			<div class="message-wrap col-sm-9">
				<div class="msg-wrap" id="msg-wrap">

<!-- <img src="resources/dashboard/Images/chat.jpg"> -->



				</div>

				<div class="send-wrap b-hide">

					<textarea onkeypress="isTyping('true'); timer=5;" onkeyup="isTyping('false')"  id="send-msg-details" class="form-control send-message" rows="3"
						placeholder="Write a reply..."></textarea>
					<input type="hidden" id="user_id">
					<input type="hidden" id="total_count">

				</div>
				<div class="btn-panel btn_enter_send b-hide">
				<div class="fix-height"><div id="typing_on" style="display:none;"><img src="resources/dashboard/Images/typing.gif"></div></div>
					<div class="checkbox">
			          <label>
			            <input type="checkbox" value="" id="send_check">
			            <span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
			            Press Enter to send
			          </label>
			        </div>
					<a href="javascript:void(0);" onclick="send();" id="send-msg"
						class=" col-sm-3 text-right btn   send-message-btn pull-right"
						role="button">Send</a>
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
        		  newHtml+='<a href="javascript:void(0);" onclick="return getMessage(this.id);" id="'+json.school_admin[i].user_id+'" class="pull-left">'+json.school_admin[i].first_name+' '+json.school_admin[i].last_name+'</a></h5></div></div>';
        	 }
        	 $("#user-list").html(newHtml);
        	 $("#user-list").css('text-transform','uppercase');
    		}else
    			{
    		  newHtml+='<div class="media conversation">';
      		  newHtml+='<div class="media-body"><h5 class="media-heading">';
      		  newHtml+='</h5></div></div>';
      		  $("#user-list").html(newHtml);
              $("#user-list").css('text-transform','uppercase');
    	     }
        }
    });
	
}

/*Function for get message by id*/
function getMessage(id)
{
	
	//$(".conversation").removeClass("active");
	//$("#left_user_"+id).addClass("active");
	$(".b-hide").show();
	$.ajax({
		url:'getMessageByParentId.html',
		type:'post',
		//Content-type: 'application/json; charset=utf-8',
		//contentType: "application/json; charset=utf-8",
		//contentType: "application/x-www-form-urlencoded;charset=utf-8",
		data:{'user_id':id},
		success:function(data){
			//var json=$.parseJSON(data);
			var json=data;
			var updated_count=parseInt(json.update_count);
	    	var total_count_badge=parseInt($("#total_count_badge").val());
	    	if(total_count_badge>0)
	    		{
	    			$("#message-badge").html(total_count_badge-updated_count);
	    			$("#total_count_badge").val(total_count_badge-updated_count);
	    		}
			var oldscrollHeight = ($("#msg-wrap").scrollTop() + 470);
			$("#user_id").val(id);
			
			
			$("#total_count").val(json.count);
			var newHtml='';
			if(json.message.length>0)
        	{
        	 for (i = 0; i < json.message.length; i++)
        	 { 
        		 if(json.message[i].sender!=0)
					{
        			 newHtml+='<div class="msg odd_1">';
            		 newHtml+='	<a class="pull-left" href="javascript:void(0);">';
            		 newHtml+='</a>';
            		 var t = json.message[i].time.split(/[- :]/);
            		 var d = new Date(Date.UTC(t[0], t[1]-1, t[2], t[3], t[4], t[5]));
            			var options = {
            		    weekday: "long", year: "numeric", month: "short",
            		    day: "numeric", hour: "2-digit", minute: "2-digit"
            		};
            			
            		 
            		 newHtml+='<div class="media-body"><small class="pull-left time" style="direction:ltr;"><i class="fa fa-clock-o"></i>	'+json.message[i].time+'</small>';
            		 if(json.message[i].sender==0)
             		{	
            		 newHtml+='<h5 class="media-heading">'+json.first_name+' '+json.last_name+'</h5>';
            		}
            	   else
            	   {  
            		   
            		 newHtml+='<h5 class="media-heading">'+json.logged_f_name+' '+json.logged_l_name+'</h5>';
                   }
            		 if(json.message[i].status==0)
            			 {
            			 newHtml+='<small class="chat_text">'+json.message[i].msg+' <img src="resources/dashboard/Images/check_sent.png" width="16"/></small>';
     					
            			 }else
            			{
            			newHtml+='<small class="chat_text">'+json.message[i].msg+' <img src="resources/dashboard/Images/check_read.png" width="16"/></small>';
         						 
            			}
            		 newHtml+='</div></div>';
        			 
					
        			 
					}else
					{

						 newHtml+='<div class="msg even_1 ">';
	            		 newHtml+='	<a class="pull-left" href="javascript:void(0);">';
	            		 newHtml+='</a>';
	            		 var t = json.message[i].time.split(/[- :]/);
	            		 var d = new Date(Date.UTC(t[0], t[1]-1, t[2], t[3], t[4], t[5]));
	            			var options = {
	            		    weekday: "long", year: "numeric", month: "short",
	            		    day: "numeric", hour: "2-digit", minute: "2-digit"
	            		};
	            			
	            		 
	            		 newHtml+='<div class="media-body"><small class="pull-right time"><i class="fa fa-clock-o"></i>	'+json.message[i].time+'</small>';
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
					}
        		    $("#msg-wrap").html(newHtml);
				
			 }
        	 
        	}else
    			{
    		    newHtml+='<div class="media msg ">';
				newHtml+='	<a class="pull-left" href="javascript:void(0);">';
				/* newHtml+='<img class="media-object" style="width: 32px; height: 32px;" src="resources/dashboard/uploads/school/'+json.logo+'">';
				newHtml+='<img class="media-object" style="width: 32px; height: 32px;" src="resources/dashboard/uploads/school/'+json.logo+'">';	 
				 */newHtml+='</a>';
				newHtml+='<div class="media-body"><small class="pull-right time">';
				newHtml+='<h5 class="media-heading"></h5>';
				newHtml+='<small class="col-sm-12"></small>';
				newHtml+='</div></div>';
			    $("#msg-wrap").html(newHtml);
        	 }
		
			var d = $('#msg-wrap');
			
			var newscrollHeight = $("#msg-wrap").prop("scrollHeight");
	
			$("#msg-wrap").animate({ scrollTop: $('#msg-wrap')[0].scrollHeight }, 0);
			
			 if(newscrollHeight < (oldscrollHeight +150)) {
				    var height = $('#msg-wrap')[0].scrollHeight;
		            $('#msg-wrap').scrollTop(height);
		        }
			 var user_id=$("#user_id").val();
			 is_read('message',"type",user_id);
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
			url:'sendMessageToSchoolAdminByParent.html',
			type:'post',
			data:{'reciever_id':user_id,'msg':message,'sender':"1",'status':"0",'sender_id':<%= session.getAttribute("user_id")%>},
			success:function(data){
				/* $("#send-msg-details").val('');
				getMessage(user_id);
				var d = $('#msg-wrap');
				d.scrollTop(d.prop("scrollHeight")); */
			}
		});
		
	}
	
	
}
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
        			send();
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
    	var user_id=$("#user_id").val();
    	is_typing_user('message',"type",user_id);
    	
    }
</script>
<script>
//to keep the session id
var sessionId = '';

// name of the client
var name = '';

// socket connection url and port
var socket_url = 'm3aak.net';
var port = '80'; 

/* var socket_url = '127.0.0.1';
var port = '8080'; */

$(document).ready(function() {
 
	join();
});

var webSocket;
function join() {
			name="${User_name}";
			openSocket();
			return false;
}

/**
 * Will open the socket connection
 */
function openSocket() {
	// Ensures only one connection is open at a time
	if (webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED) {
		return;
	}

	// Create a new instance of the websocket
	webSocket = new WebSocket("ws://" + socket_url + ":" + port
			+ "/chat?name=${User_id}");

	/**
	 * Binds functions to the listeners for the websocket.
	 */
	webSocket.onopen = function(event) {
		$('#message_container').fadeIn();

		if (event.data === undefined)
			return;

	};

	webSocket.onmessage = function(event) {

		// parsing the json data
		parseMessage(event.data);
	};

	webSocket.onclose = function(event) {
		
	};
}

/**
 * Sending the chat message to server
 */
function send() {
	var message = $('#send-msg-details').val();
	sendMessage();
	$('#send-msg-details').val('');
	var reciever_id=$('#user_id').val();
	var chat_type="1";
	var sender_name="<%=session.getAttribute("f_name")%>";
	if (message.trim().length > 0) {
		sendMessageToServer('message', message,reciever_id,sender_name,chat_type);
	} else {
		alert('Please enter message to send!');
	}
	

}

/**
 * Closing the socket connection
 */
function closeSocket() {
	webSocket.close();

	$('#message_container').fadeOut(600, function() {
		$('#prompt_name_container').fadeIn();
		// clearing the name and session id
		sessionId = '';
		name = '';
	});
}

/**
 * Parsing the json message. The type of message is identified by 'flag' node
 * value flag can be self, new, message, exit
 */
function parseMessage(message) {
	
	var jObj = $.parseJSON(message);

	// if the flag is 'self' message contains the session id
	 
	if (jObj.message == '1') {
		if (jObj.sessionId != sessionId && jObj.name==$("#user_id").val() ) {
			$("#typing_on").show();
		}
	}else if (jObj.message == 'read_unread_check') {
		$(".read-unread").attr('src','resources/dashboard/Images/check_read.png');
	}else if (jObj.flag == 'self') {

		sessionId = jObj.sessionId;

	} else if (jObj.flag == 'new') {
		

	} else if (jObj.flag == 'message') {
		var options = {
		  		    weekday: "long", year: "numeric", month: "short",
		  		    day: "numeric", hour: "2-digit", minute: "2-digit"
		  		};
		var date = Date();
		var myDate = new Date(date);
		var from_name = 'You';

		if (jObj.sessionId != sessionId) {
			from_name = jObj.sender_name;
		}

		//var li  ='<div class="media msg "><div class="media-body"><small class="pull-right time"><i class="fa fa-clock-o"></i>	Wednesday, Nov 30, 2016, 8:39 AM</small><h5 class="media-heading">' + from_name + '</h5><small class="col-sm-12">'+jObj.message+'</small></div></div>';
		
		if (jObj.sessionId != sessionId) {
			from_name = jObj.sender_name;
			
			
			var li  ='<div class="msg even_1"><a class="pull-left" href="javascript:void(0);"></a><div class="media-body"><small class="pull-right time"><i class="fa fa-clock-o"></i>'+myDate.toLocaleDateString("en-us", options)+'</small><h5 class="media-heading">'+from_name+'</h5><small class="chat_text1">'+jObj.message+'</small></div></div>';
		}else{
			var li  ='<div class="msg odd_1"><a class="pull-left" href="javascript:void(0);"></a><div class="media-body"><small class="pull-left time"><i class="fa fa-clock-o"></i>'+myDate.toLocaleDateString("en-us", options)+'</small><h5 class="media-heading">'+from_name+'</h5><small class="chat_text">'+jObj.message+' <img class="read-unread"  src="resources/dashboard/Images/check_sent.png" width="16"></small></div></div>';
		}
		
		if(jObj.name==$("#user_id").val() || jObj.name=="<%=session.getAttribute("user_id")%>")
		{
			if(jObj.chat_type=="1")
				{
				appendChatMessage(li);
				if (jObj.sessionId != sessionId && jObj.name==$("#user_id").val() ) {
					var user_id=$("#user_id").val();
			    	is_read('message',"type",user_id);
				}
			
			}
		}else
		{
			
		}
	//	appendChatMessage(li);

		$('#input_message').val('');

	} else if (jObj.flag == 'exit') {
		
	}
	setTimeout(function(){$("#typing_on").hide(); }, 1000);
}

/**
 * Appending the chat message to list
 */
function appendChatMessage(li) {
	$('#msg-wrap').append(li);
	//$('#msg-wrap').scrollTop($('#msg-wrap').height()+870);
	var d = $('#msg-wrap');
	d.scrollTop(d.prop("scrollHeight"));
	/* $('#messages').append(li);

	// scrolling the list to bottom so that new message will be visible
	$('#messages').scrollTop($('#messages').height()); */
}

/**
 * Sending message to socket server message will be in json format
 */
function sendMessageToServer(flag, message,reciever_id,sender_name,chat_type) {
	var json = '{""}';

	// preparing json object
	var myObject = new Object();
	myObject.sessionId = sessionId;
	myObject.message = message;
	myObject.flag = flag;
	myObject.user_id=reciever_id;
	myObject.sender_name=sender_name;
	myObject.chat_type=chat_type;

	// converting json object to json string
	json = JSON.stringify(myObject);

	// sending message to server
	webSocket.send(json);
	sendMessage();
}

function is_typing_user(flag, message,reciever_id) {
	var json = '{""}';

	// preparing json object
	var myObject = new Object();
	myObject.sessionId = sessionId;
	myObject.message = "type";
	myObject.flag = flag;
	myObject.user_id=reciever_id;

	// converting json object to json string
	json = JSON.stringify(myObject);

	// sending message to server
	webSocket.send(json);
}
/*Javascript function for call when message is read or unread*/
 function is_read(flag, message,reciever_id) {
	
	var json = '{""}';

	// preparing json object
	var myObject = new Object();
	myObject.sessionId = sessionId;
	myObject.message = "read";
	myObject.flag = flag;
	myObject.user_id=reciever_id;

	// converting json object to json string
	json = JSON.stringify(myObject);

	// sending message to server
	webSocket.send(json);
}


 
 </script>
<jsp:include page="footer.jsp" />
