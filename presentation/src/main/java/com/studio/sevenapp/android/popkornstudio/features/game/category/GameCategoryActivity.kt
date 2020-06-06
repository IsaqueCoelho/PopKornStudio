package com.studio.sevenapp.android.popkornstudio.features.game.category

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import com.studio.sevenapp.android.popkornstudio.features.game.challenge.ChallengeActivity
import kotlinx.android.synthetic.main.activity_category.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GameCategoryActivity : BaseActivity<GameCategoryViewModel>(),
    GameCategoryAdapter.GameCategoryItemClickListener {

    override val viewModel: GameCategoryViewModel by viewModel()

    private val adapter: GameCategoryAdapter by inject {
        parametersOf(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
    }

    override fun onResume() {
        super.onResume()
        setComponentViews()
        prepareObservers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(genre: Genre) {
        changeScreen(
            intent = Intent(
                this,
                ChallengeActivity::class.java
            ).putExtras(ChallengeActivity.paramsChallengeType(genre)),
            addToStack = false
        )
    }

    private fun setComponentViews() {
        setSupportActionBar(toolbar as Toolbar?)
        setToolbarBackButton()
        setToolbarTitle(
            getString(R.string.title_game_choice_category)
        )

        loadStateView = loadstate

        recyclerview.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerview.adapter = adapter
    }

    private fun prepareObservers() {
        viewModel.showCategory().observe(this, Observer { movieCategoryList ->
            adapter.updatedList(movieCategoryList)
            setloadingState(false)
        })
    }
}
