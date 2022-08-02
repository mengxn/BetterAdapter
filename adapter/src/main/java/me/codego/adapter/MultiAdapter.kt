package me.codego.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * 多类型适配器
 *
 * @author mengxn
 * @date 2022/2/28
 */
open class MultiAdapter<D>(block: ViewHolderConfig<D>.() -> Unit) : BetterAdapter<D>() {

    private val mConfig = ViewHolderConfig<D>().apply(block)

    override fun getItemViewType(position: Int): Int {
        return mConfig.getViewType(getItem(position)) ?: super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<D> {
        val wrapper = mConfig.getViewHolderWrapper(viewType) ?: throw IllegalArgumentException("viewType $viewType is undefined")
        val layoutId = wrapper.layoutId
        val bindBlock = wrapper.bind
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), layoutId, parent, false)
        return object : ViewHolder2<ViewDataBinding, D>(binding.root) {
            override fun bind(data: D) {
                super.bind(data)
                bindBlock(this)
            }
        }
    }
}

