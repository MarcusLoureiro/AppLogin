package com.example.snackbar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.snackbar.R
import kotlinx.android.synthetic.main.login_body.*

class LoginActivity : AppCompatActivity() {

    lateinit var edEmail: EditText
    lateinit var edPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edEmail = findViewById(R.id.edEmailCadastro)
        edPassword = findViewById(R.id.edPassword)

        btn_CadConfirm.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
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


        fun validarCampos(): Boolean {
            var retorno = false
            var res: Boolean = false

            var email: String = edEmail.text.toString()
            var password: String = edPassword.text.toString()

            if (TesteCampoVazio(email)) {
                edEmail.requestFocus()
                res = true

                } else if (TesteCampoVazio(password)) {
                edPassword.requestFocus()
                res = true

                    } else if (!TesteEmailValido(email)) {
                     edEmail.requestFocus()
                    res = true
            }else{
                retorno = true
            }

            if(res){

                var dlg = AlertDialog.Builder(this)
                dlg.setTitle("Aviso")
                dlg.setMessage("Há campos inválidos ou em branco!")
                dlg.setNeutralButton("OK", null)
                dlg.show()
            }else{
                retorno = true
            }
            return retorno
        }

        btn_login.setOnClickListener{
            if(validarCampos()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Olá, ${edEmail.text.toString()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}