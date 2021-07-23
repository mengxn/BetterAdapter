package me.codego.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 *
 * @author mengxn
 * @date 2021/7/22
 */
class SingleAdapter2<B : ViewDataBinding, D>(
    private val layoutId: Int,
    dataList: MutableList<D> = arrayListOf(),
    private val bind: (ViewHolder2<B, D>) -> Unit
) : BetterAdapter<D>(dataList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<D> {
        val binding = DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), layoutId, parent, false)
        return object : ViewHolder2<B, D>(binding.root) {
            override fun bind(data: D) {
                super.bind(data)
                this@SingleAdapter2.bind(this)
            }
        }
    }

}