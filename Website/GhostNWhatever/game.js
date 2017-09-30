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

// Get gameName from Params
var urlString = window.location.href;

var url = new URL(urlString);

var gameName = url.searchParams.get('gamename');

// UPDATE THE GAME PREVIEW

// Set lobby name

document.getElementById('lobby').innerHTML = gameName;

// Make Initial List of players and score
var playersRef = database.ref('Games').child(gameName).child('Players');

playersRef.on('child_added', function(data){
    var playerList = document.getElementById('players');
    var playerName = data.val().name;
    var playerScore = data.val().score;

    var listItem = document.createElement('li');

    playerList.appendChild(listItem);

    listItem.innerHTML = playerName + " - " + playerScore + " points.";
});