package com.appa.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appa.databinding.CardMessgeBinding
import com.appa.model.entities.Message

class ListMessageAdapter :
    ListAdapter<Message,ListMessageAdapter.ListViewHolder>(ListMessageDiffCallback()){
    private var list: List<Message> = mutableListOf()

        class ListViewHolder(private val binding: CardMessgeBinding, view: View) : RecyclerView.ViewHolder(view){
            fun bind(message: Message){
                binding.titleTxt.text = message.message
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = CardMessgeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding, binding.root)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun submitList(submitList: List<Message>?) {
        submitList?.let {
            list = it
        }
        super.submitList(list)
    }
}

class ListMessageDiffCallback : DiffUtil.ItemCallback<Message>(){
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}