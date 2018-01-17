package me.codego.adapter

import android.view.View

/**
 * 单类型，基础 RecyclerView Adapter
 * Created by mengxn on 2017/9/20.
 */

open class SingleAdapter<T>(private val layoutId: Int, dataList: MutableList<T>, val bind: (View, T, Int) -> Unit) : MultiAdapter<T>(object : ITypeFactory<T> {

    override fun type(data: T): ITypeFactory.TypeData<T> = ITypeFactory.TypeData(layoutId, bind)

}, dataList) {
    constructor(layoutId: Int, bind: (View, T, Int) -> Unit) : this(layoutId, arrayListOf(), bind)
}