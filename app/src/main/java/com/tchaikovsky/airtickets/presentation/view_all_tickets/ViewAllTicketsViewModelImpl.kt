package com.tchaikovsky.airtickets.presentation.view_all_tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tchaikovsky.airtickets.data.resurce_provider.ResourcesProvider
import com.tchaikovsky.airtickets.data.resurce_provider.StringEnum
import com.tchaikovsky.airtickets.domain.entity.tickets.Ticket
import com.tchaikovsky.airtickets.domain.repository.AirTicketsRepository
import com.tchaikovsky.airtickets.utility.SingleEventLiveData
import com.tchaikovsky.airtickets.utility.toStringForUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds


class ViewAllTicketsViewModelImpl @Inject constructor(
    private val repository: AirTicketsRepository,
    private val resourceProvider: ResourcesProvider
) :
    ViewAllTicketsViewModel, ViewModel() {

    private val ticketsLiveData: MutableLiveData<List<TicketUI>> = MutableLiveData()

    private val singleEventLiveData: SingleEventLiveData<ViewAllTicketsScreenState> =
        SingleEventLiveData()

    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        singleEventLiveData.value =
            ViewAllTicketsScreenState.Error(
                (error.message ?: resourceProvider.getString(StringEnum.DEFAULT_ERROR))
            )
    }

    init {
        viewModelScope.launch(exceptionHandler) {
            ticketsLiveData.value = repository.getTickets().tickets.map {
                mapTicketToTicketUI(it)
            }
        }
    }

    override fun getTicketsLiveData(): LiveData<List<TicketUI>> = ticketsLiveData

    override fun getSingleEventLiveData(): SingleEventLiveData<ViewAllTicketsScreenState> =
        singleEventLiveData

    private fun mapTicketToTicketUI(ticket: Ticket): TicketUI {
        val firstCalendar = convertTimeToCalendar(ticket.departure.date)
        val secondCalendar = convertTimeToCalendar(ticket.arrival.date)
        return TicketUI(
            badge = ticket.badge,
            departurePort = ticket.departure.airport,
            arrivalPort = ticket.arrival.airport,
            hasTransfer = ticket.hasTransfer,
            price = ticket.price.toStringForUI(),
            departureTime = convertCalendarToTimeUI(firstCalendar),
            arrivalTime = convertCalendarToTimeUI(secondCalendar),
            flightTime = calculateFlightTimeUI(firstCalendar, secondCalendar)
        )
    }

    private fun convertTimeToCalendar(time: String): Calendar {
        val format: DateFormat = SimpleDateFormat(TIME_PATTERN, Locale.UK)
        val calender = Calendar.getInstance()
        format.parse(time)?.let { calender.time = it }
        return calender
    }

    private fun convertCalendarToTimeUI(calendar: Calendar) =
        String.format(
            resourceProvider.getString(StringEnum.TIME),
            String.format(Locale.UK, "%02d", calendar.get(Calendar.HOUR_OF_DAY)),
            String.format(Locale.UK, "%02d", calendar.get(Calendar.MINUTE))
        )

    private fun calculateFlightTimeUI(firstCalendar: Calendar, secondCalendar: Calendar): String {
        val duration = (secondCalendar.time.time - firstCalendar.time.time).milliseconds
        return duration.toComponents { hours, minutes, _, _ ->
            String.format(resourceProvider.getString(StringEnum.FLIGHT_TIME), hours, minutes)
        }
    }

    companion object {
        private const val TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
    }

}