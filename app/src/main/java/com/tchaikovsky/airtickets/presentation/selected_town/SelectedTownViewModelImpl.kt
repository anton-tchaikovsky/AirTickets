package com.tchaikovsky.airtickets.presentation.selected_town

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tchaikovsky.airtickets.resurce_provider.FotoEnum
import com.tchaikovsky.airtickets.resurce_provider.ResourcesProvider
import com.tchaikovsky.airtickets.resurce_provider.StringEnum
import com.tchaikovsky.domain.entity.tickets_offers.TicketsOffer
import com.tchaikovsky.domain.repository.AirTicketsRepository
import com.tchaikovsky.utils.SingleEventLiveData
import com.tchaikovsky.utils.mapCalendarDayOfWeekToDayUI
import com.tchaikovsky.utils.mapCalendarMonthToMonthUI
import com.tchaikovsky.utils.toStringForUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class SelectedTownViewModelImpl @Inject constructor(
    private val repository: AirTicketsRepository,
    private val resourceProvider: ResourcesProvider
) :
    SelectedTownViewModel, ViewModel() {

    private val ticketsOfferLiveData: MutableLiveData<List<TicketsOfferUI>> = MutableLiveData()

    private val dateLiveData: MutableLiveData<String> = MutableLiveData()

    private val singleEventLiveData: SingleEventLiveData<SelectedTownScreenState> =
        SingleEventLiveData()

    private val exceptionHandler =
        CoroutineExceptionHandler { _, error ->
            singleEventLiveData.value =
                SelectedTownScreenState.Error(
                    (error.message ?: resourceProvider.getString(
                        StringEnum.DEFAULT_ERROR
                    ))
                )
        }

    private var selectedDate = Calendar.getInstance()

    init {
        viewModelScope.launch(exceptionHandler) {
            ticketsOfferLiveData.value =
                repository.getTicketsOffers().ticketsOffers.subList(0, 3).mapIndexed { index, it ->
                    mapTicketsOfferToTicketsOfferUI(index, it)
                }
        }
        dateLiveData.value = mapCalendarToShortDateUI(selectedDate)
    }

    override fun getTicketsOfferLiveData(): LiveData<List<TicketsOfferUI>> = ticketsOfferLiveData

    override fun getSingleEventLiveData(): SingleEventLiveData<SelectedTownScreenState> =
        singleEventLiveData

    override fun getDateLivData(): LiveData<String> = dateLiveData

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
            singleEventLiveData.value = SelectedTownScreenState.Error(
                resourceProvider.getString(
                    StringEnum.NO_SELECTED_WHERE_FROM
                )
            )
        else if (where.isBlank())
            singleEventLiveData.value = SelectedTownScreenState.Error(
                resourceProvider.getString(
                    StringEnum.NO_SELECTED_WHERE
                )
            )
        else
            singleEventLiveData.value =
                SelectedTownScreenState.OpenViewAllTicketsState(
                    String.format(resourceProvider.getString(StringEnum.FLIGHT), whereFrom, where),
                    mapCalendarToLongDateUI(selectedDate)
                )
    }

    override fun onChangeDepartureDate(date: Long) {
        selectedDate = Calendar.getInstance().apply { timeInMillis = date }
        dateLiveData.value = mapCalendarToShortDateUI(selectedDate)
    }

    private fun mapCalendarToShortDateUI(calendar: Calendar) =
        calendar.let {
            String.format(
                resourceProvider.getString(StringEnum.SHORT_DATE),
                it.get(Calendar.DAY_OF_MONTH),
                mapCalendarMonthToMonthUI(it.get(Calendar.MONTH)).first,
                mapCalendarDayOfWeekToDayUI(it.get(Calendar.DAY_OF_WEEK))
            )
        }

    private fun mapCalendarToLongDateUI(calendar: Calendar) =
        calendar.let {
            String.format(
                resourceProvider.getString(StringEnum.LONG_DATE),
                it.get(Calendar.DAY_OF_MONTH),
                mapCalendarMonthToMonthUI(it.get(Calendar.MONTH)).second
            )
        }

    private fun mapTicketsOfferToTicketsOfferUI(
        index: Int,
        ticketsOffer: TicketsOffer
    ): TicketsOfferUI =
        TicketsOfferUI(
            idImage = when (index) {
                0 -> resourceProvider.getFotoId(FotoEnum.RED_CIRCLE)
                1 -> resourceProvider.getFotoId(FotoEnum.BLUE_CIRCLE)
                2 -> resourceProvider.getFotoId(FotoEnum.WHITE_CIRCLE)
                else -> resourceProvider.getFotoId(FotoEnum.WHITE_CIRCLE)
            },
            title = ticketsOffer.title,
            price = ticketsOffer.price.toStringForUI(),
            timeRange = ticketsOffer.timeRange.joinToString(" ")
        )
}