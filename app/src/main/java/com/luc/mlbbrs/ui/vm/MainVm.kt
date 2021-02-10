package com.luc.mlbbrs.ui.vm

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.luc.mlbbrs.core.BaseViewModel
import com.luc.mlbbrs.repo.RankRepo
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainVm(context: Application) : BaseViewModel(context) {
    var remainingDay = ObservableField(0)
    var startRankDate = ObservableField(Calendar.getInstance())
    var resetDate = ObservableField(Calendar.getInstance())
    var startPower = ObservableField(0)
    var maxStartPower = ObservableField(0)
    var currentRank = ObservableField("")
    var expectedRank = ObservableField("")
    var resetDateText = ObservableField("")
    var startDateText = ObservableField("")
    val DEFAULT_DATE_OUTPUT = "d MMMM yyyy"
    private val rankRepo = RankRepo()

    init {
        maxStartPower.set(rankRepo.maxPower)

        val startRankDate = rankRepo.getStartRankDate()
        startPower.set(rankRepo.getStartPower())


        this.startRankDate.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                this@MainVm.startRankDate.get()?.let { updateResetDate(it) }
                val format1 = SimpleDateFormat(DEFAULT_DATE_OUTPUT)
                startDateText.set(format1.format(this@MainVm.startRankDate.get()?.time))
            }
        })

        resetDate.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val format1 = SimpleDateFormat(DEFAULT_DATE_OUTPUT)
                resetDateText.set(format1.format(this@MainVm.resetDate.get()?.time))
            }
        })

        startPower.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                calculateTimeEstimation()
                startPower.get()?.let { rankRepo.saveStartPower(it) }
            }
        })
        val cal = Calendar.getInstance()
        cal.timeInMillis = startRankDate
        this.startRankDate.set(cal)
    }

    fun calculateTimeEstimation() {
        val current = Calendar.getInstance()
        val firstTotalValue = startPower.get() ?: 0
        val targetTotalValue = rankRepo.maxPower
        val diffCurrentTime = current.timeInMillis - (startRankDate.get()?.timeInMillis ?: 0L) + 1L
        val diffResetTime =
            (resetDate.get()?.timeInMillis ?: 0L) - (startRankDate.get()?.timeInMillis ?: 0L)
        if (diffResetTime == 0L) return
        val addedValue = diffCurrentTime * (targetTotalValue - firstTotalValue) / diffResetTime
        val expectedAddedValue = firstTotalValue + addedValue
        currentRank.set(rankRepo.getRank(firstTotalValue))
        expectedRank.set(rankRepo.getRank(expectedAddedValue.toInt()))
    }

    fun setStartRankDate(year: Int, month: Int, dayOfMonth: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        startRankDate.set(cal)
        rankRepo.saveStartRankDate(cal.timeInMillis)
    }

    private fun updateResetDate(startRankDate: Calendar) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, startRankDate.get(Calendar.YEAR))
        cal.set(Calendar.MONTH, startRankDate.get(Calendar.MONTH))
        cal.set(Calendar.DAY_OF_MONTH, startRankDate.get(Calendar.DAY_OF_MONTH))
        cal.add(Calendar.DAY_OF_YEAR, rankRepo.resetDay)
        resetDate.set(cal)
        val diff: Long = cal.timeInMillis - Calendar.getInstance().timeInMillis
        remainingDay.set(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt())
        calculateTimeEstimation()
    }
}