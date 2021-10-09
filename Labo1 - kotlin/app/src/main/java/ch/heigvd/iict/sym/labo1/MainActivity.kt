package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.regex.Pattern
import android.R.id

import android.content.Intent
import android.widget.TextView
import android.app.Activity




class MainActivity : AppCompatActivity() {

    // on définit une liste de couples e-mail / mot de passe
    // ceci est fait juste pour simplifier ce premier laboratoire,
    // mais il est évident que de hardcoder ceux-ci est une pratique à éviter à tout prix...
    // /!\ listOf() retourne une List<T> qui est immuable
    private var credentials = listOf(
        Pair("user1@heig-vd.ch","1234"),
        Pair("user2@heig-vd.ch","abcd")
    ).toMutableList()

    // le modifieur lateinit permet de définir des variables avec un type non-null
    // sans pour autant les initialiser immédiatement
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button
    private lateinit var newAccountLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_main)

        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        email = findViewById(R.id.main_email)
        password = findViewById(R.id.main_password)
        cancelButton = findViewById(R.id.main_cancel)
        validateButton = findViewById(R.id.main_validate)
        newAccountLink = findViewById(R.id.main_new_account)
        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        //mise en place des événements
        cancelButton.setOnClickListener {
            ActivityUtils.cancelButtonEvent(email, password)
        }
        newAccountLink.setOnClickListener{
            // Lorsqu'on clique sur le lien 'new account' on doit pouvoir lancer une nouvelle activité
            val i = Intent(this, NewActivity5::class.java);
            startActivityForResult(i, LAUNCH_NEW_ACTIVITY);
            return@setOnClickListener;
        }
        validateButton.setOnClickListener {
            //on réinitialise les messages d'erreur
            email.error = null
            password.error = null
            //on récupère le contenu de deux champs dans des variables de type String
            val emailInput = email.text?.toString()
            val error = getString(R.string.main_mandatory_field)
            when{
                ActivityUtils.fieldNullOrEmpty(email, password, TAG, error) -> return@setOnClickListener
                ActivityUtils.notValidEmail(email, applicationContext) -> return@setOnClickListener
                ActivityUtils.notValidCredentials(email, password, credentials, this) -> return@setOnClickListener
                else -> {
                    //Ici on passe en paramètre l'email et on commence une nouvelle activité
                    val i = Intent(this, NewActivity2::class.java)
                    i.putExtra("email", emailInput)
                    startActivity(i)
                    return@setOnClickListener
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LAUNCH_NEW_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                val result = data?.getStringExtra("result")
                val result2 = data?.getStringExtra("result2")
                val pair = Pair(result.toString(), result2.toString())
                credentials.add(pair)
            }
        }
    }
    override fun onStart() {
        super.onStart()
        LifeCycleUtils.start(TAG)
    }

    override fun onPause() {
        super.onPause()
        LifeCycleUtils.pause(TAG)
    }

    override fun onResume() {
        super.onResume()
        LifeCycleUtils.resume(TAG)
    }

    override fun onStop() {
        super.onStop()
        LifeCycleUtils.stop(TAG)
    }

    override fun onDestroy() {
        super.onDestroy()
        LifeCycleUtils.destroy(TAG)
    }

    // En Kotlin, les variables static ne sont pas tout à fait comme en Java
    // pour des raison de lisibilité du code, les variables et méthodes static
    // d'une classe doivent être regroupées dans un bloc à part: le companion object
    // cela permet d'avoir un aperçu plus rapide de tous les éléments static d'une classe
    // sans devoir trouver le modifieur dans la définition de ceux-ci, qui peuvent être mélangé
    // avec les autres éléments non-static de la classe
    companion object {
        private const val LAUNCH_NEW_ACTIVITY = 1
        private const val TAG: String = "MainActivity"
    }

}