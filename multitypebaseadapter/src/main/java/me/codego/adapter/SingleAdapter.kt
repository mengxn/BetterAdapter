package me.codego.adapter

import android.view.View

/**
 * 单类型，基础 RecyclerView Adapter
 * Created by mengxn on 2017/9/20.
 */

open class SingleAdapter<T>(private val layoutId: Int, dataList: MutableList<T>, val bind: (View, T) -> Unit) : MultiAdapter<T>(dataList, object : ITypeFactory<T> {

    override fun type(data: T): ITypeFactory.TypeData = ITypeFactory.TypeData(0, layoutId)

    override fun createViewHolder(view: View, type: Int): ViewHolder<T> = ViewHolder(view, bind)

}){
    constructor(layoutId: Int, bind: (View, T) -> Unit): this(layoutId, arrayListOf(), bind)
}