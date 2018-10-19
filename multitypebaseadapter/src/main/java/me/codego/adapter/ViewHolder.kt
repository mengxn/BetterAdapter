package me.codego.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by mengxn on 2017/9/21.
 */
open class ViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    var data: T? = null

    fun bind(typeData: ITypeFactory.TypeData<T>, data: T) {
        this.data = data
        typeData.bind(this)
    }

}





