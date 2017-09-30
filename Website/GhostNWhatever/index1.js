

// Initialize Firebase
var config = {
    apiKey: "AIzaSyB0apCFC3WRnF75Azql9EEvT4qEHBC6gVM",
    authDomain: "ghostsandwhatever.firebaseapp.com",
    databaseURL: "https://ghostsandwhatever.firebaseio.com",
    projectId: "ghostsandwhatever",
    storageBucket: "ghostsandwhatever.appspot.com",
    messagingSenderId: "914678984501"
};
firebase.initializeApp(config);

var database = firebase.database();

var gamesRef = database.ref('Games');

gamesRef.on('child_added', function(data){
    var gamesList = document.getElementById('games');
    var gameName = data.key;

    var listItem = document.createElement('li');

    listItem.innerHTML = gameName;
    listItem.addEventListener('click', function(data){
        window.location.href = './game.html?gamename=' + gameName;
    });

    gamesList.appendChild(listItem);



});