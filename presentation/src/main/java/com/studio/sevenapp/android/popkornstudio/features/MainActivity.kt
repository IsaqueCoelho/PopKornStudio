package com.studio.sevenapp.android.popkornstudio.features

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.studio.sevenapp.android.popkornstudio.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_loggout.setOnClickListener {
            AuthUI.getInstance().signOut(this).addOnCompleteListener {
                Toast.makeText(this, "loggout", Toast.LENGTH_LONG).show()
            }
        }
    }
}
