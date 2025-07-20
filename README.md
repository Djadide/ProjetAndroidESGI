# ProjetAndroidESGI
Une application Android native de quiz, développée en Java avec IntelliJ.

## Fonctionnalités principales

- **Page d’accueil** : saisie du prénom, bouton pour démarrer un quiz ou accéder au profil personnalisé.
- **Profil personnalisé** : message d’accueil (français/anglais automatique), image de profil, couleur de fond définie.
- **Quiz** : questions à choix multiples, score automatique, navigation fluide entre questions.
- **Résultat** : affichage du score, félicitations, option de retour à l’accueil ou de recommencer.
- **Ressources externalisées** : toutes les chaînes/valeurs/couleurs sont placées dans les fichiers adéquats.
- **Accessibilité & multilingue** : gestion du français/anglais avec strings.xml et values-en/strings.xml.
- **Bonne pratique** : architecture MVC simple, ViewBinding activé, layouts XML clairs et adaptés aux écrans mobiles.

## Fonctionnalités bonus

- **Chronomètre** : mesure et affiche le temps total pour chaque partie.
- **Sauvegarde des scores** : historique local avec SharedPreferences.
- **Affichage du nom du joueur** sur tous les écrans.
- **Bouton “Rejouer”** sur l’écran de résultat.
- **Système de questions à choix multiples de java ** : possibilité de sélectionner plusieurs réponses correctes pour certaines questions, score attribué uniquement si toutes les bonnes réponses (et seulement elles) sont cochées.
- **Bouton “Retour à l’accueil”** pour réinitialiser la partie.
- **Réinitialisation automatique du quiz** lors du retour à l’accueil.
- **Gestion multilingue automatique** (français/anglais).

## Structure du projet

app/
├── src/
│ ├── main/
│ │ ├── java/com/example/projetquizesgi/
│ │ │ ├── MainActivity.java
│ │ │ ├── ProfilActivity.java
│ │ │ ├── QuizActivity.java
│ │ │ ├── ResultatActivity.java
│ │ │ ├── Question.java
│ │ ├── res/
│ │ │ ├── layout/
│ │ │ │ ├── activity_main.xml
│ │ │ │ ├── activity_profil.xml
│ │ │ │ ├── activity_quiz.xml
│ │ │ │ ├── activity_resultat.xml
│ │ │ ├── values/
│ │ │ │ ├── strings.xml
│ │ │ │ ├── colors.xml
│ │ │ │ ├── values-en/
│ │ │ │ │ └── strings.xml
│ │ │ ├── drawable/
│ │ │ │ ├── baseline_account_circle_24.xml
│ │ │ │ ├── ic_launcher_background.xml
│ │ │ │ ├── ic_launcher_foreground.xml
      AndroidManifest.xml
...


## Lancer et tester

1. **Cloner le projet** :  
   `git clone https://github.com/Djadide/ProjetAndroidESGI.git`

2. **Ouvrir dans IntelliJ**

3. **Synchroniser Gradle** (`File > Sync Project with Gradle Files`)

4. **Exécuter sur un émulateur ou un vrai appareil** (API 21+)

5. **Changer la langue du téléphone** pour tester le multilingue

## Auteurs

- [Djadide](https://github.com/Djadide) - Développeur principal
- Projet encadré dans le cadre de la formation Android ESGI

## Licence

Ce projet est sous licence MIT.

---

**Contact et questions** : ouvre une issue ou contacte-moi sur GitHub !
