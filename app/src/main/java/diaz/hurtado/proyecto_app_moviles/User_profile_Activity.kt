package diaz.hurtado.proyecto_app_moviles

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class User_profile_Activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()

        val newPasswordField = findViewById<EditText>(R.id.new_password)
        val confirmNewPasswordField = findViewById<EditText>(R.id.confirm_new_password)
        val changePasswordButton = findViewById<Button>(R.id.change_password)
        changePasswordButton.setOnClickListener {
            val newPassword = newPasswordField.text.toString()
            val confirmNewPassword = confirmNewPasswordField.text.toString()

            if (newPassword == confirmNewPassword) {
                AlertDialog.Builder(this)
                    .setTitle("Confirmar cambio de contraseña")
                    .setMessage("¿Estás seguro de que quieres cambiar tu contraseña?")
                    .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
                        auth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Contraseña actualizada", Toast.LENGTH_SHORT).show()
                                redirectToLogin()
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
        }

        val deactivateAccountButton = findViewById<Button>(R.id.desactivate_account)
        deactivateAccountButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirmar desactivación de cuenta")
                .setMessage("¿Estás seguro de que quieres desactivar tu cuenta?")
                .setPositiveButton("Aceptar") { _, _ ->
                    auth.currentUser?.delete()?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Cuenta desactivada", Toast.LENGTH_SHORT).show()
                            redirectToLogin()
                        } else {
                            Toast.makeText(this, "Error al desactivar cuenta", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}