package diaz.hurtado.proyecto_app_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class User_checkout_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_checkout)
        val nombreProducto = intent.getStringExtra("nombre")
        val precioProducto = intent.getDoubleExtra("precio", 0.0)
        val imagenProducto = intent.getStringExtra("imagen")

        val nombreTextView = findViewById<TextView>(R.id.nombreProducto)
        val precioTextView = findViewById<TextView>(R.id.precioProducto)
        val totalTextView = findViewById<TextView>(R.id.totalCompra)
        val imagenView = findViewById<ImageView>(R.id.productImage)
        val confirmarCompra = findViewById<Button>(R.id.confirmarCompra)
        val btnLimpiarCarrito = findViewById<Button>(R.id.btnLimpiarCarrito)

        nombreTextView.text = nombreProducto
        precioTextView.text = precioProducto.toString()

        // Sumar el precio del producto al total de la compra
        val total = precioProducto // Aquí puedes sumar el precio del producto a un total existente
        totalTextView.text = "Total: $${total}"

        // Cargar la imagen del producto
        Glide.with(this).load(imagenProducto).into(imagenView)

        // Configurar el escuchador de clics del botón de compra
        confirmarCompra.setOnClickListener {
            // Guardar la información de la compra en Firebase
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("compras")



            // Calcular el precio total
            val precioTotal = precioProducto

            val compra = hashMapOf(
                "nombre" to nombreProducto,
                "precio" to precioTotal, // Usar el precio total en lugar del precio del producto
                "imagen" to imagenProducto,

            )

            myRef.push().setValue(compra).addOnSuccessListener {
                // Mostrar un Toast cuando la compra se realiza correctamente
                Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show()

                // Redirigir al usuario a MenuActivity
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Configurar el escuchador de clics del botón de limpiar carrito
        btnLimpiarCarrito.setOnClickListener {
            // Aquí va el código para limpiar el carrito
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("compras")
            myRef.removeValue().addOnSuccessListener {
                // Mostrar un Toast cuando el carrito se limpia correctamente
                Toast.makeText(this, "Carrito limpiado con éxito", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}