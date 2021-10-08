package ch.heigvd.iict.sym.labo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.regex.Pattern
import android.app.Activity




class NewActivity5 : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new5)

        email = findViewById(R.id.main_email)
        password = findViewById(R.id.main_password)
        cancelButton = findViewById(R.id.main_cancel)
        validateButton = findViewById(R.id.main_validate)

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
            }
            else{
                //TODO à completer
                if(!isValidEmail(emailInput.toString())){ //Ici on teste que l'email suit bien le pattern d'un email
                    val text = "Invalid Email!" //Utilisation du template de developers.android
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                    return@setOnClickListener
                }

                else{
                    val returnIntent = Intent()
                    returnIntent.putExtra("result", emailInput.toString())
                    returnIntent.putExtra("result2", passwordInput.toString())
                    setResult(RESULT_OK, returnIntent)
                    finish()
                    return@setOnClickListener
                }

            }

        }

    }
    companion object {
        private const val TAG: String = "NewActivity5"
        private fun isValidEmail(email:String): Boolean{
            return Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)
        }

    }
}