package com.studio.sevenapp.android.popkornstudio.features.ranking

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import kotlinx.android.synthetic.main.activity_ranking.*
import kotlinx.android.synthetic.main.content_ranking_bottom_sheet.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class RankingActivity :
    BaseActivity<RankingViewModel>(),
    RankingCategoryAdapter.RankingCategoryItemClickListener {

    override val viewModel: RankingViewModel by inject()

    private val bottomSheetBehavior by lazy { BottomSheetBehavior.from(bottomSheet) }

    private val categoryAdapter: RankingCategoryAdapter by inject {
        parametersOf(this)
    }

    private val rankingAdapter: RankingAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        setComponentViews()
        prepareObservers()
        setComponentListener()
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
        textview_title.text = genre.name
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        viewModel.getRanking(genre = genre)
    }

    private fun setComponentViews() {
        setSupportActionBar(toolbar as Toolbar?)
        setToolbarBackButton()
        setToolbarTitle(
            getString(R.string.title_ranking)
        )

        loadStateView = loadstate

        recyclerview.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerview.adapter = rankingAdapter

        val childLayoutParams = bottomSheet.layoutParams
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        childLayoutParams.height = displayMetrics.heightPixels

        bottomSheet.layoutParams = childLayoutParams
        bottomSheetBehavior.isHideable = false

        recyclerview_category.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerview_category.adapter = categoryAdapter
    }

    private fun prepareObservers() {
        viewModel.showCategory().observe(this, Observer { genreList ->
            textview_title.text = genreList[0].name
            categoryAdapter.updatedList(newGenreList = genreList)
        })

        viewModel.showRanking().observe(this, Observer { userList ->
            rankingAdapter.updatedList(newUserList = userList)
            setloadingState(showLoading = false)
        })
    }

    private fun setComponentListener() {
        fab_filter.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}
