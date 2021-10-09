package ch.heigvd.iict.sym.labo1

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class Utils: AppCompatActivity() {

    fun cancelButton(email: EditText, password: EditText) {
        //on va vider les champs de la page de login lors du clique sur le bouton Cancel
        email.text?.clear()
        password.text?.clear()
        // on annule les éventuels messages d'erreur présents sur les champs de saisie
        email.error = null
        password.error = null
    }


    fun checkNullEmailAndPassword(email: EditText, password: EditText, tag: String, error: String) : Boolean {
        //on récupère le contenu de deux champs dans des variables de type String
        val emailInput = email.text?.toString()
        val passwordInput = password.text?.toString()

        if(emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {
            // on affiche un message dans les logs de l'application
            Log.d(tag, "Au moins un des deux champs est vide")
            // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
            // la méthode getString permet de charger un String depuis les ressources de
            // l'application à partir de son id
            if (emailInput.isNullOrEmpty())
                email.error = error
            if (passwordInput.isNullOrEmpty())
                password.error = error
            return true
        }
        return false
    }
    private fun isValidEmail(email:String): Boolean{
        return Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)
    }
    fun checkPatternEmail(email: EditText, context: Context) : Boolean{
        val emailInput = email.text?.toString()

        if(!isValidEmail(emailInput.toString())){ //Ici on teste que l'email suit bien le pattern d'un email
            val text = "Invalid Email!" //Utilisation du template de developers.android
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
            return true
        }
        return false
    }
    private fun isValidCredentials(email:String, password:String, credentials: List<Pair<String, String>>): Boolean{
        return credentials.contains(Pair(email, password))
    }

    fun checkCredencials(email: EditText, password: EditText, credentials: List<Pair<String, String>>, context: Context): Boolean{
        //on récupère le contenu de deux champs dans des variables de type String
        val emailInput = email.text?.toString()
        val passwordInput = password.text?.toString()
        if(!isValidCredentials(emailInput.toString(), passwordInput.toString(), credentials)){
            //Ici on crée l'alert dialog
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Invalid credentials")
            builder.setMessage("You have typed the wrong credentials")
            builder.show()
            return true
        }
        return false
    }

    fun start(TAG: String) {

        Log.d(TAG, "onStart() called")
    }

    fun pause(TAG: String) {

        Log.d(TAG, "onPause() called")
    }

    fun resume(TAG: String) {

        Log.d(TAG, "onResume() called")
    }

    fun stop(TAG: String) {

        Log.d(TAG, "onStop() called")
    }

    fun destroy(TAG: String) {

        Log.d(TAG, "onDestroy() called")
    }
}

