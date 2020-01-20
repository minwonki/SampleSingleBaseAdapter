package com.wk.min.samplesinglebaseadapter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Consumer
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerView {

    class Adapter<ITEM : Any, B : ViewDataBinding>(
        private val layoutResId: Int,
        private val variableId: Int,
        private val callback: Consumer<ITEM>
    ) : RecyclerView.Adapter<ViewHolder<B>>() {

        private var items = ArrayList<ITEM>()

        fun bindList(items: List<ITEM>) {
            this.items = items as ArrayList<ITEM>
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
            val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
            view.setOnClickListener { callback.accept(items[it.tag as Int]) }
            return ViewHolder(variableId, view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
            holder.onBindViewHolder(items[position], position)
        }
    }

    class ViewHolder<B : ViewDataBinding>(
        private val variableId: Int,
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = DataBindingUtil.bind<B>(itemView)

        fun onBindViewHolder(item: Any, position: Int) {
            itemView.tag = position
            binding?.setVariable(variableId, item)
        }
    }
}