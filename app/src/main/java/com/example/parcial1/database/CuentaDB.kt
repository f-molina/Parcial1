package com.example.parcial1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.parcial1.database.dao.CuentaDao
import com.example.parcial1.database.entities.Cuenta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Cuenta::class], version = 7, exportSchema = false)
abstract class CuentaDB: RoomDatabase() {

    abstract fun cuentaDao(): CuentaDao

    companion object {
        @Volatile
        private var INSTANCE: CuentaDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CuentaDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CuentaDB::class.java,
                    "cuenta_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.cuentaDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(wordDao: CuentaDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            var cuenta = Cuenta(
                "1", "Super Alimentos", "Algo", "$500", "$40", "$1.25"
            )
            wordDao.insertCuenta(cuenta)

             cuenta = Cuenta(
                "2","Carros", "Ahorro", "$400", "$400", "$50"
            )
            wordDao.insertCuenta(cuenta)

            cuenta = Cuenta(
                "3", "Viajes", "Ahorro", "$100", "$150", "$1.50"
            )
            wordDao.insertCuenta(cuenta)

            cuenta = Cuenta(
                "4", "Algo", "Algo", "$300", "$50", "$10"
            )
            wordDao.insertCuenta(cuenta)
        }
    }

}