'use strict';

var chatPage = document.querySelector('#chatPage');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#messageInput');
var messageArea = document.querySelector('#chatArea');

var stompClient = null;
var curSubscription = null;
var username = null;

var colors = [
    '#2196F3', '#0054ee', '#00BCD4', 'rgba(82,249,255,0.76)',
    '#070bff', 'rgba(133,200,255,0.75)', 'rgba(0,89,255,0.49)', 'rgba(57,83,187,0.65)'
];

/*
function getAvatarColor(messageSender) {
    console.log("Started getAvatar()");
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}
 */

function onConnected(event) {
    console.log("Started onConnected()");
    // subscribe to the chatroom (multiple later?)
    curSubscription = stompClient.subscribe('/topic/MainRoom', onMessageRecieved);

    // alert socket that user has connected
    stompClient.send("/app/chat.addUser", {}, JSON.stringify({sender: username, type: 'JOIN'}));
    // hide the connecting element (if needed)
    // TO-DO!!

    // IMPORTANT -- DO NOT DELETE THIS
    // it stops the form from refreshing the page
    event.preventDefault();
}


function onError() {
    console.log("Started onError()");
    // send text content to HTML element to alert a failed connection
}

function onLeave() {
    console.log("Started onLeave()")

    // unsubscribe client and alert the server
    curSubscription.unsubscribe();
    stompClient.send("/app/chat.removeUser", {}, JSON.stringify({sender: username, type: 'LEAVE'}));
}


function sendMessage(event) {
    console.log("Started sendMessage()");
    // get user's entry into text input element
    var messageContent = messageInput.value.trim();

    // if both message to send and currently connected client exist
    if (messageContent && stompClient) {
        // create new instance of chatMessage object with relevant content
        // (see see java/com.example.co2201group10/model/ChatMessage for contents of message package)
        var chatMessage = {sender: username, content: messageInput.value, type: "CHAT"};

        // stringify the chat message and send to ChatRoomController to be sent
        // (see java/com.example.co2201group10/controller/ChatRoomController)
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));

        //clear the message input field
        messageInput.value = "";
    }

    // IMPORTANT -- DO NOT DELETE THIS
    // it stops the form from refreshing the page
    event.preventDefault();
}


function onMessageRecieved(payload) {
    console.log("Started onMessageRecieved()");

    const d = new Date();
    const curHour = d.getHours();
    const curMins = ("0" + d.getMinutes()).slice(-2);


    // create variable holding message object (from received payload)
    var message = JSON.parse(payload.body);

    // new message element to write to
    var messageElement = document.createElement('li');

    //determine type of message received
    if (message.type === "JOIN") {
        // create a user joined announcement
        console.log("JOIN message received");
        messageElement.classList.add("event-message");
        message.content = message.sender + " joined the room!";

    } else if (message.type === "LEAVE") {
        // create a user left announcement
        console.log("LEAVE message received");
        messageElement.classList.add("event-message");
        message.content = message.sender + " left the room!";

    } else {
        console.log("CHAT message received");
        messageElement.classList.add('chat-message');

        /*
        // deal with avatars later
        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);
         */

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender + " at " + curHour + ":" + curMins);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement("p");
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


// call message submit function listener
messageForm.addEventListener('submit', sendMessage, true);

