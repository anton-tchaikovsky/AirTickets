package com.tchaikovsky.airtickets.presentation.air_tickets

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tchaikovsky.airtickets.AirTicketsApp
import com.tchaikovsky.airtickets.R
import com.tchaikovsky.airtickets.databinding.FragmentAirTicketsBinding
import com.tchaikovsky.airtickets.presentation.air_tickets.offers_list.OffersAdapter
import com.tchaikovsky.airtickets.utility.ViewBindingFragment
import com.tchaikovsky.airtickets.utility.viewModelProviderFactoryOf

class AirTicketsFragment :
    ViewBindingFragment<FragmentAirTicketsBinding>(FragmentAirTicketsBinding::inflate) {

    private val viewModel: AirTicketsViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactoryOf {
            AirTicketsApp.instance.appComponent.airTicketsScreenSubcomponent().airTicketsViewModelImpl
        })[AirTicketsViewModelImpl::class.java]
    }

    private val offersAdapter: OffersAdapter by lazy {
        OffersAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AirTicketsApp.instance.initScope()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            getSingleEventLiveData().observe(viewLifecycleOwner) {
                renderData(it)
            }
            getOffersLiveData().observe(viewLifecycleOwner) {
                renderOffers(it)
            }
        }
        initOffersRecycleView()
        initSearchView()
    }

    override fun onPause() {
        with(binding) {
            viewModel.onViewPause(whereFromEditText.text.toString(), whereEditText.text.toString())
        }
        super.onPause()
    }

    override fun onDestroy() {
        AirTicketsApp.instance.releaseScope()
        super.onDestroy()
    }

    private fun initOffersRecycleView() {
        with(binding.offersRecycleView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = offersAdapter
            val decorator = DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL).apply {
                ContextCompat.getDrawable(requireContext(), R.drawable.decorator)
                    ?.let { setDrawable(it) }
            }
            addItemDecoration(decorator)
        }
    }

    private fun initSearchView() {
        with(binding) {
            whereEditText.setText(viewModel.preferencesWhere)
            whereFromEditText.setText(viewModel.preferencesWhereFrom)
            searchButton.setOnClickListener {
                viewModel.onClickSearch(
                    whereFromEditText.text.toString(),
                    whereEditText.text.toString()
                )
            }
        }
    }

    private fun renderData(airTicketsScreenState: AirTicketsScreenState) {
        when (airTicketsScreenState) {
            is AirTicketsScreenState.Error -> Toast.makeText(
                requireContext(),
                airTicketsScreenState.message,
                Toast.LENGTH_SHORT
            ).show()

            is AirTicketsScreenState.OpenSearchScreenState -> Toast.makeText(
                requireContext(),
                "открыть модальное окно",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun renderOffers(offers: List<OfferUi>) {
        offersAdapter.setOffers(offers)
    }

    companion object {
        const val AIR_TICKETS_FRAGMENT_TAG = "AirTicketsFragmentTag"

        @JvmStatic
        fun newInstance() = AirTicketsFragment()
    }
}