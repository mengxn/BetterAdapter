package me.codego.adapter

import android.view.View

/**
 * Created by mengxn on 2017/9/21.
 */
interface ITypeFactory<in T> {

    fun type(data: T): TypeData<T>

    data class TypeData<in D>(val layoutId: Int, val bind: (View, D, Int) -> Unit)
}