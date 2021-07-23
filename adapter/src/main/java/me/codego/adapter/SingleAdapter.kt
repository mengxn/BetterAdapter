package me.codego.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * 单类型，基础 RecyclerView Adapter
 * Created by mengxn on 2017/9/20.
 */

open class SingleAdapter<T>(
    private val layoutId: Int,
    dataList: MutableList<T> = arrayListOf(),
    val bind: (data: ViewHolder<T>) -> Unit
) : BetterAdapter<T>(dataList) {

    constructor(layoutId: Int, bind: (data: ViewHolder<T>) -> Unit) : this(layoutId, arrayListOf(), bind)

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return object : ViewHolder<T>(view) {
            override fun bind(data: T) {
                super.bind(data)
                this@SingleAdapter.bind(this)
            }
        }
    }

}