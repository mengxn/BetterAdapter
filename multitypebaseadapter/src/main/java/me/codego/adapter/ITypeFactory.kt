package me.codego.adapter

/**
 * Created by mengxn on 2017/9/21.
 */
interface ITypeFactory<in T> {

    fun type(data: T): TypeData

    data class TypeData(val type: Int, val layoutId: Int)
}