package ch.heigvd.iict.sym.labo1

import android.app.*
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button
    private lateinit var newAccountButton: TextView

    private val credentials = listOf(
        Pair("user1@heig-vd.ch","1234"),
        Pair("user2@heig-vd.ch","abcd")
    ).toMutableList()

    private val newAccountContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK) {
            val newEmail = result.data?.getStringExtra("email")
            val newPassword = result.data?.getStringExtra("password")

            //(credentials as MutableList<Pair<String, String>>)[1]=(Pair(newEmail.toString(), newPassword.toString()))
            credentials.add(Pair(newEmail.toString(), newPassword.toString()))
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container,false)


      
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    fun verifyCredential(credentials: List<Pair<String, String>>, email: String, password: String): Boolean {
        return credentials.contains(Pair(email, password))
    }

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}