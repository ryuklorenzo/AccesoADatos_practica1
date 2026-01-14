package ies.sequeros.dam.ad.orm.domain

import java.util.UUID

interface ICategoriaRepository {
    fun all(): MutableList<Categoria>
    fun update(item: Categoria)
    fun create(item: Categoria)
    fun delete(item: Categoria?)
    fun delete(id: UUID)
    fun findById(id: UUID): Categoria?
    fun existsById(id: String): Boolean
}