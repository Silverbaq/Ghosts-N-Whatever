# Ghosts-N-Whatever
Ghosts-N-Whatever is a multiplayer mobile game, that was developed over 48 hours durring a GameJam at EAL in 2017. 

## The game idea

There is three types of characters in this game.

* A Ghost
* A Ghosthunter
* A Graverobber

There is also two different types of locations for the game.

* A Town
* Crypts

The locations for the game is QR codes, that should be printed out on paper, and put at different location in a building. (One town, several crypts).
Players now have to move between the different locations and scan the QR codes with their devices. 

### How to play
Print out as many QR codes as you like. Place them on different locations that you think will make the game fun.

Start the Web application, and Create a new game. Select the running time for the game (you can set the time limit a lot higher if you wish).
Every player who wishes to play should start the Android application on their own device. Click join game and insert the code that is provided by the website.
Before starting the game, the players need to setup the gameboard. To do this, they need to scan a QR code and select if its a Town or a Crypt.
When everyone is ready, click the Start game button on the website and the game will begin.

### Gameplay
When the game starts, every player will get one of the three charater class by random.

Ghost: Your purpose is to go to the town to pickup souls. You get provided points if you bring a soul back to a crypt. In case a crypt has a trap, you will lose the soul and not get any points.

Ghosthunter: You need to go trap some ghosts. To do this, you will need to go to town and buy a trap (this will cost you some points). Then you need to go to a crypt to set the trap. If a ghost accesses a trapped crypt (with a trap that you have set) you will get points.

Graverobber: Your purpose is to go get some loot. You should visit crypts where you can steal the trap (if there is any). Then you can go to the town and sell it. In case you visit a crypt that do not have a trap, you will lose points.

On the website, you can always follow the highscore the an active game. When the time is up, the player with the most points will win the game.


## Configure
Ghosts-N-Whatever is using Firebase as the backend. Therefor you will need to create a firebase project (https://console.firebase.google.com) and add your own firebase project config to the project.

### Website
To configure the website, insert the configuration details from your firebase project into the 'index1.js' file.

### Android 
Inside the android project, replace the app/google-services.json file with the config file from your firebase project.
