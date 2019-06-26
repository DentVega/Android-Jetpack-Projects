package com.brianvega.startupweekend.fragments.metrics

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brianvega.startupweekend.R

class MetricsFragment : Fragment() {

    companion object {
        fun newInstance() = MetricsFragment()
    }

    private lateinit var viewModel: MetricsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.metrics_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MetricsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
