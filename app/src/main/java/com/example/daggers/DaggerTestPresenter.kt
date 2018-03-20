package com.example.daggers

import android.util.Log
import com.example.util.executorpack.ExecutorService
import java.util.concurrent.Executors

/**
 *
 * Created by Administrator on 2018/3/20 0020.
 */
class DaggerTestPresenter(private val mView: DaggerTestView) {
    private val TAG: String = "DaggerTestPresenter"
    private var syncRunnable :Runnable?=null
    private val executorService = Executors.newCachedThreadPool() as ExecutorService
    fun loadData() {
        executor()
    }

    fun onCancel() {
        syncRunnable=null
        executorService.shutdown()
    }

    private fun executor() {
        if(syncRunnable==null){
            syncRunnable  = Runnable {
                Log.e(TAG, Thread.currentThread().name)

            }
        }

        for (i in 0..99) {
            if(syncRunnable!=null) executorService.execute(syncRunnable)
            if(i==22){
                mView.updateUI()
            }
        }
    }

}