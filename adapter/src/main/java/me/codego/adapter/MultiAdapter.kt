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
class MultiAdapter<D>(val layoutBlock: (D) -> ViewHolderWrapper<D>) : BetterAdapter<D>() {

    private val mWrapperMap by lazy { mutableMapOf<Int, ViewHolderWrapper<D>>() }

    override fun getItemViewType(position: Int): Int {
        val wrapper = layoutBlock(getItem(position))
        mWrapperMap[wrapper.viewType] = wrapper
        return wrapper.viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<D> {
        val wrapper = mWrapperMap[viewType] ?: throw IllegalArgumentException("viewType $viewType is undefined")

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

