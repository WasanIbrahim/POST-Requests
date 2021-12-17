package com.example.postrequests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postrequests.databinding.ItemRowBinding

class RecyclerViewAdapter(var APIList: ArrayList<TestItem>): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewAdapterView>(){

     class RecyclerViewAdapterView(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterView {
          return RecyclerViewAdapterView(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent,false))
     }

     override fun onBindViewHolder(holder: RecyclerViewAdapterView, position: Int) {
          var Name = APIList[position]
          var Location = APIList[position]

          holder.binding.apply {
               rvName.text = Name.name //name is the one in the TestItem
               rvLocation.text = Location.location

          }
     }

     override fun getItemCount(): Int {
         return APIList.size
     }

}