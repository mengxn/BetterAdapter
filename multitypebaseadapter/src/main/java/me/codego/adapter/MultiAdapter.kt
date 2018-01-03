package me.codego.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * 多类型，基础 RecyclerView Adapter
 * Created by mengxn on 2017/9/21.
 */
open class MultiAdapter<T>(private val dataList: MutableList<T>, private val typeFactory: ITypeFactory<T>) : RecyclerView.Adapter<ViewHolder<T>>() {

    constructor(typeFactory: ITypeFactory<T>) : this(arrayListOf(), typeFactory)

    private val typeDataArray = SparseArray<ITypeFactory.TypeData>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder<T> {
        parent?.let {
            val view = LayoutInflater.from(parent.context).inflate(typeDataArray[viewType].layoutId, parent, false)
            return typeFactory.createViewHolder(view, viewType)
        }
        throw Exception("parent is null")
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder<T>?, position: Int) {
        holder?.let { holder.bind(dataList[holder.adapterPosition]) }
    }

    override fun getItemViewType(position: Int): Int {
        val typeData = typeFactory.type(dataList[position])
        typeDataArray.put(typeData.type, typeData)
        return typeData.type
    }

    fun setData(dataList: List<T>) {
        this.dataList.apply {
            clear()
            addAll(dataList)
        }.also { notifyDataSetChanged() }
    }

    fun getData() = dataList

    fun append(data: T) {
        insert(itemCount, data)
    }

    fun append(dataList: List<T>) {
        insert(itemCount, dataList)
    }

    fun insert(index: Int, data: T) {
        dataList.add(index, data)
        notifyItemInserted(index)
    }

    fun insert(index: Int, dataList: List<T>) {
        this.dataList.addAll(index, dataList)
        notifyItemRangeInserted(index, dataList.size)
    }

    fun replace(index: Int, data: T) {
        dataList[index] = data
        notifyItemChanged(index)
    }

    fun delete(index: Int) {
        dataList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun getItem(index: Int) = dataList[index]
}


