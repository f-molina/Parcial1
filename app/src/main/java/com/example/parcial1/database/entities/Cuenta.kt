package com.example.parcial1.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cuenta_table")
data class Cuenta(
    @PrimaryKey
    @ColumnInfo(name = "id_cuenta") val id_cuenta: String = "N/a",
    @ColumnInfo(name = "nombre") val nombre: String = "N/a",
    @ColumnInfo(name = "concepto") val concepto: String = "N/a",
    @ColumnInfo(name = "saldo") val saldo: String = "N/a",
    @ColumnInfo(name = "abono") val abono: String = "N/a",
    @ColumnInfo(name = "cargo") val cargo: String = "N/a"
)