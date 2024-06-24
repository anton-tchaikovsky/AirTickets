package com.tchaikovsky.airtickets.presentation.search_tickets

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tchaikovsky.airtickets.AirTicketsApp
import com.tchaikovsky.airtickets.databinding.FragmentSearchTicketsBinding
import com.tchaikovsky.airtickets.presentation.search_tickets.popular_list.PopularsAdapter
import com.tchaikovsky.airtickets.utility.ViewBindingFragment
import com.tchaikovsky.airtickets.utility.viewModelProviderFactoryOf

class SearchTicketsFragment : ViewBindingFragment<FragmentSearchTicketsBinding>(
    FragmentSearchTicketsBinding::inflate
) {
    private val popularsAdapter: PopularsAdapter by lazy {
        PopularsAdapter(::onClickPopular)
    }

    private val viewModel: SearchTicketsViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactoryOf {
            AirTicketsApp.instance.appComponent.searchTicketsViewModelImpl
        })[SearchTicketsViewModelImpl::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            initSearchTicketsView(it.getString(KEY_WHERE_FROM), it.getString(KEY_WHERE))
        }
        initPopularsRecycleView()
        initTab()
        with(viewModel) {
            getPopularsLiveData().observe(viewLifecycleOwner) { renderPopulars(it) }
            getSingleEventLiveData().observe(viewLifecycleOwner) { renderData(it) }
        }
    }

    private fun initTab() {
        with(binding) {
            difficultRouteImageButton.setOnClickListener {
                viewModel.onClickTab(Tab.DIFFICULT_ROUTER)
            }
            anywhereButton.setOnClickListener {
                viewModel.onClickTab(Tab.ANYWHERE)
            }
            weekendsButton.setOnClickListener {
                viewModel.onClickTab(Tab.WEEKENDS)
            }
            hotTicketsButton.setOnClickListener {
                viewModel.onClickTab(Tab.HOT_TICKETS)
            }
        }
    }

    private fun initSearchTicketsView(whereFrom: String?, where: String?) {
        with(binding) {
            whereFromEditText.setText(whereFrom)
            whereEditText.setText(where)
            searchButton.setOnClickListener {
                viewModel.onClickSearch(
                    whereFromEditText.text.toString(),
                    whereEditText.text.toString()
                )
            }
            closeButton.setOnClickListener { viewModel.onClickClear() }
        }
    }

    private fun onClickPopular(popularWhere: String) {
        viewModel.onClickPopular(popularWhere)
    }

    private fun initPopularsRecycleView() {
        with(binding.popularRecycleView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = popularsAdapter
            val decorator =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).apply {
                    ContextCompat.getDrawable(
                        requireContext(),
                        com.tchaikovsky.airtickets.R.drawable.decorator_populars
                    )
                        ?.let { setDrawable(it) }
                }
            addItemDecoration(decorator)
        }
    }

    private fun renderData(searchTicketsScreenState: SearchTicketsScreenState) {
        when (searchTicketsScreenState) {
            SearchTicketsScreenState.ClearWhere -> binding.whereEditText.text?.clear()
            is SearchTicketsScreenState.Error -> Toast.makeText(
                requireContext(),
                searchTicketsScreenState.message,
                Toast.LENGTH_SHORT
            ).show()

            is SearchTicketsScreenState.OpenTab -> Log.d("@@@", searchTicketsScreenState.name)

            is SearchTicketsScreenState.SearchState -> Log.d("@@@", "search")
            is SearchTicketsScreenState.WhereState -> binding.whereEditText.setText(
                searchTicketsScreenState.where
            )
        }
    }

    private fun renderPopulars(populars: List<PopularUI>) {
        popularsAdapter.setPopulars(populars)
    }

    companion object {
        const val TAG_SEARCH_BOTTOM_SHEET_DIALOG_FRAGMENT = "TagSearchBottomSheetDialogFragment"

        const val KEY_WHERE_FROM = "KeyWhereFrom"

        const val KEY_WHERE = "KeyWhere"

        @JvmStatic
        fun newInstance(whereFrom: String?, where: String?) =
            SearchTicketsFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_WHERE_FROM, whereFrom)
                    putString(KEY_WHERE, where)
                }
            }
    }
}