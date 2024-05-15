package diaz.hurtado.proyecto_app_moviles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var txtCorreo: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnIngresar: Button
    private lateinit var lblOlvidoContra: TextView
    private lateinit var lblRegistrarse: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtCorreo = findViewById(R.id.txtCorreo)
        txtPassword = findViewById(R.id.txtPassword)
        btnIngresar = findViewById(R.id.btnLogin)
        lblOlvidoContra = findViewById(R.id.lblOlvidoContra)
        lblRegistrarse = findViewById(R.id.lblRegistrarse)

        auth = FirebaseAuth.getInstance()

        btnIngresar.setOnClickListener {
            val correo = txtCorreo.text.toString()
            val password = txtPassword.text.toString()

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                auth.signInWithEmailAndPassword(correo, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d("Exito", "signInWithEmail:success")
                            val user = auth.currentUser
                            Toast.makeText(
                                baseContext,
                                "Se ha iniciado sesión correctamente.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            var intent = Intent(this, MenuActivity::class.java)

                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

        lblOlvidoContra.setOnClickListener {
            val intent = Intent(this, PasswordRecoveryActivity::class.java)
            startActivity(intent)
        }

        lblRegistrarse.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}