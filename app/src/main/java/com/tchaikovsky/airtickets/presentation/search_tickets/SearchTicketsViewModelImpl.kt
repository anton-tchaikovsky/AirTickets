package com.tchaikovsky.airtickets.presentation.search_tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tchaikovsky.airtickets.R
import com.tchaikovsky.airtickets.domain.entity.Popular
import com.tchaikovsky.airtickets.domain.repository.AirTicketsRepository
import com.tchaikovsky.airtickets.utility.SingleEventLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchTicketsViewModelImpl @Inject constructor(private val repository: AirTicketsRepository) :
    SearchTicketsViewModel, ViewModel() {

    private val popularsLiveData: MutableLiveData<List<PopularUI>> = MutableLiveData()

    private val singleEventLiveData: SingleEventLiveData<SearchTicketsScreenState> =
        SingleEventLiveData()

    private val exceptionHandler =
        CoroutineExceptionHandler { _, error ->
            singleEventLiveData.value =
                SearchTicketsScreenState.Error((error.message ?: DEFAULT_ERROR))
        }

    init {
        viewModelScope.launch(exceptionHandler) {
            popularsLiveData.value = repository.getMockPopulars().map {
                mapPopularToPopularUI(it)
            }
        }
    }

    override fun getPopularsLiveData(): LiveData<List<PopularUI>> = popularsLiveData

    override fun getSingleEventLiveData(): SingleEventLiveData<SearchTicketsScreenState> =
        singleEventLiveData

    override fun onClickSearch(whereFrom: String, where: String) {
        if (whereFrom.isBlank())
            singleEventLiveData.value = SearchTicketsScreenState.Error(NO_SELECTED_WHERE_FROM)
        else if (where.isBlank())
            singleEventLiveData.value = SearchTicketsScreenState.Error(NO_SELECTED_WHERE)
        else
            singleEventLiveData.value = SearchTicketsScreenState.SearchState(whereFrom, where)
    }

    override fun onClickClear() {
        singleEventLiveData.value = SearchTicketsScreenState.ClearWhere
    }

    override fun onClickTab(tab: Tab) {
        singleEventLiveData.value =
            when (tab) {
                Tab.DIFFICULT_ROUTER -> SearchTicketsScreenState.OpenTab.DIFFICULT_ROUTER
                Tab.ANYWHERE -> SearchTicketsScreenState.WhereState(ANYWHERE)
                Tab.WEEKENDS -> SearchTicketsScreenState.OpenTab.WEEKENDS
                Tab.HOT_TICKETS -> SearchTicketsScreenState.OpenTab.HOT_TICKETS
            }
    }

    override fun onClickPopular(popular: String) {
        singleEventLiveData.value = SearchTicketsScreenState.WhereState(popular)
    }

    private fun mapPopularToPopularUI(popular: Popular): PopularUI =
        PopularUI(
            idImage = when (popular.id) {
                1 -> R.drawable.first_popular
                2 -> R.drawable.second_popular
                3 -> R.drawable.third_popular
                else -> R.drawable.ic_launcher_foreground
            },
            town = popular.town
        )

    companion object {
        private const val DEFAULT_ERROR = "Default error"

        private const val NO_SELECTED_WHERE_FROM = "Не выбрано место отправления"

        private const val NO_SELECTED_WHERE = "Не выбрано место назначения"

        private const val ANYWHERE = "Куда угодно"
    }
}