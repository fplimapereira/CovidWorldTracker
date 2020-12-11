package com.flpereira.covidworldtracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.flpereira.covidworldtracker.R
import com.flpereira.covidworldtracker.model.CountryListItem
import com.flpereira.covidworldtracker.util.getUrlImage
import kotlinx.android.synthetic.main.country_list_item.view.*

class CountriesListAdapter(private val onClickListener: OnClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<CountryListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CountryViewHolder){
            holder.bind(items[position], onClickListener)
        }
    }

    fun submitList(countryList: List<CountryListItem>){
        items = countryList
        notifyDataSetChanged()
    }

    class CountryViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgFlag = itemView.imgFlag
        val countryName = itemView.countryName

        fun bind(item: CountryListItem, onClickListener: OnClickListener){
            countryName.text = item.name
            imgFlag.getUrlImage(item.flagUrl)
            imgFlag.transitionName = item.name
            itemView.setOnClickListener {
                onClickListener.onClick(item.name)
            }

        }
    }

    class OnClickListener(val clickListener: (String) -> Unit) {
        fun onClick(countryName: String) = clickListener(countryName)
    }
}