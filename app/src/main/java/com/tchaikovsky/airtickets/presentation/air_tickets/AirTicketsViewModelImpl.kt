package com.tchaikovsky.airtickets.presentation.air_tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tchaikovsky.airtickets.resurce_provider.FotoEnum
import com.tchaikovsky.airtickets.resurce_provider.ResourcesProvider
import com.tchaikovsky.airtickets.resurce_provider.StringEnum
import com.tchaikovsky.domain.entity.offers.Offer
import com.tchaikovsky.domain.repository.AirTicketsRepository
import com.tchaikovsky.utils.SingleEventLiveData
import com.tchaikovsky.utils.toStringForUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class AirTicketsViewModelImpl @Inject constructor(
    private val repository: AirTicketsRepository,
    private val resourceProvider: ResourcesProvider
) :
    AirTicketsViewModel, ViewModel() {

    private val offersLiveData: MutableLiveData<List<OfferUi>> = MutableLiveData()

    private val singleEventLiveData: SingleEventLiveData<AirTicketsScreenState> =
        SingleEventLiveData()

    private val exceptionHandler =
        CoroutineExceptionHandler { _, error ->
            singleEventLiveData.value =
                AirTicketsScreenState.Error(
                    error.message ?: resourceProvider.getString(StringEnum.DEFAULT_ERROR)
                )
        }

    override var preferencesWhere: String?

    override var preferencesWhereFrom: String?

    init {
        with(repository) {
            readPreferences().let {
                val where = it.second
                val whereFrom = it.first
                preferencesWhere = if (where.isNullOrBlank())
                    null
                else
                    where
                preferencesWhereFrom = if (where.isNullOrBlank())
                    null
                else
                    whereFrom
            }

            this@AirTicketsViewModelImpl.viewModelScope.launch(exceptionHandler) {
                offersLiveData.value = getOffers().offers.map {
                    mapOfferToOfferUI(it)
                }
            }
        }
    }

    override fun getOffersLiveData(): LiveData<List<OfferUi>> = offersLiveData

    override fun getSingleEventLiveData(): SingleEventLiveData<AirTicketsScreenState> =
        singleEventLiveData

    override fun onClickSearch(preferencesWhereFrom: String, preferencesWhere: String) {
        updatePreference(preferencesWhereFrom, preferencesWhere)
        singleEventLiveData.value =
            AirTicketsScreenState.PreferencesState(preferencesWhereFrom, preferencesWhere)
    }

    override fun onViewPause(preferencesWhereFrom: String, preferencesWhere: String) {
        updatePreference(preferencesWhereFrom, preferencesWhere)
    }

    private fun updatePreference(preferencesWhereFrom: String, preferencesWhere: String) {
        this.preferencesWhere = preferencesWhere
        this.preferencesWhereFrom = preferencesWhereFrom
        repository.savePreferences(preferencesWhereFrom to preferencesWhere)
    }

    private fun mapOfferToOfferUI(offer: Offer): OfferUi =
        OfferUi(
            idImage = when (offer.id) {
                1 -> resourceProvider.getFotoId(FotoEnum.FIRST_FOTO)
                2 -> resourceProvider.getFotoId(FotoEnum.SECOND_FOTO)
                3 -> resourceProvider.getFotoId(FotoEnum.THIRD_FOTO)
                else -> resourceProvider.getFotoId(FotoEnum.DEFAULT_FOTO)
            },
            title = offer.title,
            price = offer.price.toStringForUI(),
            town = offer.town
        )
}