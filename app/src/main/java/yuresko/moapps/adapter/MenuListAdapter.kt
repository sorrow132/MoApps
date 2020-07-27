package yuresko.moapps.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import yuresko.moapps.mainview.model.ItemModel

class MenuListAdapter (private val context: Context) : ListAdapter<ItemModel, RecyclerView.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CustomViewHolder(parent, context)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CustomViewHolder) {
            holder.bind(getItem(position) as ItemModel)
        }
    }
}