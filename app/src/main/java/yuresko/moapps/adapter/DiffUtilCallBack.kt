package yuresko.moapps.adapter

import androidx.recyclerview.widget.DiffUtil
import yuresko.moapps.mainview.model.ItemModel

class DiffUtilCallback : DiffUtil.ItemCallback<ItemModel>() {
    override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem is ItemModel && newItem is ItemModel
    }

    override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem == newItem
    }
}