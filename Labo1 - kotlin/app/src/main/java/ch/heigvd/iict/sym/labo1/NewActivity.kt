package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new2)

        // Get the Intent that started this activity and extract the string
        val email = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.email).apply {
            text = email
        }

        ImageDownloader(findViewById(R.id.image),"https://thispersondoesnotexist.com/image").show()

    }
}