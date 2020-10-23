package com.example.snackbar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.snackbar.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val frag_home = HomeFragment()
        val frag_entrada = EntradasFragment()
        val frag_gastos = GastosFragment()
         btn_fragment_home.setOnClickListener {
             supportFragmentManager.beginTransaction().apply {
                 replace(R.id.fragment_layout, frag_home)
                 commit()
             }
         }

        btn_fragments_Entradas.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_layout, frag_entrada)
                commit()
            }
        }

        btn_fragment_Gastos.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_layout, frag_gastos)
                commit()
            }
        }
    }
}