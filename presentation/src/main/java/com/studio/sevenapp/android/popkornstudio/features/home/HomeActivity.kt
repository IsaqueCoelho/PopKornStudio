package com.studio.sevenapp.android.popkornstudio.features.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseUser
import com.studio.sevenapp.android.popkornstudio.BuildConfig
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
        loadStateView = loadstate
    }

    private fun listeningComponents() {
        link_sair.setOnClickListener {
            logout()
        }

        cardview_box_one.setOnClickListener {
            rateApp()
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

    private fun logout() {
        AuthUI.getInstance().signOut(this).addOnCompleteListener {
            changeScreen(
                intent = Intent(this, SignInActivity::class.java),
                addToStack = false
            )
        }
    }

    private fun rateApp() {
        try {
            val rateIntent = rateIntentForUrl(BuildConfig.PLAY_STORE_MARKET_DETAILS)
            startActivity(rateIntent)
        } catch (e: ActivityNotFoundException) {
            val rateIntent =
                rateIntentForUrl(BuildConfig.PLAY_STORE_MARKET_DETAILS_FULL_URL)
            startActivity(rateIntent)
        }
    }

    private fun rateIntentForUrl(url: String): Intent? {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(String.format("%s?id=%s", url, packageName))
        )
        var flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        flags = flags or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
        intent.addFlags(flags)
        return intent
    }

    private fun prepareObservers() {
        viewModel.showUser().observe(this, Observer { user ->
            user?.let {
                setUserProfile(it)
            }
        })

        viewModel.showNews().observe(this, Observer {newsFragment ->
            loadNewsFragment(
                fragment = newsFragment
            )
            setloadingState(showLoading = false)
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

    private fun loadNewsFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container_news, fragment)
        fragmentTransaction.commit()
    }
}
