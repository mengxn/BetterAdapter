package me.codego.adapter

/**
 * 单类型，基础 RecyclerView Adapter
 * Created by mengxn on 2017/9/20.
 */

open class SingleAdapter<T>(private val layoutId: Int, dataList: MutableList<T> = arrayListOf(), val bind: (holder: ViewHolder<T>) -> Unit) : MultiAdapter<T>(object : ITypeFactory<T> {

    override fun type(data: T): ITypeFactory.TypeData<T> = ITypeFactory.TypeData(layoutId, bind)

}, dataList)