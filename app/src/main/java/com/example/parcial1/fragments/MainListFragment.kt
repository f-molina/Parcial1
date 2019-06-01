package com.example.parcial1.fragments


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial1.R
import com.example.parcial1.adapters.CuentaAdapter
import com.example.parcial1.constants.AppConstants
import com.example.parcial1.database.entities.Cuenta
import com.example.parcial1.viewmodels.CuentaViewModel
import kotlinx.android.synthetic.main.account_list_fragment.view.*
import java.lang.RuntimeException

class MainListFragment : Fragment() {

    private lateinit var cuentaViewModel: CuentaViewModel
    private lateinit var cuentaAdapter: CuentaAdapter
    var listenerTool : ClickedMovieListener? = null

    interface ClickedMovieListener{
        fun managePortraitItemClick(cuenta: Cuenta)
        fun managedLandscapeItemClick(cuenta: Cuenta)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickedMovieListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de la interfaz")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.account_list_fragment, container, false)

        cuentaViewModel = ViewModelProviders.of(this).get(CuentaViewModel::class.java)

        initRecyclerView(resources.configuration.orientation, view)

        cuentaViewModel.getAll().observe(this, Observer { result ->
            cuentaAdapter.changeDataSet(result)
        })

        return view
    }

    fun initRecyclerView(orientation: Int, container: View) {
        val linearLayoutManager = LinearLayoutManager(this.context)
        val recyclerview  = container.rv_list

        cuentaAdapter = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            CuentaAdapter(cuentas = AppConstants.emptyAccounts, clickListener = { cuenta: Cuenta -> listenerTool?.managePortraitItemClick(cuenta)})
        }else {
            CuentaAdapter(cuentas = AppConstants.emptyAccounts, clickListener = { cuenta: Cuenta -> listenerTool?.managedLandscapeItemClick(cuenta)})
        }

        recyclerview.apply {
            adapter = cuentaAdapter
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)

        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Hace que cuando presiones el botón de sumbit se ejecute lo que pongas aquí
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //Hace que cambie dinamicamente mientras escribis, porque ejecuta lo que pongas aquí cada vez que escribis.
                queryToDatabase(newText?: "N/A")
                return true
            }

        })
    }

    private fun queryToDatabase(query: String) = cuentaViewModel.getCuentasByName("%$query%").observe(this,
        Observer { queryResult -> cuentaAdapter.changeDataSet(queryResult)})


}
