package com.tchaikovsky.airtickets.presentation.air_tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tchaikovsky.airtickets.R
import com.tchaikovsky.airtickets.domain.entity.offers.Offer
import com.tchaikovsky.airtickets.domain.repository.AirTicketsRepository
import com.tchaikovsky.airtickets.utility.SingleEventLiveData
import com.tchaikovsky.airtickets.utility.toStringForUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


class AirTicketsViewModelImpl @Inject constructor(private val repository: AirTicketsRepository) :
    AirTicketsViewModel, ViewModel() {

    private val offersLiveData: MutableLiveData<List<OfferUi>> = MutableLiveData()

    private val singleEventLiveData: SingleEventLiveData<AirTicketsScreenState> =
        SingleEventLiveData()

    private val exceptionHandler =
        CoroutineExceptionHandler { _, error ->
            singleEventLiveData.value =
                AirTicketsScreenState.Error(error.message ?: DEFAULT_ERROR)
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
                    mapperOfferToOfferUI(it)
                }
            }
        }
    }

    override fun getOffersLiveData(): LiveData<List<OfferUi>> = offersLiveData

    override fun getSingleEventLiveData(): SingleEventLiveData<AirTicketsScreenState> =
        singleEventLiveData

    override fun onClickSearch(preferencesWhere: String, preferencesWhereFrom: String) {
        updatePreference(preferencesWhere, preferencesWhereFrom)
        singleEventLiveData.value =
            AirTicketsScreenState.OpenSearchScreenState(preferencesWhere, preferencesWhereFrom)
    }

    override fun onViewPause(preferencesWhere: String, preferencesWhereFrom: String) {
        updatePreference(preferencesWhere, preferencesWhereFrom)
    }

    private fun updatePreference(preferencesWhereFrom: String, preferencesWhere: String){
        this.preferencesWhere = preferencesWhere
        this.preferencesWhereFrom = preferencesWhereFrom
        repository.savePreferences(preferencesWhereFrom to preferencesWhere)
    }

    private fun mapperOfferToOfferUI(offer: Offer): OfferUi =
        OfferUi(
            idImage = when (offer.id) {
                1 -> R.drawable.first_foto
                2 -> R.drawable.second_foto
                3 -> R.drawable.third_foto
                else -> R.drawable.ic_launcher_foreground
            },
            title = offer.title,
            price = offer.price.toStringForUI(),
            town = offer.town
        )

    companion object {
        private const val DEFAULT_ERROR = " Default error"
    }
}