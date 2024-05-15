package diaz.hurtado.proyecto_app_moviles

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import diaz.hurtado.proyecto_app_moviles.adaptadores.AdapterProductos
import diaz.hurtado.proyecto_app_moviles.modelos.Producto


class MenuActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterProductos
    private lateinit var productos: MutableList<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productos = obtenerProductos("").toMutableList()
        adapter = AdapterProductos(productos)
        recyclerView.adapter = adapter

        val editText = findViewById<EditText>(R.id.editTextText)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                productos = obtenerProductos(s.toString()).toMutableList()
                adapter.updateData(productos)
            }
        })

        val btnAgregarProducto = findViewById<Button>(R.id.btnAgregarProducto)
        btnAgregarProducto.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val newProduct = data?.getSerializableExtra("newProduct") as? Producto
            newProduct?.let {
                productos.add(it)
                adapter.notifyItemInserted(productos.size - 1)
            }
        }
    }

    private fun obtenerProductos(busqueda: String): List<Producto> {
        val productos = listOf(
            Producto("Procesador Intel 9", "Soy un procesador, jalo machin", 9.99, 10, R.drawable.photo3),
            Producto("Laptop not gamer", "Medio Jalo", 19.99, 5, R.drawable.photo2),
            Producto("Soy un celular", "Sale una mano en la foto", 29.99, 20, R.drawable.photo1)
        )

        return productos.filter { it.nombre.contains(busqueda, ignoreCase = true) }
    }

    fun onBtnPerfilClick(view: View) {
        // Navegar a la actividad de perfil
        val intent = Intent(this, User_profile_Activity::class.java)
        startActivity(intent)
    }

    fun onBtnCarritoClick(view: View) {
        // Navegar a la actividad de carrito
        val intent = Intent(this, User_checkout_Activity::class.java)
        startActivity(intent)
    }

    fun onBtnAjustesClick(view: View) {
        // Navegar a la actividad de ajustes
        val intent = Intent(this, User_settings_Activity::class.java)
        startActivity(intent)
    }
}