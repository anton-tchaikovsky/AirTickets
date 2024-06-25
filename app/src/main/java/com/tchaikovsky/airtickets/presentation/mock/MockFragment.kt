package com.tchaikovsky.airtickets.presentation.mock

import android.os.Bundle
import android.view.View
import com.tchaikovsky.airtickets.databinding.FragmentMockBinding
import com.tchaikovsky.airtickets.utility.ViewBindingFragment

class MockFragment : ViewBindingFragment<FragmentMockBinding>(
    FragmentMockBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameTextView.text =  this.tag
    }

    companion object {
        const val TAG_HOTELS = "HOTELS"

        const val TAG_SHORT_WAY = "SHORT_WAY"

        const val TAG_SUBSCRIPTION = "SUBSCRIPTION"

        const val TAG_PROFILE = "PROFILE"

        const val TAG_HOT_TICKETS = "HOT_TICKETS"

        const val TAG_DIFFICULT_ROUTER = "DIFFICULT_ROUTER"

        const val TAG_WEEKENDS = "WEEKENDS"

        @JvmStatic
        fun newInstance() =
            MockFragment()
    }
}