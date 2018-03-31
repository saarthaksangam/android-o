package hellokotlinandroid.gohool.com.buttonstextviewedittext

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var text = enterNameEdt.text
        showNameButton.setOnClickListener {

            if (text.isEmpty()) resultTxt.text = "Enter name" else resultTxt.text = "Welcome " + text



        }


    }
}
