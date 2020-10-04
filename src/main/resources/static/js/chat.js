const url = 'http://localhost:8080';
let stompClient;
let selectedUser;
let selectedUserImg;

// show menu of on chatting user
$(document).ready(function(){
    $('#action_menu_btn').click(function(){
        $('.action_menu').toggle();
    });
});

// select user to chat
$('.contacts li').click(function(e) {
    $('.contacts li').removeClass('active');

    var $this = $(this);
    if (!$this.hasClass('active')) {
        $this.addClass('active');

        selectedUser = $this.find('.username').text();
        $('#on-chat-username').html('Chat with ' + selectedUser);

        selectedUserImg = $this.find('.user_image').html();
        $('#on-chat-user-image').html(selectedUserImg);

        $chatHistoryList.html('');
    }

});

function connectToChat(username) {
    console.log(username + " connecting to chat...");
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({},function (frame){
       console.log("connected to: " + frame);
       stompClient.subscribe("/topic/messages/" + username, function (response){
          let data = JSON.parse(response.body);
          console.log('From ' + data.from + ': ' + data.message);
          let templateResponse = Handlebars.compile($("#receive-message-template").html());
          let contextResponse = {
              response: data.message,
              time: getCurrentTime(),
              userName: data.from
          };
          $chatHistoryList.append(templateResponse(contextResponse));
          let receive_msg_image = selectedUserImg.replace('rounded-circle user_img','rounded-circle user_img_msg');
          $('.receive_msg_img').html(receive_msg_image);
          $chatHistory.scrollTop($chatHistory[0].scrollHeight);
       });
    });
}

function sendMsg(from, text){
    stompClient.send('/app/chat/' + selectedUser, {}, JSON.stringify({
        from: from,
        message: text
    }));
}

function registration(){
    username = document.getElementById("user").value;
    console.log("username registry: " + username);
    $.get(url + "/registration/" + username, function (response){
        connectToChat(username);
    }).fail(function (error){
        if (error.status === 400) {
            alert("Login is already busy!")
        }
    });
}

init();