package com.tchaikovsky.airtickets.presentation.view_all_tickets

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tchaikovsky.airtickets.AirTicketsApp
import com.tchaikovsky.airtickets.databinding.FragmentViewAllTicketsBinding
import com.tchaikovsky.airtickets.presentation.view_all_tickets.ticketsList.TicketsAdapter
import com.tchaikovsky.airtickets.utility.ViewBindingFragment
import com.tchaikovsky.airtickets.utility.viewModelProviderFactoryOf

class ViewAllTicketsFragment : ViewBindingFragment<FragmentViewAllTicketsBinding>(
    FragmentViewAllTicketsBinding::inflate
) {

    private val ticketsAdapter: TicketsAdapter by lazy {
        TicketsAdapter()
    }

    private val viewModel: ViewAllTicketsViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactoryOf {
            AirTicketsApp.instance.appComponent.viewAllTicketsViewModelImpl
        })[ViewAllTicketsViewModelImpl::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            initFlightInfoView(
                it.getString(KEY_FLIGHT), it.getString(KEY_FLIGHT_INFO)
            )
        }
        initTicketsRecycleView()
        with(viewModel) {
            getTicketsLiveData().observe(viewLifecycleOwner) { renderTickets(it) }
            getSingleEventLiveData().observe(viewLifecycleOwner) { renderData(it) }
        }

    }

    override fun onBackPressed(): Boolean {
        requireActivity().supportFragmentManager.popBackStack()
        return false
    }

    private fun initFlightInfoView(flight: String?, flyInfo: String?) {
        with(binding) {
            flightTextView.text = flight
            infoFlightTextView.text = flyInfo
            backButton.setOnClickListener { onBackPressed() }
        }
    }

    private fun renderTickets(tickets: List<TicketUI>) {
        ticketsAdapter.setTickets(tickets)
    }

    private fun renderData(allTicketsScreenState: ViewAllTicketsScreenState) {
        when (allTicketsScreenState) {
            is ViewAllTicketsScreenState.Error -> Toast.makeText(
                requireContext(),
                allTicketsScreenState.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initTicketsRecycleView() {
        with(binding.ticketsRecycleView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = ticketsAdapter
            val decorator =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).apply {
                    androidx.core.content.ContextCompat.getDrawable(
                        requireContext(),
                        com.tchaikovsky.airtickets.R.drawable.decorator_horizontal_large
                    )
                        ?.let { setDrawable(it) }
                }
            addItemDecoration(decorator)
        }
    }

    companion object {
        const val TAG_VIEW_ALL_TICKETS_FRAGMENT = "TagViewAllTicketsFragment"

        private const val KEY_FLIGHT = "KeyFlight"

        private const val KEY_FLIGHT_INFO = "KeyFlightInfo"

        @JvmStatic
        fun newInstance(flight: String, flightInfo: String) =
            ViewAllTicketsFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_FLIGHT, flight)
                    putString(KEY_FLIGHT_INFO, flightInfo)
                }
            }
    }
}