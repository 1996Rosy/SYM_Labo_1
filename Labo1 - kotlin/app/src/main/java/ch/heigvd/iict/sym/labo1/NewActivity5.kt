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
            ActivityUtils.cancelButtonEvent(email, password)
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
                ActivityUtils.fieldNullOrEmpty(email, password, TAG, error) -> return@setOnClickListener
                ActivityUtils.notValidEmail(email, applicationContext) -> return@setOnClickListener
                else -> {
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
    companion object {
        private const val TAG: String = "NewActivity5"
    }
}