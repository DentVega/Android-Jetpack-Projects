package com.brianvega.startupweekend.activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.brianvega.startupweekend.Constants
import com.brianvega.startupweekend.R


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), Constants {

    private var progressDialog: ProgressDialog? = null

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
    }

    private fun loadProgress() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage(resources.getString(R.string.loading))
        progressDialog!!.setCancelable(false)
    }

    protected fun showToast(resIdMessage: Int) {
        showToast(resources.getString(resIdMessage))
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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

    fun showProgressBar(progressBar: ProgressBar) {
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar(progressBar: ProgressBar) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBar.visibility = View.GONE
    }

    fun loadFragment(fragment: Fragment, @IdRes containerView: Int) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(containerView, fragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.addToBackStack(null)
        ft.commit()
    }

    open fun goBack() {
        super.onBackPressed()
    }

    @SuppressLint("InflateParams")
    fun showCustomToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        val view = layoutInflater.inflate(R.layout.toast_view, null)
        (view.findViewById<View>(R.id.txt_message) as TextView).text = message
        toast.view = view
        toast.show()
    }

}
