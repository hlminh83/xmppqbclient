var stompClient = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	} else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}

function connect() {
	var socket = new SockJS('/pa-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/publicaccount/chat', function(greeting) {
			//        	alert("hahah");
			showGreeting(greeting.body);
		});
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendToken() {
	//	var url = "";
	//	var posting = $.post(url, {
	//		s : term
	//	});
	//	// Put the results in a div
	$.ajax({
		url : "/publicaccount/login",
		contentType : "application/json",
		type : "POST",
		data :  JSON.stringify({
			userId : $("#userId").val(),
			sessionToken : $("#sessionToken").val()
		}),
		dataType : "json",
		success : function() {
		}
	}); 

	//	$.post("/publicaccount/login",
	//			  {
	//			    userId: $("#userId").val(),
	//			    sessionToken: $("#userId").val()
	//			  },
	//			  function(data, status){
	////			    alert("Data: " + data + "\nStatus: " + status);
	//			  });

	// posting.done(function(data) {
	//		var content = $(data).find("#content");
	//		$("#result").empty().append(content);
	//	});
}

function showGreeting(message) {
	$("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
		connect();
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#login").click(function() {
		sendToken();
	});
});
