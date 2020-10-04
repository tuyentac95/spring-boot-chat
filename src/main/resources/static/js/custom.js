let $button;
let $textarea;
let username;
let $chatHistory;
let $chatHistoryList;

function init() {
    cacheDOM();
    bindEvents();
    registration();
}

function bindEvents() {
    $button.on('click', addMessage.bind(this));
    $textarea.on('keyup', addMessageEnter.bind(this));
}

function cacheDOM(){
    $button = $('#sendBtn');
    $textarea = $('#message-to-send');
    $chatHistory = $('.msg_card_body');
    $chatHistoryList = $chatHistory.find('ul');
}

function addMessage() {
    sendMessage($textarea.val());
}

function sendMessage(message) {
    if(message.trim() !== ''){
        sendMsg(username, message);
        let template = Handlebars.compile($("#send-message-template").html());
        let context = {
            messageOutput: message,
            time: getCurrentTime(),
            toUserName: selectedUser
        };
        $chatHistoryList.append(template(context));
        $textarea.val('');
        $chatHistory.scrollTop($chatHistory[0].scrollHeight);
    }
}

function addMessageEnter(event) {
    // enter was pressed
    if (event.keyCode === 13) {
        addMessage();
    }
}

function getCurrentTime() {
    return new Date().toLocaleTimeString().replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
}

