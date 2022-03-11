package com.app.sinnerschradertest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.sinnerschradertest.databinding.RowItemFibonacciBinding


class FibonacciItemAdapter(private val itemList: MutableList<String>) : RecyclerView.Adapter<FibonacciItemAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewBinding: RowItemFibonacciBinding? = DataBindingUtil.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.row_item_fibonacci, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding?.txtResults?.text = itemList[position]
    }
}