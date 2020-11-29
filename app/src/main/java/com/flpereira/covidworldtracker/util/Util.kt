package com.flpereira.covidworldtracker.util

import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.flpereira.covidworldtracker.R
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok"){
            snackbar.dismiss()
        }
    }.show()
}

fun ImageView.getUrlImage(url: String){
    Glide.with(context)
        .load(url)
        .into(this)
}