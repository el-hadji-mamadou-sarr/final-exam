package com.example.final_exam_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.final_exam_app.ui.theme.FinalexamappTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Récupérer le message localisé
        val welcomeMessage = getString(R.string.welcome_message)

        // Afficher un Toast avec le message localisé
        Toast.makeText(this, welcomeMessage, Toast.LENGTH_LONG).show()

        // afficher la notification
        showNotification(this)

        // Créer une requête pour exécuter MyWorker une seule fois
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()

        // Enregistrer la requête avec WorkManager
        WorkManager.getInstance(this).enqueue(workRequest)

        val textView = findViewById<TextView>(R.id.textView)

        // Modify the text programmatically
        textView.text = "Bienvenue sur FinalExamApp!"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter()

        // Initialiser RecyclerView
        val userRecyclerView = findViewById<RecyclerView>(R.id.userRecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialiser la base de données Room
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "exam_database"
        ).build()
        val userDao = db.userDao()

        // Observer les données avec LiveData
        userDao.getAll().observe(this) { users ->
            userRecyclerView.adapter = UserAdapter(users)
        }

        // Insérer un utilisateur dans la base de données
        lifecycleScope.launch(Dispatchers.IO) { // Utilisation de Dispatchers.IO pour exécuter sur un thread d'E/S
            userDao.insert(User(1, "John Doe"))
            userDao.insert(User(2, "Jane Smith"))
        }

        // Mettre à jour le TextView avec les données des préférences
        val userInfoTextView = findViewById<TextView>(R.id.userInfoTextView)
        manageSharedPreferences(this, userInfoTextView)
    }
}

fun showNotification(context: Context) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = "exam_channel"

    // Créer le canal pour Android 8.0 (API 26) et plus
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelName = "Exam Notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = "Notifications for exam updates"
        }
        notificationManager.createNotificationChannel(channel)
    }

    // Construire la notification
    val notification = NotificationCompat.Builder(context, channelId)
        .setContentTitle("Notification FinalExamApp")
        .setContentText("Bonjour, voici un exemple de notification.")
        .setSmallIcon(R.drawable.ic_notification) // Assurez-vous que l'icône existe dans res/drawable
        .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Priorité pour Android 7.1 (API 25) et moins
        .build()

    // Afficher la notification
    notificationManager.notify(1, notification)
}

private fun manageSharedPreferences(context: Context, textView: TextView) {
    // Écriture dans SharedPreferences
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("username", "John Doe")
    editor.putInt("userId", 1)
    editor.apply()

    // Lecture dans SharedPreferences
    val username = sharedPreferences.getString("username", "Default User")
    val userId = sharedPreferences.getInt("userId", -1)

    // Mettre à jour le texte du TextView
    textView.text = "Nom d'utilisateur : $username, ID : $userId"
}
