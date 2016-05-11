# ProjetM1 concernant les matières : Génie Logiciel, Réseaux et Java
# Contributeurs :
- Alexandre GINGEMBRE
-  Arthur MOUREY
- Jérôme MARINTHE
- Yohann HUGO
- Mathieu MOUROT

# Lancer le Jeu :
- Générer les JARs
- Créer un Dossier et placer les Jar dedans
- Placer le fichier ```security.policy``` dans le dossier créé

- Nous avons appelé dans l'exemple suivant Client.jar le JAR du Client et ClientServeur.jar le JAR du serveur

## Lancer le Serveur

- Trouver votre adresse IP ```ifconfig``` sur Linux ou ```ipconfig``` sur Windows)
- Lancer le serveur depuis le dossier

```
java -jar ClientServeur.jar VOTRE_ADRESSE_IP Djava.security.policy=file:./security.policy  -Djava.rmi.server.codebase=file:Client.jar/bin/ClientServeur.jar
```

## Lancer le Client

- Lancer le client dans un nouveau terminal, toujours depuis le dossier

```
java -jar Client.jar -Djava.security.policy=file:./security.policy 
```
- Entrez votre adresse IP (```ifconfig``` sur Linux ou ```ipconfig``` sur Windows)


