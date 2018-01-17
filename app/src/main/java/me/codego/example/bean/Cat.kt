package me.codego.example.bean

/**
 * Created by mengxn on 2018/1/16.
 */
data class Cat(val id: Int) : Animal {

    override fun getName() = "Cat $id"

}