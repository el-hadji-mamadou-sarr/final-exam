
# FinalExamApp

## Description

FinalExamApp est une application Android conçue pour démontrer l'intégration de plusieurs fonctionnalités Android modernes, notamment Room Database, RecyclerView, LiveData, WorkManager, SharedPreferences, et les notifications. L'application met en avant la gestion efficace du stockage, de la récupération et de l'affichage des données en utilisant les meilleures pratiques de développement Android.

---

## Démonstration

[Vidéo de démonstration](https://github.com/user-attachments/assets/e746a8ae-c3eb-403e-8e7a-5fd834b1bb1f)

---

## Fonctionnalités

1. **Base de données Room**

    - Stocke les informations utilisateur de manière persistante.
    - Réinitialise la base de données à chaque lancement de l'application pour repartir à zéro.
    - Utilise LiveData pour observer les changements et mettre à jour l'interface utilisateur automatiquement.

2. **RecyclerView**

    - Affiche deux listes d'éléments sous forme défilante.
    - Met à jour dynamiquement les données.

3. **SharedPreferences**

    - Stocke et récupère les préférences utilisateur légères.

4. **WorkManager**

    - Planifie des tâches en arrière-plan comme l'envoi de notifications.

5. **CustomView**

    - Illustre la création et le rendu de vues personnalisées dans Android.

6. **Notifications**

    - Envoie des notifications pour engager l'utilisateur avec des messages importants.

7. **Localisation**

    - Supporte les chaînes localisées pour offrir une meilleure expérience utilisateur dans plusieurs langues.

---

## Structure du projet

### **Packages**

- `com.example.final_exam_app`
    - **Activities** : Contient `MainActivity`, la principale activité de l'application.
    - **Database** :
        - `User` : Entité représentant la table utilisateur.
        - `UserDao` : Objet d'accès aux données pour interagir avec la base.
        - `AppDatabase` : Implémentation de la base de données Room.
    - **Adapters** :
        - `MyAdapter` : Adaptateur pour le premier RecyclerView.
        - `UserAdapter` : Adaptateur pour le deuxième RecyclerView affichant les données utilisateur.
    - **Workers** :
        - `MyWorker` : Tâche en arrière-plan gérée par WorkManager.
    - **Views** :
        - `CustomView` : Vue personnalisée pour améliorer l'interface utilisateur.

---

## Installation

1. Clonez le dépôt :

   ```bash
   git clone https://github.com/el-hadji-mamadou-sarr/final-exam.git
   ```

2. Ouvrez le projet dans Android Studio.

3. Synchronisez les fichiers Gradle pour résoudre toutes les dépendances.

4. Construisez et exécutez le projet sur un émulateur ou un appareil physique.

---

## Utilisation

1. **Message de bienvenue** :

    - Affiche un message de bienvenue localisé au lancement de l'application.

2. **Opérations de base de données** :

    - Réinitialise la base de données au démarrage.
    - Ajoute automatiquement deux utilisateurs (John Doe et Jane Smith) dans la base.

3. **RecyclerView** :

    - Affiche une liste statique dans le premier RecyclerView.
    - Met à jour dynamiquement les données utilisateur dans le deuxième RecyclerView.

4. **SharedPreferences** :

    - Affiche les préférences utilisateur stockées dans SharedPreferences.

5. **CustomView** :

    - Rend un fond cyan avec un message personnalisé.

6. **WorkManager** :

    - Planifie des tâches en arrière-plan pour des notifications.

7. **Notifications** :

    - Envoie une notification avec un contenu prédéfini au lancement de l'application.

---

## Points forts du code

### **Initialisation de la base de données Room**

```kotlin
val db = Room.databaseBuilder(
    applicationContext,
    AppDatabase::class.java, "exam_database"
).build()
val userDao = db.userDao()
```

### **Réinitialisation de la base de données**

```kotlin
lifecycleScope.launch(Dispatchers.IO) {
    userDao.deleteAll()
}
```

### **Observation avec LiveData**

```kotlin
userDao.getAll().observe(this) { users ->
    userRecyclerView.adapter = UserAdapter(users)
}
```

### **Configuration d'un RecyclerView**

```kotlin
val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
recyclerView.layoutManager = LinearLayoutManager(this)
recyclerView.adapter = MyAdapter()
```

### **Exemple de SharedPreferences**

```kotlin
val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
val editor = sharedPreferences.edit()
editor.putString("username", "John Doe")
editor.apply()
```

---

## Dépendances

- **Room Database** : `androidx.room:room-runtime:2.x.x`
- **RecyclerView** : `androidx.recyclerview:recyclerview:1.x.x`
- **LiveData** : `androidx.lifecycle:lifecycle-livedata-ktx:2.x.x`
- **WorkManager** : `androidx.work:work-runtime-ktx:2.x.x`

---

## Améliorations futures

- Ajouter des fonctionnalités de gestion utilisateur (par exemple, modification et suppression).
- Améliorer l'interface utilisateur avec des animations et les principes du design Material.
- Implémenter des opérations réseau pour récupérer les données utilisateur depuis une API distante.

---

## Licence

Ce projet est sous licence MIT. Consultez le fichier LICENSE pour plus de détails.

---

## Contributeur

- [El Hadji Mamadou Sarr](https://github.com/el-hadji-mamadou-sarr)
