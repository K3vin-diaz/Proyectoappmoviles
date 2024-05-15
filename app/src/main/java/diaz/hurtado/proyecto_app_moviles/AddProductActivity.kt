package diaz.hurtado.proyecto_app_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import diaz.hurtado.proyecto_app_moviles.modelos.Producto
class AddProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val database = FirebaseDatabase.getInstance().getReference("productos")

        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = findViewById<EditText>(R.id.etName).text.toString()
            val description = findViewById<EditText>(R.id.etDescription).text.toString()
            val price = findViewById<EditText>(R.id.etPrice).text.toString().toDouble()
            val stock = findViewById<EditText>(R.id.etStock).text.toString().toInt()
            val image = R.drawable.photo2 // Aquí puedes agregar lógica para permitir al usuario seleccionar una imagen

            val newProduct = Producto(name, description, price, stock, image)

            // Guarda el producto en Firebase
            val id = database.push().key
            database.child(id!!).setValue(newProduct)

            // Pasa el producto de vuelta a tu MenuActivity
            val resultIntent = Intent()
            resultIntent.putExtra("newProduct", newProduct)
            setResult(RESULT_OK, resultIntent)
            finish()

            // Muestra un Toast
            Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
        }
    }
}