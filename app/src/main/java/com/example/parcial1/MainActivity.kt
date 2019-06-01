package com.example.parcial1

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial1.database.entities.Cuenta
import com.example.parcial1.fragments.MainContentFragment
import com.example.parcial1.fragments.MainListFragment
import com.example.parcial1.viewmodels.CuentaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainListFragment.ClickedMovieListener {
    override fun managedLandscapeItemClick(cuenta: Cuenta) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var mainFragment: MainListFragment
    private lateinit var mainContentFragment: MainContentFragment
    private var resource = 0
    private val newWordActivityRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarmain)

        initFragments()
    }

    fun initFragments() {
        mainFragment = MainListFragment()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            resource = R.id.portrait_main_place_holder
            changeFragment(resource, mainFragment)
        }

    }

    private fun changeFragment(id: Int, frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, frag).addToBackStack(null).commit()
    }

    private fun showContent(id_placeholder: Int, cuenta: Cuenta) {
        mainContentFragment = MainContentFragment.newInstance(cuenta)
        changeFragment(id_placeholder, mainContentFragment)
    }

    override fun managePortraitItemClick(cuenta: Cuenta) = showContent(R.id.portrait_main_place_holder, cuenta)

}

