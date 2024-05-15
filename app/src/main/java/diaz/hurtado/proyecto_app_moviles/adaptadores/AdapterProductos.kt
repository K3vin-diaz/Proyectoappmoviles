package diaz.hurtado.proyecto_app_moviles.adaptadores

import ViewHolderProducto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import diaz.hurtado.proyecto_app_moviles.R
import diaz.hurtado.proyecto_app_moviles.modelos.Producto

class AdapterProductos(var productos: List<Producto>) : RecyclerView.Adapter<ViewHolderProducto>() {

    fun updateData(newData: List<Producto>) {
        this.productos = newData
        notifyDataSetChanged()
    }

    fun addProduct(newProduct: Producto) {
        this.productos = this.productos + newProduct
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProducto {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.producto_item, parent, false)
        return ViewHolderProducto(view)
    }

    override fun onBindViewHolder(holder: ViewHolderProducto, position: Int) {
        val producto = productos[position]
        holder.bindProducto(producto)
    }

    override fun getItemCount() = productos.size
}