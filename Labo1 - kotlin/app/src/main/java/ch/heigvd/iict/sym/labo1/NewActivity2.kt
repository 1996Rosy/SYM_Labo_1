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