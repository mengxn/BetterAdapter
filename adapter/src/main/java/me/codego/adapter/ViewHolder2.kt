package me.codego.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View

/**
 *
 * @author mengxn
 * @date 2021/7/22
 */
open class ViewHolder2<B: ViewDataBinding, D>(view: View): ViewHolder<D>(view) {

    fun getBinding(): B {
        return DataBindingUtil.getBinding<B>(itemView)!!
    }

}