package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class NewActivity2 : AppCompatActivity() {

    private lateinit var connected_image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new2)
        connected_image = findViewById(R.id.profile_photo)

        val textView = findViewById<TextView>(R.id.new_email)
        textView.setText(intent.getStringExtra("email")).toString()

        ImageDownloader(connected_image, "https://thispersondoesnotexist.com/image").show()
    }
}