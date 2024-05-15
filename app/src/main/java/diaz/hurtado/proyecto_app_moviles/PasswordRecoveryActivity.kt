package diaz.hurtado.proyecto_app_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class PasswordRecoveryActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_recovery)

        auth = FirebaseAuth.getInstance()

        val txtCorreo: EditText = findViewById(R.id.txtCorreo)
        val btnRecuperar: Button = findViewById(R.id.btnRecuperar)

        btnRecuperar.setOnClickListener {
            val email = txtCorreo.text.toString()

            if (email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            AlertDialog.Builder(this)
                                .setTitle("Recuperación de contraseña")
                                .setMessage("Se ha enviado un enlace de recuperación a su correo registrado")
                                .setPositiveButton("OK") { _, _ ->
                                    val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                .show()
                        } else {
                            Toast.makeText(this, "Error al enviar correo de recuperación", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, introduce tu correo electrónico", Toast.LENGTH_SHORT).show()
            }
        }
    }
}