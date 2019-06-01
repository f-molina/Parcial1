package com.example.parcial1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parcial1.R
import com.example.parcial1.database.entities.Cuenta
import kotlinx.android.synthetic.main.rv_accounts.view.*

class CuentaAdapter(var cuentas: List<Cuenta>, val clickListener: (Cuenta) -> Unit) : RecyclerView.Adapter<CuentaAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_accounts, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cuentas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(cuentas[position], clickListener)

    fun changeDataSet(newMovieList: List<Cuenta>) {
        cuentas = newMovieList
        notifyDataSetChanged()
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        fun bind(cuenta: Cuenta, clickListener: (Cuenta) -> Unit) = with(itemView){
            account_title.text = cuenta.nombre
            cargo_arrow.text = cuenta.cargo
            abono_arrow.text = cuenta.abono
            saldo_ammount.text = cuenta.saldo
            this.setOnClickListener { clickListener(cuenta) }
        }
    }
}