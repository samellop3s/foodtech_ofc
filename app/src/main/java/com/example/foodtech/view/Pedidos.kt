package com.example.foodtech.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.foodtech.R


// Classe de dados para representar um lanche
data class Lanche(
    val nome: String,
    val descricao: String,
    val imagem: Int,
    val preco: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeString(descricao)
        parcel.writeInt(imagem)
        parcel.writeDouble(preco)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lanche> {
        override fun createFromParcel(parcel: Parcel): Lanche {
            return Lanche(parcel)
        }

        override fun newArray(size: Int): Array<Lanche?> {
            return arrayOfNulls(size)
        }
    }
}

class Pedidos : AppCompatActivity() {
    private val itensSelecionados = mutableListOf<Lanche>() // Lista de itens selecionados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        var totalPayment: Double = 0.0

        val btnGoToPayment = findViewById<Button>(R.id.btnGoPayment)

        btnGoToPayment.setOnClickListener {
            val intent = Intent(this, PaymentArea::class.java)
            intent.putExtra("TOTAL_PAYMENT", totalPayment) // Modificado para "valor_total"
            intent.putParcelableArrayListExtra("itens_selecionados", ArrayList(itensSelecionados))
            startActivity(intent)
        }

        window.statusBarColor = ContextCompat.getColor(this, R.color.cofee)


        // Referência ao LinearLayout onde os tickets (cards) serão adicionados
        val linearLayoutTickets = findViewById<LinearLayout>(R.id.linearLayoutTickets)

        // Lista de lanches (nome, descrição, imagem e preço)
        val lanches = listOf(
            Lanche("X-Burguer", "Hambúrguer, queijo, alface, tomate, e molho especial.", R.drawable.x_burguer, 25.00),
            Lanche("X-Bacon", "Hambúrguer, bacon, queijo, alface e maionese.", R.drawable.x_bacon, 28.00),
            Lanche("X-Frango", "Filé de frango grelhado, queijo, alface e tomate.", R.drawable.x_frango, 25.00),
            Lanche("X-Coração", "Coração de galinha, queijo, alface e molho especial.", R.drawable.x_coracao, 25.00),
            Lanche("Batata", "Batata frita crocante.", R.drawable.batata, 25.00),
            Lanche("Del-vale", "Suco de uva Del vale.", R.drawable.delvale, 9.00),
            Lanche("Água", "Garrafa de Água.", R.drawable.agua, 5.00),
            Lanche("Calabresa", "Calabresa frita.", R.drawable.calabresa, 25.00),
            Lanche("Cebola frita", "Cebola frita.", R.drawable.cebolafrita, 25.00),
            Lanche("Coca-Cola", "Coca-Cola.", R.drawable.cocacola, 8.00),
            Lanche("Fanta Laranja", "Refrigerante de laranja.", R.drawable.fantalaranja, 8.00),
            Lanche("Fanta Uva", "Refrigerante de uva.", R.drawable.fantauva, 8.00),
            Lanche("Guarana", "Refrigerante de guarana.", R.drawable.guarana, 8.00),
            Lanche("H2o", "H2o.", R.drawable.h2o, 9.00),
            Lanche("Itubaina", "Refrigerante tubaina.", R.drawable.itubaina, 8.00),
            Lanche("Redbull", "Energetico.", R.drawable.redbull, 15.00),
            Lanche("Big-picanha", "Picanha, Queijo, Alho poro.", R.drawable.big_picanha, 40.00),
            Lanche("X-calabresa", "Calabresa, Queijo, Hamburguer, Cheddar.", R.drawable.x_calabresa, 28.00),
            Lanche("X-costela", "Costela, Queijo Cheddar, Vinagrete.", R.drawable.x_costela, 28.00),
            Lanche("X-ovo", "Ovo, Alface, tomate, Picles, Hamburguer.", R.drawable.x_egg, 30.00),
            Lanche("X-salada", "Maionese, Cebola, Alface, tomate, Queijo, Hamburguer.", R.drawable.x_salada, 25.00),
            Lanche("X-tech", "Maionese temperada, Bacon, Queijo, Dois Hamburguer.", R.drawable.x_tech, 40.00),
            Lanche("X-tudo", "Cebola roxa, Tomate, Bacon, Queijo, Hamburguer.", R.drawable.x_tudo, 35.00)
        )

        // Inflar e adicionar cada card ao layout
        lanches.forEach { lanche ->
            // Inflar o layout do card
            val cardView = LayoutInflater.from(this).inflate(R.layout.ticket_card, linearLayoutTickets, false) as CardView

            // Definir título, descrição e imagem do card
            val ticketTitle = cardView.findViewById<TextView>(R.id.ticketTitle)
            val ticketDescription = cardView.findViewById<TextView>(R.id.ticketDescription)
            val ticketImage = cardView.findViewById<ImageView>(R.id.ticketImage)
            val ticketPrice = cardView.findViewById<TextView>(R.id.ticketPrice)

            ticketPrice.text = String.format("R$ %.2f", lanche.preco)
            ticketTitle.text = lanche.nome
            ticketDescription.text = lanche.descricao
            ticketImage.setImageResource(lanche.imagem)

            ticketTitle.text = lanche.nome
            ticketDescription.text = lanche.descricao
            ticketImage.setImageResource(lanche.imagem)

            // Define um clique no card para seleção
            cardView.setOnClickListener {
                val isSelected = cardView.isSelected

                if (isSelected) {
                    totalPayment -= lanche.preco // Remove o valor do pedido se desmarcado
                    itensSelecionados.remove(lanche) // Remove o lanche da lista
                    Toast.makeText(this, "${lanche.nome} desmarcado, total: R$ $totalPayment", Toast.LENGTH_SHORT).show()
                } else {
                    totalPayment += lanche.preco // Adiciona o valor do pedido se selecionado
                    itensSelecionados.add(lanche) // Adiciona o lanche à lista
                    Toast.makeText(this, "${lanche.nome} selecionado, total: R$ $totalPayment", Toast.LENGTH_SHORT).show()
                }

                cardView.isSelected = !isSelected // Alterna o estado de seleção
            }

            // Adiciona o card ao LinearLayout
            linearLayoutTickets.addView(cardView)
        }
    }
}




