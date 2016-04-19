# Teaching-HEIGVD-RES-2016-Labo-SMTP

## Description du projet

Ce laboratoire est une introduction au protocole SMTP. Il a été réalisé dans le cadre du cours de Réseau à l'HEIG-VD (Haute école d'ingénierie et de gestion du canton de Vaud, Suisse).

Les objectifs du laboratoire sont :

* comprendre le fonctionnement du protocole SMTP entre le client et le serveur

* implémenter une version basique de ce protocole en Java, afin d'envoyer des mails directement depuis un socket

* comprendre qu'il est facile de spécifier une fausse adresse mail pour l'expéditeur

* réaliser une petite application paramétrable qui envoie des "prank mails" à un groupe d'adresses mail, en définissant un faux expéditeur



## Installation et configuration

L'installation de l'application se fait en trois étapes :

1. récupérer le contenu de ce repository

2. modifier les fichiers de configuration selon vos besoins

3. compiler et exécuter le projet Java

4. perdre vos amis


Fichiers de configuration :

* Paramètres SMTP et configuration des groupes : _config/config.properties_

  * _smtpServerAddress_ : adresse du serveur SMTP
  
  * _smtpServerPort_ : port sur le serveur SMTP
  
  * _numberOfGroups_ : nombre de groupes à créer
  
  * _minimumGroupMembers_ : nombre minimum de personnes par groupe (1 expéditeur fictif, le reste seront des destinataires)

  
* Liste des adresses mail cibles : _config/victims.utf8_

  * chaque ligne a la syntaxe suivante : _<prénom>;<nom>;<adresse_mail>
  
  * les noms et prénoms ne sont pas encore utilisés pour personnaliser les mails, mais peut-être dans une prochaine version

  
* Liste des messages à envoyer : _config/messages.utf8_

  * chaque message à la syntaxe suivante :
    1. _<sujet du mail>_
	2. _---_ (séparateur sujet/corps du message)
	3. _<corps du message>_ (une ou plusieurs lignes)
	4. _###_ (séparateur de messages)


  
## Implémentation


## Utilisation d'un serveur mock SMTP

Si vous voulez seulement tester le logiciel en local, ou éviter de vous faire bloquer par votre provider Internet, vous pouvez utiliser un serveur mock SMTP. Il s'agit d'un serveur SMTP local qui ne fait que répondre aux clients SMTP sans réellement transmettre les mails.

L'application a été testé avec [fakeSMTP](https://nilhcem.github.io/FakeSMTP/). La configuration par défaut est faite pour fonctionner avec ce serveur mock.

Ci-dessous une capture du résultat visible dans **fakeSMTP**, après lancement du programme Java avec la configuration par défaut :

![Capture fakeSMTP](https://github.com/adussier/Teaching-HEIGVD-RES-2016-Labo-SMTP/tree/master/figures/fakeSMTP.png)