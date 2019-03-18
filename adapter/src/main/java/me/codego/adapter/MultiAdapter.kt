package me.codego.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * 多类型，基础 RecyclerView Adapter
 * Created by mengxn on 2017/9/21.
 */
open class MultiAdapter<T>(private val typeFactory: ITypeFactory<T>, dataList: MutableList<T> = arrayListOf()) : BetterAdapter<T>(dataList) {

    private val typeDataArray = SparseArray<ITypeFactory.TypeData<T>>()

    override fun getItemViewType(position: Int): Int {
        val typeData = typeFactory.type(getItem(position))
        typeDataArray.put(typeData.layoutId, typeData)
        return typeData.layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(typeDataArray[viewType].layoutId, parent, false)
        return ViewHolder<T>(view)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        val adapterPosition = holder.adapterPosition
        holder.bind(typeDataArray[getItemViewType(adapterPosition)], getItem(adapterPosition))
    }
}


