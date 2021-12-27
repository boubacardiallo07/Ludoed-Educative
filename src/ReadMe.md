Résumé:

Ce projet est crée à l'occasion de l'enseignement de Pdp.
Il consiste à créer une application Android adapté à des enfants avec des troubles du développement cognitif.

Build:

Le projet peut être construit depuis l'application Android Studio, en spécifiant le dossier du SDK installé dans votre machine.
Il faudra le spécifier dans le fichier ./local.properties si ce n'est pas fait automatiquement par Android Studio.

Run:

Créer une émulation depuis l'application Android Studio peut permettre de lancer directement notre application, tout comme la connexion appropriée de la machine avec un appareil Android quelconque.

Étape d'importation du projet:

Selectionnez l'onglet File -> open...

Puis selectionnez le projet dans le repertoire où vous l'avez enregistré

lorsque le projet est chargé verifier dans local.properties si le 
chemin du sdk correspond au chemin dans votre programme

Etape pour lancer le jeu :
Pour pouvoir émuler le jeu sur un simulateur 

Selectionnez Tools -> AVD Manager -> Create Virtual Device

Caractéristique du Virtual Device :
Pour l'instant on emule notre projet sur la tablette

- Category : tablette
- Name : Pixel C
- Release Name : R
- Startup orientation : Landscape

Appuyer sur Finnish pour terminer la création du simulateur

Pour simuler sur votre propre tablette veuillez-vous suivre
les explications de ce site :
https://doc.pcsoft.fr/fr-FR/?9000117