package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import ch.heigvd.iict.sym.labo1.R


class CreateAccountActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button
    private lateinit var createButton: TextView
    private   var utils=Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // on définit le layout à utiliser pour l'affichage

        setContentView(R.layout.content_create_account)


        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        email = findViewById(R.id.new_main_email)
        password = findViewById(R.id.new_main_password)
        cancelButton = findViewById(R.id.create_cancel)
        validateButton = findViewById(R.id.create_validate)
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
            val error = getString(R.string.main_mandatory_field)



            when{
                utils.checkNullEmailAndPassword(email, password, TAG, error) -> return@setOnClickListener
                utils.checkPatternEmail(email, applicationContext) -> return@setOnClickListener

                else -> {

                    // Put the String to pass back into an Intent and close this activity
                    val intent = Intent()
                    intent.putExtra("email", emailInput)
                    intent.putExtra("password", passwordInput)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }

            }

        }
    }
    override fun onStart() {
        super.onStart()

        utils.start(TAG)
    }

    override fun onPause() {
        super.onPause()
        utils.pause(TAG)

    }

    override fun onResume() {
        super.onResume()
        utils.resume(TAG)
    }

    override fun onStop() {
        super.onStop()
        utils.stop(TAG)
    }

    override fun onDestroy() {
        super.onDestroy()
        utils.destroy(TAG)
    }
    companion object {
        private const val TAG: String = "CreateAcountActivity"
    }


}