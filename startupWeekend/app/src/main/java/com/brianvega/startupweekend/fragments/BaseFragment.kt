package com.brianvega.startupweekend.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.brianvega.startupweekend.R


open class BaseFragment : Fragment() {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun loadProgress() {
        progressDialog = ProgressDialog(context)
        progressDialog!!.setMessage(resources.getString(R.string.loading))
        progressDialog!!.setCancelable(false)
    }

    protected fun loadProgressCustom(message: String) {
        progressDialog = ProgressDialog(context)
        progressDialog!!.setMessage(message)
        progressDialog!!.setCancelable(false)
    }

    protected fun showToast(resIdMessage: Int) {
        showToast(resources.getString(resIdMessage))
    }

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showLoadingDialog() {
        if (progressDialog != null) {
            progressDialog!!.show()
        } else {
            loadProgress()
            progressDialog!!.show()
        }
    }

    protected fun closeLoadingDialog() {
        if (progressDialog == null)
            return

        progressDialog!!.dismiss()
    }

    fun showProgressBar(progressBar: ProgressBar, activity: Activity) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar(progressBar: ProgressBar, activity: Activity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBar.visibility = View.GONE
    }

    @SuppressLint("InflateParams")
    fun showCustomToast(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        val view = layoutInflater.inflate(R.layout.toast_view, null)
        (view.findViewById<View>(R.id.txt_message) as TextView).text = message
        toast.view = view
        toast.show()
    }
}
