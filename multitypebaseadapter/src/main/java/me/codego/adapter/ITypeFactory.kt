package me.codego.adapter

import android.view.View

/**
 * Created by mengxn on 2017/9/21.
 */
interface ITypeFactory<in T> {

    fun type(data: T): TypeData

    fun createViewHolder(view: View, type: Int): ViewHolder<T>

    data class TypeData(val type: Int, val layoutId: Int)
}