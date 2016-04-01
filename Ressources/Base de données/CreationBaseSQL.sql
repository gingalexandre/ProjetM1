CREATE TABLE Joueur(
	idJoueur            INTEGER PRIMARY KEY ,
	pseudo              TEXT NOT NULL ,
	mdp                 TEXT NOT NULL ,
	nombrePartieGagnee  INTEGER NOT NULL ,
	nombrePartieJouee   INTEGER NOT NULL ,
	dateNaissance      Datetime NOT NULL  
);


CREATE TABLE Partie(
	idPartie  INTEGER PRIMARY KEY NOT NULL ,
	path      TEXT NOT NULL ,
	checksum  TEXT
);


CREATE TABLE Jouer(
	idJoueur  INTEGER NOT NULL ,
	idPartie  INTEGER NOT NULL ,
	PRIMARY KEY (idJoueur,idPartie) ,
	
	FOREIGN KEY (idJoueur) REFERENCES Joueur(idJoueur),
	FOREIGN KEY (idPartie) REFERENCES Partie(idPartie)
);


