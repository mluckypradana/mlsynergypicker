package com.luc.mlbbrs.ui.activity

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.luc.mlbbrs.databinding.ActivityMainBinding
import com.luc.mlbbrs.ui.vm.MainVm
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    private val vm: MainVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val bind = ActivityMainBinding.inflate(layoutInflater)
        bind.vm = vm
        setContentView(bind.root)
        super.onCreate(savedInstanceState)
    }

    fun pickDate(view: View) {
        val cldr: Calendar = vm.startRankDate.get() ?: Calendar.getInstance()
        val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
        val month: Int = cldr.get(Calendar.MONTH)
        val year: Int = cldr.get(Calendar.YEAR)
        // date picker dialog
        var picker = DatePickerDialog(
            this@MainActivity,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                vm.setStartRankDate(year, monthOfYear, dayOfMonth)
            },
            year,
            month,
            day
        )
        picker.show()
    }
}
