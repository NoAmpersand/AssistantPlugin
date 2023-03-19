# AssistantPlugin

## Utilisation :
L'assitant va vous demander d'écrire une requête et va y répondre si possible.  
Une fois la classe Main lancée, l'applications peut prendre quelques secondes pour démarrer la conversation.
écrivez "stop" pour arreter la conversation.
Attention : Pensez à mettre des majuscules sur les noms propres (ex: Jean, Paris, Michel...) et il est parfois nécessaire de mettre les accents !

## Fonctionnalités et API :
L'application permet grâce aux APIs de Google de récupérer des informations sur l'application Google Contact et Google Calendar (Contact seulement pour la v1).

## NLP et traitement de la requête :
L'application utilise des regex pour comprendre les requêtes. Pour optimiser le traitement nous avons utiliser la bibliothèque CoreNLP-french (détecter le type et le sens des mots de la requête).
