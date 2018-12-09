nrPlayers = 446;
lastMarble = 71522 * 100;

playerValue = [0] * nrPlayers;

marbles = [];
marbles.append(0);
marbles.append(1);

marbleId = 2;

currentMarble = 1;
currentPlayer = 1;
while (marbleId < lastMarble):
    if (marbleId % 23 != 0):
        currentMarble = (currentMarble + 2) % len(marbles);
        if (currentMarble == 0):
            currentMarble = len(marbles);
        tmp = marbles[:currentMarble];
        tmp.append(marbleId);
        tmp.extend(marbles[currentMarble:]);
        marbles = tmp;
    
    else:
        playerValue[currentPlayer] = playerValue[currentPlayer] + marbleId;

        currentMarble -= 7;
        currentMarble = currentMarble % len(marbles);
        if(currentMarble < 0):
            currentMarble = currentMarble + len(marbles);

        playerValue[currentPlayer] = playerValue[currentPlayer] + marbles[currentMarble];

        del marbles[currentMarble];

        currentMarble = currentMarble % len(marbles);
        if(currentMarble < 0):
            currentMarble = currentMarble + len(marbles);
    
    currentPlayer = (currentPlayer + 1) % nrPlayers;
    marbleId = marbleId + 1;
    
myMax = -1
for x in playerValue:
    if (myMax < x):
        myMax = x;
print(myMax)