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

database.ref('Games/' + gameName).on('value', function(data){
    document.getElementById('lobby').innerHTML = data.val().GameCode;
});



// Make Initial List of players and score
var playersRef = database.ref('Games').child(gameName).child('Players');

playersRef.on('child_added', function(data){
    var playerList = document.getElementById('players');
    var playerName = data.val().name;
    var playerScore = data.val().score;
    var playerClass = data.val().characterClass;
    var playerItem;

    if (data.val().backpack === undefined){
        playerItem = "Empty";
    } else {
       playerItem = data.val().backpack.items[0].name;
    }

    var listItem = document.createElement('li');
    listItem.setAttribute('id', data.key);

    playerList.appendChild(listItem);

    listItem.innerHTML = playerName + " - Class: " + playerClass + " - Backpack: " + playerItem + " - " + playerScore + " points.";
});

var currentlyWinning = {
    name: "None",
    score: 0
};

// Update Scores throughout the game
playersRef.on('child_changed', function(data){
   var listItem = document.getElementById(data.key);
   var playerName = data.val().name;
   var playerScore = data.val().score;
   var playerClass = data.val().characterClass;
   var playerItem;

   if (data.val().score > currentlyWinning.score){
       currentlyWinning = data.val();
   }


   if (data.val().backpack === undefined){
       playerItem = "Empty";
   } else {
       playerItem = data.val().backpack.items[0].name;
   }

    listItem.innerHTML = playerName + " - Class: " + playerClass + " - Backpack: " + playerItem + " - " + playerScore + " points.";
});

function startGame(){
  var dropdown = document.getElementById('game-length');
  var selection = parseInt(dropdown.options[dropdown.selectedIndex].value) * 60;

  var timerDiv = document.getElementById('timer');

  generateRoles();
  setGameStateOnDB('Running');
  runTimer(selection, timerDiv);
}

function runTimer(duration, display) {
    var timer = duration, minutes, seconds;
    var intervalID = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        updateGameTimeOnDB(timer);
        display.innerHTML = "<div id='clock'> " + minutes + ":" + seconds + "</div>";

        if (--timer < 0) {
            timer = 0;
            setGameStateOnDB('Ended');
            displayWinner();
            clearInterval(intervalID);
        } else {

        }
    }, 1000);
}

function updateGameTimeOnDB(time){
    var timeRef = database.ref('Games/' + gameName).child('GameTime');
    timeRef.set(time);
}

function setGameStateOnDB(state){
    var stateRef = database.ref('Games/' + gameName).child('GameState');
    stateRef.set(state);
}

function generateRoles(){
    // get list of players from Firebase
    var playerList;
    var numPlayers;
    var mustHaveClasses = ['Ghost', 'Hunter', 'Robber'].sort(function(){
        return .5-Math.random();
    });
    playersRef.once('value', function(data){
       playerList = Object.keys(data.val());
       numPlayers = Object.keys(playerList).length;
    });

    for (var x = 0; x<numPlayers; x++){

        var ref = playersRef.child(playerList[x]);

        if(mustHaveClasses.length > 0){
            assignClass(ref, mustHaveClasses.pop());
        } else {
            assignClass(ref, pickRandomClass());
        }
    }

}

function assignClass(playerRef, role){
        playerRef.child('characterClass').set(role);
    };

function pickRandomClass(){
    var num = Math.random(0,100)*100;
    if (num <= 10){
        return 'Robber';
    } else if (num > 10 && num <= 20){
        return 'Hunter';
    } else {
        return 'Ghost';
    }
}

function displayWinner(){
    var div = document.getElementById('timer');

    div.innerHTML = "<h4 class='extra-padding'>" + currentlyWinning.name + " has won the game with " + currentlyWinning.score + " points</h4><button onclick='endGame()'>Play Again!</button>";
}

function endGame(){
    var gameRef = database.ref('Games').child(gameName).remove();
    window.location.href = "./index.html";
}