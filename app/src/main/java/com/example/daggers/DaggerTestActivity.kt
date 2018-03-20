package com.example.daggers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.R
import javax.inject.Inject




/**
 *
 * Created by Administrator on 2018/3/20 0020.
 */
class DaggerTestActivity : AppCompatActivity(), DaggerTestView {
    override fun updateUI() {
        Log.e("log","我是输出")
    }
    @Inject
    lateinit var mainPresenter : DaggerTestPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dagger_act)

        DaggerTestComponent.builder().daggerTestModule(DaggerTestModule(this)).build().inject(this)

        mainPresenter.loadData()

    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter!!.onCancel()
    }

}