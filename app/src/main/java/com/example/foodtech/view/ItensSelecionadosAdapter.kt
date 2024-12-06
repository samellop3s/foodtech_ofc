package com.example.foodtech.adapter // ajuste o pacote de acordo com sua estrutura

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtech.R // ajuste o import se necessário
import com.example.foodtech.view.Lanche

class ItensSelecionadosAdapter(private val itens: List<Lanche>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lanche, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val lanche = itens[position]
        holder.bind(lanche)
    }

    override fun getItemCount(): Int = itens.size
}

// ViewHolder para os itens
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.itemTitle)
    private val price: TextView = itemView.findViewById(R.id.itemPrice)

    fun bind(lanche: Lanche) {
        title.text = lanche.nome
        price.text = "R$ ${lanche.preco}"
    }
}
