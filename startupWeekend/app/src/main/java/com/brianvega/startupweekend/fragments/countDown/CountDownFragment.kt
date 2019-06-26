package com.brianvega.startupweekend.fragments.countDown

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brianvega.startupweekend.R

class CountDownFragment : Fragment() {

    companion object {
        fun newInstance() = CountDownFragment()
    }

    private lateinit var viewModel: CountDownViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.count_down_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountDownViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
