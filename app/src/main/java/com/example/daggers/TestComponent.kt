package com.example.daggers

import dagger.Component


/**
 *
 * Created by Administrator on 2018/3/20 0020.
 */

@Component(modules = arrayOf(DaggerTestModule::class))
interface TestComponent {
    fun inject(activity: DaggerTestActivity)
}