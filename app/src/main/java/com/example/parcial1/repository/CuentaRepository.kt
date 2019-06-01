package com.example.parcial1.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.parcial1.database.dao.CuentaDao
import com.example.parcial1.database.entities.Cuenta

class CuentaRepository(private val cuentaDao: CuentaDao) {

    val allCuentas: LiveData<List<Cuenta>> = cuentaDao.getCuentas()

    @WorkerThread
    suspend fun insert(cuenta: Cuenta) {
        cuentaDao.insertCuenta(cuenta)
    }

    fun getCuentasByName(name: String) = cuentaDao.getCuentaByName(name)

    fun getAllfromRoomDB(): LiveData<List<Cuenta>> = cuentaDao.getCuentas()
}