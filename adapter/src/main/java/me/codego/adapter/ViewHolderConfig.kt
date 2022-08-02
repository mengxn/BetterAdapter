package me.codego.adapter

import android.databinding.ViewDataBinding

/**
 *
 * @author mengxn
 * @date 2022/8/2
 */
class ViewHolderConfig<D> {

    private var mViewType: ((D) -> Int)? = null
    private val mWrapperMap by lazy { mutableMapOf<Int, ViewHolderWrapper<D>>() }

    fun makeViewType(block: (data: D) -> Int) {
        mViewType = block
    }

    fun getViewType(data: D): Int? {
        return mViewType?.invoke(data)
    }

    fun addViewHolder(viewType: Int, layoutId: Int, bind: (ViewHolder2<ViewDataBinding, D>) -> Unit) {
        mWrapperMap[viewType] = ViewHolderWrapper<D>(layoutId, bind)
    }

    fun getViewHolderWrapper(viewType: Int): ViewHolderWrapper<D>? {
        return mWrapperMap[viewType]
    }
}