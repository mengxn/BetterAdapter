package me.codego.adapter

/**
 * Created by mengxn on 2017/9/21.
 */
interface ITypeFactory<T> {

    fun type(data: T): TypeData<T>

    data class TypeData<D>(val layoutId: Int, val bind: (holder: ViewHolder<D>) -> Unit)


}