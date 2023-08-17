package com.siddroid.begalapp.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.siddroid.begal.domain.model.Data
import com.siddroid.begalapp.databinding.ItemDogBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class DogAdapter: Adapter<DogViewHolder>() {

    private val list = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DogViewHolder(ItemDogBinding.inflate(inflater))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Data>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}

class DogViewHolder(private val view: ItemDogBinding): ViewHolder(view.root) {

    fun bind(item: Data) {
        Picasso.get().load(item.url).into(view.imvDog, object: Callback {
            override fun onSuccess() {
                Log.d("CHECK", "Image Loaded Sucessfully")
            }

            override fun onError(e: Exception?) {
                Log.d("CHECK", "Image Loading Failed")
            }

        })
    }

}