package com.brianvega.startupweekend.fragments.sponsors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.brianvega.startupweekend.R
import com.brianvega.startupweekend.fragments.BaseFragment
import com.brianvega.startupweekend.models.Sponsor
import kotlinx.android.synthetic.main.sponsors_fragment.*

class SponsorsFragment : BaseFragment() {

    private var sponsorRecyclerView: SponsorsRecyclerView? = null

    companion object {
        fun newInstance() = SponsorsFragment()
    }

    private lateinit var viewModel: SponsorsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sponsors_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showProgressBar(progress_sponsors, activity!!)
        viewModel = ViewModelProviders.of(this).get(SponsorsViewModel::class.java)
        viewModel.sponsors!!.observe(this, Observer { sponsors ->
            if (!sponsors.isNullOrEmpty()) {
                loadRecyclerViewSponsor(sponsors)
            }
        })
    }

    private fun loadRecyclerViewSponsor(sponsors: List<Sponsor>) {
        sponsorRecyclerView = SponsorsRecyclerView(activity!!.applicationContext)
        rcv_sponsors.adapter = sponsorRecyclerView
        rcv_sponsors.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        sponsorRecyclerView!!.setSponsors(sponsors)
        hideProgressBar(progress_sponsors, activity!!)
    }

}
