# Survival of the Codes

An evolution survival game

## Tutorial

After starting the game, you see your codes (akin to DNA) in the bottom-right corner.
The top-right corner shows environment stats such as the temperature and luminosity.
The bottom shows your own character and its energy.
On the top of the screen, the other creatures in the world reside.
On your turn, your character will be highlighted in green.
You can choose to eat or pair with another creature.
If you choose to pair, you will swap a code with the other creature and lose some energy in the process.
If you choose to eat, you will gain energy and the other creature will die.
Eating will only succeed if your codes are better than the other creature's codes.
After your turn, the other creatures will take their turns.
The last creature surviving will turn against you.
If you manage to kill it, you win.

## How to compile

````bash
javac -d build Main.java && cd build && jar cfe game.jar Main * && cd ..
````
