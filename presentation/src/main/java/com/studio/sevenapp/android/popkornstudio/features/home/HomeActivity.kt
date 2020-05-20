package com.studio.sevenapp.android.popkornstudio.features.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseUser
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import com.studio.sevenapp.android.popkornstudio.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listeningComponents()
        prepareObservers()
    }

    private fun listeningComponents() {
        link_sair.setOnClickListener {
            AuthUI.getInstance().signOut(this).addOnCompleteListener {
                Toast.makeText(this, "loggout", Toast.LENGTH_LONG).show()
                changeScreen( Intent(this, SignInActivity::class.java), addToStack = false )
            }
        }
    }

    private fun prepareObservers() {
        viewModel.showUser().observe(this, Observer { user ->
            user?.let {
                setUserProfile(it)
            }
        })
    }

    private fun setUserProfile(user: FirebaseUser) {
        val firstName = user.displayName!!.split(" ")
        textview_user_name.text = user.displayName
        textview_user_email.text = user.email

        textview_hello.text = String.format(getString(R.string.title_home), firstName[0])

        Glide.with(this)
            .load(user.photoUrl)
            .into(circleimageview_profile)
    }
}
