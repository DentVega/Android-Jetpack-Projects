package com.brianvega.startupweekend.fragments.sponsors

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.brianvega.startupweekend.models.Sponsor

class SponsorsViewModel : ViewModel() {

    private var repository: SponsorsRepository = SponsorsRepository()
    var sponsors: LiveData<MutableList<Sponsor>>? = null

    init {
        sponsors = getSponsorsFirebase()
    }

    private fun getSponsorsFirebase(): LiveData<MutableList<Sponsor>>? = repository.getSponsors()

}
