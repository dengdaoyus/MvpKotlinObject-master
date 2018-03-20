package com.example.daggers

import android.util.Log
import java.util.concurrent.ExecutorService

import java.util.concurrent.Executors

import javax.inject.Inject

/**
 *
 * Created by Administrator on 2018/3/20 0020.
 */
class DaggerTestPresenter @Inject internal constructor(val mView: DaggerTestView) {
    val TAG: String = "DaggerTestPresenter"
    var syncRunnable: Runnable? = null
    private val executorService: ExecutorService=Executors.newCachedThreadPool()

    fun loadData() {
        executor()
    }

    fun onCancel() {
        syncRunnable = null
        executorService.shutdown()
    }

     private fun executor() {
        if (syncRunnable == null) {
            syncRunnable = Runnable {
                Log.e(TAG, Thread.currentThread().name)

            }
        }

        for (i in 0..99) {
            if (syncRunnable != null) executorService.execute(syncRunnable)
            if (i == 22) {
                mView.updateUI()
            }
        }
    }

}