package me.codego.adapter

import android.support.v7.widget.RecyclerView

/**
 * Adapter 增强类
 * Created by mengxn on 2018/1/16.
 */
abstract class BetterAdapter<T>(private val dataList: MutableList<T> = arrayListOf()) : RecyclerView.Adapter<ViewHolder<T>>() {

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.adapterPosition.let {
            holder.bind(getItem(it), it)
        }
    }

    fun setData(dataList: List<T>) {
        this.dataList.apply {
            clear()
            addAll(dataList)
        }.also { notifyDataSetChanged() }
    }

    fun getData() = dataList

    open fun append(data: T) {
        dataList.add(data)
        notifyItemInserted(dataList.size - 1)
    }

    fun append(dataList: List<T>) {
        val start = this.dataList.size
        this.dataList.addAll(dataList)
        notifyItemRangeInserted(start, dataList.size)
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
        (itemCount - index).takeIf { it > 0 }?.let {
            notifyItemRangeChanged(index, it)
        }
    }

    fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

    open fun getItem(index: Int) = dataList[index]
}