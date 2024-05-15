import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import diaz.hurtado.proyecto_app_moviles.R
import diaz.hurtado.proyecto_app_moviles.User_checkout_Activity
import diaz.hurtado.proyecto_app_moviles.modelos.Producto



class ViewHolderProducto(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nombre: TextView = itemView.findViewById(R.id.nombreProducto)
    val descripcion: TextView = itemView.findViewById(R.id.descripcionProducto)
    val precio: TextView = itemView.findViewById(R.id.precioProducto)
    val stock: TextView = itemView.findViewById(R.id.stockProducto)
    val imagen: ImageView = itemView.findViewById(R.id.imagenProducto)
    val btnAgregarCarrito: Button = itemView.findViewById(R.id.btnAgregarCarrito)
    val btnDecrement: Button = itemView.findViewById(R.id.btnDecrement)
    val tvCantidad: TextView = itemView.findViewById(R.id.tvCantidad)
    val btnIncrement: Button = itemView.findViewById(R.id.btnIncrement)

    fun bindProducto(producto: Producto) {
        nombre.text = producto.nombre
        descripcion.text = producto.descripcion
        precio.text = producto.precio.toString()
        stock.text = producto.stock.toString()
        imagen.setImageResource(producto.imagen)

        var cantidad = tvCantidad.text.toString().toInt()

        btnDecrement.setOnClickListener {
            if (cantidad > 0) {
                cantidad--
                tvCantidad.text = cantidad.toString()
            }
        }

        btnIncrement.setOnClickListener {
            cantidad++
            tvCantidad.text = cantidad.toString()
        }

        btnAgregarCarrito.setOnClickListener {
            val intent = Intent(itemView.context, User_checkout_Activity::class.java)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("precio", producto.precio)
            intent.putExtra("cantidad", cantidad) // Agregar la cantidad al intent
            itemView.context.startActivity(intent)
        }
    }
}