package com.tchaikovsky.airtickets.presentation.search_tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tchaikovsky.airtickets.resurce_provider.FotoEnum
import com.tchaikovsky.airtickets.resurce_provider.ResourcesProvider
import com.tchaikovsky.airtickets.resurce_provider.StringEnum
import com.tchaikovsky.domain.entity.Popular
import com.tchaikovsky.domain.repository.AirTicketsRepository
import com.tchaikovsky.utils.SingleEventLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchTicketsViewModelImpl @Inject constructor(
    private val repository: AirTicketsRepository,
    private val resourceProvider: ResourcesProvider
) :
    SearchTicketsViewModel, ViewModel() {

    private val popularsLiveData: MutableLiveData<List<PopularUI>> = MutableLiveData()

    private val singleEventLiveData: SingleEventLiveData<SearchTicketsScreenState> =
        SingleEventLiveData()

    private val exceptionHandler =
        CoroutineExceptionHandler { _, error ->
            singleEventLiveData.value =
                SearchTicketsScreenState.Error(
                    (error.message ?: resourceProvider.getString(
                        StringEnum.DEFAULT_ERROR
                    ))
                )
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
            singleEventLiveData.value =
                SearchTicketsScreenState.Error(resourceProvider.getString(StringEnum.NO_SELECTED_WHERE_FROM))
        else if (where.isBlank())
            singleEventLiveData.value =
                SearchTicketsScreenState.Error(resourceProvider.getString(StringEnum.NO_SELECTED_WHERE))
        else
            singleEventLiveData.value =
                SearchTicketsScreenState.SearchState(whereFrom, where)
    }

    override fun onClickClear() {
        singleEventLiveData.value = SearchTicketsScreenState.ClearWhere
    }

    override fun onClickTab(tab: Tab) {
        singleEventLiveData.value =
            when (tab) {
                Tab.DIFFICULT_ROUTER -> SearchTicketsScreenState.OpenTab.DIFFICULT_ROUTER
                Tab.ANYWHERE -> SearchTicketsScreenState.WhereState(
                    resourceProvider.getString(
                        StringEnum.ANYWHERE
                    )
                )

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
                1 -> resourceProvider.getFotoId(FotoEnum.FIRST_POPULAR)
                2 -> resourceProvider.getFotoId(FotoEnum.SECOND_POPULAR)
                3 -> resourceProvider.getFotoId(FotoEnum.THIRD_POPULAR)
                else -> resourceProvider.getFotoId(FotoEnum.DEFAULT_FOTO)
            },
            town = popular.town
        )
}