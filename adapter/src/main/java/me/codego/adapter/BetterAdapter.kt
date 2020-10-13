package me.codego.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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

    open fun append(data: T) {
        dataList.add(data)
        notifyItemInserted(dataList.size - 1)
    }

    open fun append(dataList: List<T>) {
        val start = this.dataList.size
        this.dataList.addAll(dataList)
        notifyItemRangeInserted(start, dataList.size)
    }

    open fun insert(index: Int, data: T) {
        dataList.add(index, data)
        notifyItemInserted(index)
    }

    open fun insert(index: Int, dataList: List<T>) {
        this.dataList.addAll(index, dataList)
        notifyItemRangeInserted(index, dataList.size)
    }

    open fun replace(index: Int, data: T) {
        dataList[index] = data
        notifyItemChanged(index)
    }

    fun exchange(oldPosition: Int, newPosition: Int) {
        if (dataList.size > Math.max(oldPosition, newPosition)) {
            dataList.add(newPosition, dataList.removeAt(oldPosition))
        }
        notifyItemMoved(oldPosition, newPosition)
    }

    open fun delete(index: Int) {
        dataList.removeAt(index)
        notifyItemRemoved(index)
        (itemCount - index).takeIf { it > 0 }?.let {
            notifyItemRangeChanged(index, it)
        }
    }

    open fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

    open fun getItem(index: Int) = dataList[index]

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }

    fun inflateChildView(parent: ViewGroup, layoutId: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }
}