package com.studio.sevenapp.android.popkornstudio.features.news

import android.os.Bundle
import com.studio.sevenapp.android.domain.model.News
import com.studio.sevenapp.android.domain.model.SimpleNews
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseFragment
import com.studio.sevenapp.android.popkornstudio.base.EmptyViewModel
import kotlinx.android.synthetic.main.fragment_simple_news.*
import org.koin.android.ext.android.inject

class SimpleNewsFragment : BaseFragment<EmptyViewModel>(R.layout.fragment_simple_news) {

    private val simpleNews: SimpleNews? by lazy {
        arguments?.getParcelable(NEWS_PARAM) as SimpleNews
    }

    override val viewModel: EmptyViewModel by inject()

    override fun onResume() {
        super.onResume()
        prepareComponents()
    }

    private fun prepareComponents() {
        simpleNews?.let { news ->
            textview_title.text = news.title
            textview_description.text = news.description
        }
    }

    companion object {
        private const val NEWS_PARAM = "News"

        fun params(news: News) =
            SimpleNewsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(NEWS_PARAM, news)
                }
            }
    }
}