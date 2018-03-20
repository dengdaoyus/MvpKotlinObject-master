package com.example.daggers

import dagger.Module
import dagger.Provides


/**
 *
 * Created by Administrator on 2018/3/20 0020.
 */

@Module
class DaggerTestModule internal constructor(private val mView: DaggerTestView) {

    @Provides
    fun provideMainView(): DaggerTestView =mView

}