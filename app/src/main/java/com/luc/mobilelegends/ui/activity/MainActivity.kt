package com.luc.mobilelegends.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.luc.mobilelegends.R
import com.luc.mobilelegends.databinding.ActivityMainBinding
import com.luc.mobilelegends.ui.vm.MainVm
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val vm: MainVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityMainBinding.inflate(layoutInflater)
        bind.vm = vm
        setContentView(bind.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.refresh, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        vm.refresh()

        return super.onOptionsItemSelected(item)
    }
}
