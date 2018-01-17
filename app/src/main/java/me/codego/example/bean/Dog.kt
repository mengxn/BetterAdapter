package me.codego.example.bean

/**
 * Created by mengxn on 2018/1/16.
 */
data class Dog(val id: Int) : Animal {

    override fun getName(): String = "Dog $id"

}