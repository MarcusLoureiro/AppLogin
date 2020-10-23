package com.example.snackbar.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.snackbar.R
import kotlinx.android.synthetic.main.cadastro_body.*

class CadastroActivity: AppCompatActivity() {
    lateinit var edNameCadastro: EditText
    lateinit var edEmailCadastro: EditText
    lateinit var edPasswordCadastro: EditText
    lateinit var edPasswordConfirm: EditText
    lateinit var btn_cadastro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        btn_cadastro = findViewById(R.id.btn_CadConfirm)
        edNameCadastro = findViewById(R.id.edUserNameCadastro)
        edEmailCadastro = findViewById(R.id.edEmailCadastro)
        edPasswordCadastro = findViewById(R.id.edPasswordCadastro)
        edPasswordConfirm = findViewById(R.id.edPasswordConfirm)

        cadastro_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        fun TesteCampoVazio(valor: String): Boolean {
            var resultado: Boolean = (TextUtils.isEmpty(valor) || valor.trim().isEmpty())
            return resultado
        }

        fun TesteEmailValido(email: String): Boolean {
            var resultado: Boolean =
                (!TesteCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return resultado
        }

        fun TesteSenaha():Boolean{
            var resultado = false
            if(edPasswordCadastro.text.toString() == edPasswordConfirm.text.toString()){
                resultado = true
            }
            return resultado
        }

        fun validarCampos(): Boolean {
            var retorno = false
            var res: Boolean = false

            var userName: String = edNameCadastro.text.toString()
            var email: String = edEmailCadastro.text.toString()
            var password: String = edPasswordCadastro.text.toString()
            var passwordConfirm: String = edPasswordConfirm.text.toString()

            if (TesteCampoVazio(email)) {
                edEmailCadastro.requestFocus()
                res = true

            } else if (TesteCampoVazio(password)) {
                edPasswordCadastro.requestFocus()
                res = true

            } else if (TesteCampoVazio(passwordConfirm)) {
                edPasswordConfirm.requestFocus()
                res = true

            } else if (TesteCampoVazio(userName)) {
                edUserNameCadastro.requestFocus()
                res = true

            } else if (!TesteEmailValido(email)) {
                edEmailCadastro.requestFocus()
                res = true
            } else {
                retorno = true
            }

            if (res) {

                var dlg = AlertDialog.Builder(this)
                dlg.setTitle("Aviso")
                dlg.setMessage("Há campos inválidos ou em branco!")
                dlg.setNeutralButton("OK", null)
                dlg.show()
            } else {
                retorno = true
            }
            return retorno
        }

        btn_cadastro.setOnClickListener {
            if (TesteSenaha() == false) {
                Toast.makeText(this, "Senhas não correspondem!", Toast.LENGTH_SHORT).show()
            } else {
                if (validarCampos()) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(
                        this,
                        "Olá, ${edNameCadastro.text.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}