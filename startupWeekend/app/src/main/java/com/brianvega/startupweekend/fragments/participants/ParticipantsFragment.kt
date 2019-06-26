package com.brianvega.startupweekend.fragments.participants

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brianvega.startupweekend.R

class ParticipantsFragment : Fragment() {

    companion object {
        fun newInstance() = ParticipantsFragment()
    }

    private lateinit var viewModel: ParticipantsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.participants_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ParticipantsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
