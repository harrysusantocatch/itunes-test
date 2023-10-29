package com.example.myapplication.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel>() : AppCompatActivity() {

    protected lateinit var mViewDataBinding: VB
    protected lateinit var mViewModel: VM

    abstract fun getLayout(): Int

    abstract fun getViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        mViewModel = getViewModel()
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayout())
        mViewDataBinding.executePendingBindings()
        mViewDataBinding.lifecycleOwner = this
    }
}