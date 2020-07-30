package com.studio.sevenapp.android.popkornstudio.features.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseUser
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import com.studio.sevenapp.android.popkornstudio.features.game.category.GameCategoryActivity
import com.studio.sevenapp.android.popkornstudio.features.ranking.RankingActivity
import com.studio.sevenapp.android.popkornstudio.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        prepareComponents()
        listeningComponents()
        prepareObservers()
    }

    override fun onStop() {
        super.onStop()
        customDialogVisibility(state = false)
    }

    private fun prepareComponents() {
        setCustomDialogLayout(R.layout.dialog_about)
    }

    private fun listeningComponents() {
        link_sair.setOnClickListener {
            AuthUI.getInstance().signOut(this).addOnCompleteListener {
                changeScreen(
                    intent = Intent(this, SignInActivity::class.java),
                    addToStack = false
                )
            }
        }

        cardview_box_two.setOnClickListener {
            changeScreen(
                intent = Intent(this, RankingActivity::class.java),
                addToStack = true
            )
        }

        cardview_box_three.setOnClickListener {
            customDialogVisibility(state = true)
        }

        cardview_box_four.setOnClickListener {
            changeScreen(
                intent = Intent(this, GameCategoryActivity::class.java),
                addToStack = true
            )
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
        textview_user_name.text = user.displayName
        textview_user_email.text = user.email

        Glide.with(this)
            .load(user.photoUrl)
            .into(circleimageview_profile)

        user.displayName?.let {
            val firstName = user.displayName!!.split(" ")
            textview_hello.text = String.format(getString(R.string.title_home), firstName[0])
        }
    }
}
