package com.example.daggers


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.R
import javax.inject.Inject


@SuppressLint("Registered")
/**
 *
 * Created by Administrator on 2018/3/20 0020.
 */
class DaggerTestActivity : AppCompatActivity(), DaggerTestView {
    override fun updateUI() {
        Log.e("log","我是输出")
    }

    @Inject
    private var mainPresenter: DaggerTestPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dagger_act)
        DaggerTestModule_ProvideMainViewFactory.create(DaggerTestModule(this))
//        DaggerTestComponent
//                .builder()
//                .daggerTestModule()
//                .build().inject(this)
        mainPresenter!!.loadData()
    }

}