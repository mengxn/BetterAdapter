package me.codego.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by mengxn on 2017/9/21.
 */
open class ViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    var data: T? = null

    private val mViews: SparseArray<in View> by lazy { SparseArray<View>() }

    open fun bind(data: T) {
        this.data = data
    }

    fun setText(id: Int, text: CharSequence?) {
        getView<TextView>(id)?.text = text
    }

    fun setImageResource(id: Int, resId: Int) {
        getView<ImageView>(id)?.setImageResource(resId)
    }

    fun setBackground(id: Int, resId: Int) {
        getView<View>(id)?.setBackgroundResource(resId)
    }

    fun setBackgroundColor(id: Int, color: Int) {
        getView<View>(id)?.setBackgroundColor(color)
    }

    fun setVisibility(id: Int, visibility: Int) {
        getView<View>(id)?.visibility = visibility
    }

    fun setOnClickListener(id: Int, listener: View.OnClickListener) {
        getView<View>(id)?.setOnClickListener(listener)
    }

    fun <R: View> getView(id: Int): R? {

        fun findView(id: Int): R? {
            val view = itemView.findViewById<R>(id)
            if (view != null) {
                mViews.put(id, view)
            }
            return view
        }

        return mViews.get(id) as? R ?: findView(id)
    }
}





