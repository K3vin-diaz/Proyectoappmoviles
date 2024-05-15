package diaz.hurtado.proyecto_app_moviles.modelos

import java.io.Serializable

data class Producto(
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    var stock: Int,
    val imagen: Int
): Serializable