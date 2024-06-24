package com.tchaikovsky.airtickets.presentation.selected_town

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
import com.tchaikovsky.airtickets.databinding.FragmentSelectedTownBinding
import com.tchaikovsky.airtickets.presentation.search_tickets.SearchTicketsFragment
import com.tchaikovsky.airtickets.presentation.selected_town.tickets_offers_list.TicketsOffersAdapter
import com.tchaikovsky.airtickets.utility.ViewBindingFragment
import com.tchaikovsky.airtickets.utility.viewModelProviderFactoryOf

class SelectedTownFragment : ViewBindingFragment<FragmentSelectedTownBinding>(
    FragmentSelectedTownBinding::inflate
) {

    private val ticketsOfferAdapter: TicketsOffersAdapter by lazy {
        TicketsOffersAdapter()
    }

    private val viewModel: SelectedTownViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactoryOf {
            AirTicketsApp.instance.appComponent.selectedTownViewModelImpl
        })[SelectedTownViewModelImpl::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            initSearchTicketsView(
                it.getString(SearchTicketsFragment.KEY_WHERE_FROM), it.getString(
                    SearchTicketsFragment.KEY_WHERE
                )
            )
        }
        initTicketsOfferRecycleView()
        initTabs()
        with(viewModel) {
            getTicketsOfferLiveData().observe(viewLifecycleOwner) { renderTicketsOffer(it) }
            getSingleEventLiveData().observe(viewLifecycleOwner) { renderData(it) }
        }
    }

    override fun onBackPressed(): Boolean {
        requireActivity().supportFragmentManager.popBackStack()
        return false
    }

    private fun renderData(selectedTownScreenState: SelectedTownScreenState) {
        when (selectedTownScreenState) {
            SelectedTownScreenState.ClearWhere -> binding.whereEditText.text?.clear()
            is SelectedTownScreenState.Error -> Toast.makeText(
                requireContext(),
                selectedTownScreenState.message,
                Toast.LENGTH_SHORT
            ).show()

            SelectedTownScreenState.ReverseWhereFromAndFrom -> {
                with(binding) {
                    val whereFrom = whereFromEditText.text
                    val where = whereEditText.text
                    whereFromEditText.text = where
                    whereEditText.text = whereFrom
                }
            }

            SelectedTownScreenState.ShowCalendar -> Log.d("@@@", "calendar")
            is SelectedTownScreenState.ViewAllTicketsState -> Log.d(
                "@@@",
                "${selectedTownScreenState.whereFrom} ${selectedTownScreenState.where}"
            )
        }
    }

    private fun renderTicketsOffer(ticketsOffers: List<TicketsOfferUI>) {
        ticketsOfferAdapter.setTicketsOffers(ticketsOffers)
    }

    private fun initTabs() {
        with(binding) {
            dateFab.setOnClickListener { viewModel.onClickDate() }
            viewAllTicketsFab.setOnClickListener {
                viewModel.onClickViewAllTickets(
                    whereFromEditText.text.toString(),
                    whereEditText.text.toString()
                )
            }
        }
    }

    private fun initSearchTicketsView(whereFrom: String?, where: String?) {
        with(binding) {
            whereFromEditText.setText(whereFrom)
            whereEditText.setText(where)
            reverseButton.setOnClickListener { viewModel.onClickReverse() }
            closeButton.setOnClickListener { viewModel.onClickClear() }
            backButton.setOnClickListener { onBackPressed() }
        }
    }

    private fun initTicketsOfferRecycleView() {
        with(binding.ticketsOfferRecycleView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = ticketsOfferAdapter
            val decorator =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).apply {
                    ContextCompat.getDrawable(
                        requireContext(),
                        com.tchaikovsky.airtickets.R.drawable.decorator_horizontal
                    )
                        ?.let { setDrawable(it) }
                }
            addItemDecoration(decorator)
        }
    }

    companion object {
        const val TAG_SELECTED_TOWN_FRAGMENT = "TagSelectedTownFragment"

        private const val KEY_WHERE_FROM = "KeyWhereFrom"

        private const val KEY_WHERE = "KeyWhere"

        @JvmStatic
        fun newInstance(whereFrom: String?, where: String?) =
            SelectedTownFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_WHERE_FROM, whereFrom)
                    putString(KEY_WHERE, where)
                }
            }
    }
}