package com.example.myapplication.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceFragmentCompat
import com.example.myapplication.R
import com.example.myapplication.viewModel.ViewModel

class SettingsFragment : PreferenceFragmentCompat() {
    private lateinit var userViewModel: ViewModel
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        userViewModel = ViewModelProvider(this)[ViewModel::class.java]
        userViewModel.getUserList().observe(this, Observer {

        })
        super.onCreate(savedInstanceState)
    }

}