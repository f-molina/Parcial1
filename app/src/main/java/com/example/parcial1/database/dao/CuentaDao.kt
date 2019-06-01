package com.example.parcial1.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.parcial1.database.entities.Cuenta

@Dao
interface CuentaDao {
    @Insert
    suspend fun insertCuenta(cuenta: Cuenta)

    @Query("SELECT * FROM cuenta_table")
    fun getCuentas(): LiveData<List<Cuenta>>

    @Query("SELECT * FROM cuenta_table WHERE nombre LIKE :name")
    fun getCuentaByName(name: String): LiveData<List<Cuenta>>

    @Query("DELETE FROM cuenta_table")
    fun deleteAll()
}