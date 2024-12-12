
# FinalExamApp

## Description

FinalExamApp is an Android application designed to demonstrate the integration of various Android features including Room Database, RecyclerView, LiveData, WorkManager, SharedPreferences, and notifications. The app showcases how to handle data storage, retrieval, and display efficiently using modern Android development practices.

---

## Démonstration
https://github.com/user-attachments/assets/e746a8ae-c3eb-403e-8e7a-5fd834b1bb1f

## Features

1. **Room Database**

    - Stores user information persistently.
    - Flushes the database at each app launch for a clean slate.
    - Uses LiveData to observe changes and automatically update the UI.

2. **RecyclerView**

    - Displays two lists of items in a scrollable format.
    - Dynamically updates with data changes.

3. **SharedPreferences**

    - Stores and retrieves lightweight user preferences.

4. **WorkManager**

    - Schedules background tasks such as notifications.





5. **CustomView**

    - Demonstrates creating and rendering custom views in Android.

6. **Notifications**

    - Sends notifications to engage the user with important messages.

7. **Localization**

    - Supports localized strings for a better user experience in multiple languages.

---

## Project Structure

### **Packages**

- `com.example.final_exam_app`
    - **Activities**: Contains `MainActivity` for the app's primary functionality.
    - **Database**:
        - `User`: Entity representing the user table.
        - `UserDao`: Data Access Object for interacting with the database.
        - `AppDatabase`: Room database implementation.
    - **Adapters**:
        - `MyAdapter`: Adapter for the first RecyclerView.
        - `UserAdapter`: Adapter for the second RecyclerView displaying user data.
    - **Workers**:
        - `MyWorker`: Background task managed by WorkManager.
    - **Views**:
        - `CustomView`: Custom view for UI enhancement.

---

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-repository/final-exam-app.git
   ```

2. Open the project in Android Studio.

3. Sync the Gradle files to ensure all dependencies are resolved.

4. Build and run the project on an emulator or a physical device.

---

## Usage

1. **Welcome Message**:

    - Displays a localized welcome message on app launch.

2. **Database Operations**:

    - Flushes the database at the start.
    - Automatically adds two users (John Doe and Jane Smith) to the database.

3. **RecyclerView**:

    - Displays a list of static items in the first RecyclerView.
    - Dynamically updates user data in the second RecyclerView.

4. **SharedPreferences**:

    - Displays user preferences stored in SharedPreferences.

5. **CustomView**:

    - Renders a cyan background with a custom message.

6. **WorkManager**:

    - Schedules background tasks for notifications.

7. **Notifications**:

    - Sends a notification with predefined content upon app launch.

---

## Code Highlights

### **Room Database Initialization**

```kotlin
val db = Room.databaseBuilder(
    applicationContext,
    AppDatabase::class.java, "exam_database"
).build()
val userDao = db.userDao()
```

### **Flush Database**

```kotlin
lifecycleScope.launch(Dispatchers.IO) {
    userDao.deleteAll()
}
```

### **LiveData Observation**

```kotlin
userDao.getAll().observe(this) { users ->
    userRecyclerView.adapter = UserAdapter(users)
}
```

### **RecyclerView Setup**

```kotlin
val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
recyclerView.layoutManager = LinearLayoutManager(this)
recyclerView.adapter = MyAdapter()
```

### **SharedPreferences Example**

```kotlin
val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
val editor = sharedPreferences.edit()
editor.putString("username", "John Doe")
editor.apply()
```

---

## Dependencies

- **Room Database**: `androidx.room:room-runtime:2.x.x`
- **RecyclerView**: `androidx.recyclerview:recyclerview:1.x.x`
- **LiveData**: `androidx.lifecycle:lifecycle-livedata-ktx:2.x.x`
- **WorkManager**: `androidx.work:work-runtime-ktx:2.x.x`

---

## Future Enhancements

- Add more user management features (e.g., edit and delete users).
- Enhance the UI with animations and material design principles.
- Implement network operations to fetch user data from a remote API.

---

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## Acknowledgments

- [Android Developers Documentation](https://developer.android.com/)
- Open-source libraries used in the project.
