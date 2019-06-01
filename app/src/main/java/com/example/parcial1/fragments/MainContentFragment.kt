package com.example.parcial1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.parcial1.NewAccountActivity
import com.example.parcial1.R
import com.example.parcial1.database.entities.Cuenta
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.account_viewer.view.*

class MainContentFragment : Fragment() {

    var cuenta = Cuenta()

    companion object {
        fun newInstance(dataset: Cuenta): MainContentFragment {
            return MainContentFragment().apply {
                cuenta = dataset
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.account_viewer, container, false)

        bindData(view, cuenta)

        return view
    }

    fun bindData(view: View, data: Cuenta){
        view.account_summary.text = data.nombre
        view.saldo_viewer.text = data.saldo
        view.cargo_viewer.text = data.cargo

        val abonar: Button = view.findViewById(R.id.btn_abonar)
        abonar.setOnClickListener{
            view.saldo_viewer.text = "$550"
        }

        /*val fab: FloatingActionButton = view.findViewById(R.id.main_add_button)
        fab.setOnClickListener {
            fragmentManager!!.beginTransaction().add(R.id.portrait_main_place_holder).addToBackStack("").commit()
        }*/
    }

}
