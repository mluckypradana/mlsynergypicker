package com.luc.mlbbrs

import android.app.Application
import com.luc.mlbbrs.ui.vm.MainVm
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainVmTest {
    private var vm = MainVm(Application())

    @Test
    fun setRemainingDay() {
        vm.remainingDay.set(90)
        var resetDate = vm.resetDate.get()?.timeInMillis ?: 0L
        var currentDate = Calendar.getInstance().timeInMillis
        assertTrue(resetDate > currentDate)
    }

    @Test
    fun calculateTimeEstimation() {
        vm.remainingDay.set(90)
        val lastRankDate = Calendar.getInstance()
        lastRankDate.add(Calendar.DAY_OF_YEAR, -45)
        vm.startRankDate.set(lastRankDate)
        vm.calculateTimeEstimation()
    }
}
