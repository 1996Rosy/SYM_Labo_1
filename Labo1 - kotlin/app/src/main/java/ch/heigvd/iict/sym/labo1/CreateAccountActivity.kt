package ch.heigvd.iict.sym.labo1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<LoginFragment>(R.id.login)
            }
        }




        /*setContentView(R.layout.activity_create_account)

        email = findViewById(R.id.main_email)
        password = findViewById(R.id.main_password)
        cancelButton = findViewById(R.id.main_cancel)
        validateButton = findViewById(R.id.main_validate)

        cancelButton.setOnClickListener {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            email.text?.clear()
            password.text?.clear()
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            email.error = null
            password.error = null

            setResult(Activity.RESULT_CANCELED)
            finish()
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
                Log.d("New Account", "Au moins un des deux champs est vide")
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
            } else {
                if(!validateEmail(emailInput.toString())) {
                    Toast.makeText(applicationContext,"Invalid email", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    val i = Intent()
                    i.putExtra("email", emailInput)
                    i.putExtra("password", passwordInput)
                    setResult(Activity.RESULT_OK, i)
                    finish()
                }
            }
        }*/
    }


    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}