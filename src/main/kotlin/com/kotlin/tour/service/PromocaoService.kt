package com.kotlin.tour.service

import com.kotlin.tour.model.Promocao

interface PromocaoService {
    fun create(promocao: Promocao)
    fun getById(id: Long): Promocao? //Optional
    fun delete(id: Long)
    fun update(id: Long, promocao: Promocao)
    fun searchByLocal(local: String): List<Promocao>
    fun getAll(start: Int, size: Int): List<Promocao>
    fun count(): Long
    fun getAllSortedByLocal(): List<Promocao>
    fun getAllByPrecoMenorQue9000(): List<Promocao>
}