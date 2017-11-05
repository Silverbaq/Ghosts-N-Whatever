

// Initialize Firebase
var config = {
    // *************************************
    // Insert Firebase config here
    // *************************************
};
firebase.initializeApp(config);

var database = firebase.database();

var gamesRef = database.ref('Games');

gamesRef.on('child_added', function(data){
    var gamesList = document.getElementById('games');
    var gameName = data.val().GameCode;
    var gameKey = data.key;

    var listItem = document.createElement('li');

    listItem.innerHTML = gameName;
    listItem.addEventListener('click', function(data){
        window.location.href = './game.html?gamename=' + gameKey;
    });

    gamesList.appendChild(listItem);



});

// Create a new game and push it to Firebase

function createGame(){
    //Generate random ID

    var gameCode = makeiID();

    // Create Game Object

    var newGame = {
        Players: [],
        Location: [],
        GameState: "Creating",
        GameCode: gameCode,
        GameTime: 0
    };

    var gameID = gamesRef.push(newGame).key;

    window.location.href = './game.html?gamename=' + gameID;

};

function makeiID() {
    var text = "";
    var possible = "abcdefghijklmnopqrstuvwxyz0123456789";

    for (var i = 0; i < 5; i++)
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}
