package me.codego.adapter

import android.databinding.ViewDataBinding

class ViewHolderWrapper<D>(val layoutId: Int, var bind: (ViewHolder2<ViewDataBinding, D>) -> Unit)