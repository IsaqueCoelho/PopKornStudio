package com.studio.sevenapp.android.popkornstudio.features.game.category

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import kotlinx.android.synthetic.main.activity_category.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameCategoryActivity : BaseActivity<GameCategoryViewModel>() {

    override val viewModel: GameCategoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        setComponentViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setComponentViews() {
        setSupportActionBar(toolbar as Toolbar?)
        setToolbarBackButton()
        setToolbarTitle(
            getString(R.string.title_game_choice_category)
        )
    }
}
