package me.codego.adapter

import android.support.v7.widget.RecyclerView

/**
 * Adapter 增强类
 * Created by mengxn on 2018/1/16.
 */
abstract class BetterAdapter<T>(private val dataList: MutableList<T> = arrayListOf()) : RecyclerView.Adapter<ViewHolder<T>>() {

    override fun getItemCount(): Int = dataList.size

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