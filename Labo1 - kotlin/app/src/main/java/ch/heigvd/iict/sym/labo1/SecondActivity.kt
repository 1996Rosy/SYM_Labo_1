package ch.heigvd.iict.sym.labo1

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class SecondActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private   var utils=Utils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val textView = findViewById<TextView>(R.id.second_email)
        textView.setText(intent.getStringExtra("Email")).toString()
        ImageDownloader(findViewById(R.id.image),"https://thispersondoesnotexist.com/image").show()


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
        private const val TAG: String = "SecondActivity"
    }
}