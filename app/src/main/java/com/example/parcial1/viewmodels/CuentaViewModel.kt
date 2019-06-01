package com.example.parcial1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.parcial1.database.CuentaDB
import com.example.parcial1.database.entities.Cuenta
import com.example.parcial1.repository.CuentaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CuentaViewModel(application: Application): AndroidViewModel(application) {

    private val repository: CuentaRepository
    val allCuentas: LiveData<List<Cuenta>>

    init {
        val cuentaDao = CuentaDB.getDatabase(application, viewModelScope).cuentaDao()
        repository = CuentaRepository(cuentaDao)
        allCuentas = repository.allCuentas
    }

    fun insert(cuenta: Cuenta) = viewModelScope.launch(
        Dispatchers.IO) {
        repository.insert(cuenta)
    }

    fun getCuentasByName(name: String): LiveData<List<Cuenta>> = repository.getCuentasByName(name)

    fun getAll(): LiveData<List<Cuenta>> = repository.getAllfromRoomDB()
}