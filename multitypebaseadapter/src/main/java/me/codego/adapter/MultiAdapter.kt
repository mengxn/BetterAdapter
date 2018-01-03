package me.codego.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 多类型，基础 RecyclerView Adapter
 * 根据 Model 确定 ViewType
 * Created by mengxn on 2017/9/21.
 */
open class MultiAdapter<T>(private val typeFactory: ITypeFactory<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<T>()
    private val typeDataArray = SparseArray<ITypeFactory.TypeData>()

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        val typeData = typeFactory.type(dataList[position])
        typeDataArray.put(typeData.type, typeData)
        return typeData.type
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        parent?.let {
            val view = LayoutInflater.from(parent.context).inflate(typeDataArray[viewType].layoutId, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }
        throw Exception("parent is null")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder?.run {
            bindAction?.invoke(itemView, dataList[adapterPosition])
            bindIndexedAction?.invoke(itemView, dataList[adapterPosition], adapterPosition)
        }
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

    private var bindAction: ((View, T) -> Unit)? = null

    fun forEach(action: (View, T) -> Unit): MultiAdapter<T> {
        bindAction = action
        return this
    }

    private var bindIndexedAction: ((View, T, Int) -> Unit)? = null

    fun forEachIndexed(action: (View, T, Int) -> Unit): MultiAdapter<T> {
        bindIndexedAction = action
        return this
    }
}


