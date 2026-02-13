<h1>But</h1>
La configuration présente permet de lancer un service mariaDB XXX-bdd et un service adminer (XXX-adminer).



<h1>Utiliser le service</h1>
Pour lancer les services il faut executer la commande docker-compose up

<h1>
En l'état le service maria tourne sur le port 3306, le service adminer sur le port 86. La base de donnée par défaut XXX contient une table avec 4 users. Pour y acceder il faut aller sur le host mariadb avec l'utilisateur jackSQL  et le mot de passe jack

<h1>Paramètrer les services</h1>
Le service mariaDB permet d'heberger une base de donnée SQL de type mariaDB. 
- les différents parametres du serveurs sont accessible dans le fichier .env
- le dossier BDD/init.d contient des fichiers SQL qui sont executés dans l'ordre alphabétique au PREMIER lancement du service
- l'ensemble des données de la BDD est stocké dans le dossier BDD/data qui est généré au premier lancement du service
- pour ré-initialiser la base de donnée avec les données des fichiers init.d il faut suprimer le dossier BDD/data et lancer le docker avec l'option --build

Le service adminer permet d'accéder à une interface graphique web pour visualiser les informations de la base de donnée. 
- Le service tourne sur le port PORT_ADMINER present dans le fichier .env
