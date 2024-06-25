package com.tchaikovsky.airtickets.presentation.selected_town

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tchaikovsky.airtickets.R
import com.tchaikovsky.airtickets.domain.entity.tickets_offers.TicketsOffer
import com.tchaikovsky.airtickets.domain.repository.AirTicketsRepository
import com.tchaikovsky.airtickets.utility.SingleEventLiveData
import com.tchaikovsky.airtickets.utility.mapCalendarDayOfWeekToDayUI
import com.tchaikovsky.airtickets.utility.mapCalendarMonthToMonthUI
import com.tchaikovsky.airtickets.utility.toStringForUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class SelectedTownViewModelImpl @Inject constructor(private val repository: AirTicketsRepository) :
    SelectedTownViewModel, ViewModel() {

    private val ticketsOfferLiveData: MutableLiveData<List<TicketsOfferUI>> = MutableLiveData()

    private val singleEventLiveData: SingleEventLiveData<SelectedTownScreenState> =
        SingleEventLiveData()

    private val exceptionHandler =
        CoroutineExceptionHandler { _, error ->
            singleEventLiveData.value =
                SelectedTownScreenState.Error((error.message ?: DEFAULT_ERROR))
        }

    private var selectedDate = Calendar.getInstance()

    init {
        viewModelScope.launch(exceptionHandler) {
            ticketsOfferLiveData.value =
                repository.getTicketsOffers().ticketsOffers.subList(0, 3).mapIndexed { index, it ->
                    mapTicketsOfferToTicketsOfferUI(index, it)
                }
        }
        singleEventLiveData.value = SelectedTownScreenState.ChangeDepartureDateState(
            mapCalendarToDateUI(selectedDate)
        )
    }

    override fun getTicketsOfferLiveData(): LiveData<List<TicketsOfferUI>> = ticketsOfferLiveData

    override fun getSingleEventLiveData(): SingleEventLiveData<SelectedTownScreenState> =
        singleEventLiveData

    override fun onClickClear() {
        singleEventLiveData.value = SelectedTownScreenState.ClearWhere
    }

    override fun onClickReverse() {
        singleEventLiveData.value = SelectedTownScreenState.ReverseWhereFromAndFrom
    }

    override fun onClickDepartureDate() {
        singleEventLiveData.value = SelectedTownScreenState.ShowCalendar
    }

    override fun onClickViewAllTickets(whereFrom: String, where: String) {
        if (whereFrom.isBlank())
            singleEventLiveData.value = SelectedTownScreenState.Error(NO_SELECTED_WHERE_FROM)
        else if (where.isBlank())
            singleEventLiveData.value = SelectedTownScreenState.Error(NO_SELECTED_WHERE)
        else
            singleEventLiveData.value =
                SelectedTownScreenState.OpenViewAllTicketsState(
                    "$whereFrom-$where",
                    mapCalendarToDateUI(selectedDate)
                )
    }

    override fun onChangeDepartureDate(date: Long) {
        selectedDate = Calendar.getInstance().apply { timeInMillis = date }
        singleEventLiveData.value = SelectedTownScreenState.ChangeDepartureDateState(
            mapCalendarToDateUI(selectedDate)
        )
    }

    private fun mapCalendarToDateUI(calendar: Calendar) =
        calendar.let {
            " ${it.get(Calendar.DAY_OF_MONTH)} ${mapCalendarMonthToMonthUI(it.get(Calendar.MONTH))}, " +
                    mapCalendarDayOfWeekToDayUI(it.get(Calendar.DAY_OF_WEEK))
        }

    private fun mapTicketsOfferToTicketsOfferUI(
        index: Int,
        ticketsOffer: TicketsOffer
    ): TicketsOfferUI =
        TicketsOfferUI(
            idImage = when (index) {
                0 -> R.drawable.red_circle
                1 -> R.drawable.blue_circle
                2 -> R.drawable.white_circle
                else -> R.drawable.white_circle
            },
            title = ticketsOffer.title,
            price = ticketsOffer.price.toStringForUI(),
            timeRange = ticketsOffer.timeRange.joinToString(" ")
        )

    companion object {
        private const val DEFAULT_ERROR = "Default error"

        private const val NO_SELECTED_WHERE_FROM = "Не выбрано место отправления"

        private const val NO_SELECTED_WHERE = "Не выбрано место назначения"
    }
}