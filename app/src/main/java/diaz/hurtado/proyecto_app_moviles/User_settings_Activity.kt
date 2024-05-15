package diaz.hurtado.proyecto_app_moviles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class User_settings_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)

        configurarBotonCerrarSesion()
        configurarSwitchNotificaciones()

    }

    private fun configurarBotonCerrarSesion() {
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("isLoggedIn", false)
                apply()
            }

            // Mostrar un Toast indicando que la sesión se cerró
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()

            // Redirigir al usuario a LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun configurarSwitchNotificaciones() {
        val swNotificaciones = findViewById<Switch>(R.id.switchNotificaciones)
        swNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("notificationsEnabled", isChecked)
                apply()
            }
        }
    }
}
