package ch.heigvd.iict.sym.labo1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.widget.TextView


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
    private lateinit var createButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage

            setContentView(R.layout.activity_main)



credentials.toMutableList()
        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        email = findViewById(R.id.main_email)
        password = findViewById(R.id.main_password)
        cancelButton = findViewById(R.id.main_cancel)
        validateButton = findViewById(R.id.main_validate)
        createButton= findViewById(R.id.main_new_account)

        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        //mise en place des événements
        cancelButton.setOnClickListener {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            email.text?.clear()
            password.text?.clear()
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            email.error = null
            password.error = null
        }

        validateButton.setOnClickListener {
            //on réinitialise les messages d'erreur
            email.error = null
            password.error = null

            //on récupère le contenu de deux champs dans des variables de type String
            val emailInput = email.text?.toString()
            val passwordInput = password.text?.toString()



            if(emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {
                // on affiche un message dans les logs de l'application
                Log.d(TAG, "Au moins un des deux champs est vide")
                // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
                // la méthode getString permet de charger un String depuis les ressources de
                // l'application à partir de son id
                if(emailInput.isNullOrEmpty())
                    email.error = getString(R.string.main_mandatory_field)
                if(passwordInput.isNullOrEmpty())
                    password.error = getString(R.string.main_mandatory_field)
                // Pour les fonctions lambda, on doit préciser à quelle fonction l'appel à return
                // doit être appliqué
                return@setOnClickListener
            }else{
                val value = Patterns.EMAIL_ADDRESS;
               if(!value.matcher(emailInput).matches()) {
                   Toast.makeText(applicationContext, "invalid Email!", Toast.LENGTH_SHORT).show()
               } else {
                   if(!credentials.contains(Pair(emailInput,passwordInput))){
                       val alertDialog = AlertDialog.Builder(this)
                       alertDialog.apply {
                           setTitle("Email or Password are not correct!")
                       }.create().show()
                   }else{
                       val intent = Intent(this, SecondActivity::class.java)
                       intent.putExtra("Email", emailInput) //Optional parameters

                      startActivity(intent)
                   }

               }

            }

        }
        createButton.setOnClickListener{
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivityForResult(intent,1)

        }

        }
    // see: https://tutorial.eyehunts.com/android/getting-a-result-from-an-activity-android-startactivityforresult-example-kotlin/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val email = data?.getStringExtra("email")
                val password= data?.getStringExtra("password")
                credentials.add( Pair(email.toString(), password.toString()))
            }

        }
    }

    // En Kotlin, les variables static ne sont pas tout à fait comme en Java
    // pour des raison de lisibilité du code, les variables et méthodes static
    // d'une classe doivent être regroupées dans un bloc à part: le companion object
    // cela permet d'avoir un aperçu plus rapide de tous les éléments static d'une classe
    // sans devoir trouver le modifieur dans la définition de ceux-ci, qui peuvent être mélangé
    // avec les autres éléments non-static de la classe
    companion object {
        private const val TAG: String = "MainActivity"
    }

}
