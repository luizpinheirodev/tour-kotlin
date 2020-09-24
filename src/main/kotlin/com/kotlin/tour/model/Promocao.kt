package com.kotlin.tour.model

// "data" cria automaticamente getter, setter, equals, hascode, toString

data class Promocao(
        val id: Long,
        val descricao: String,
        val local: String,
        val isAllInclusive: Boolean,
        val qtdDias: Int,
        val preco: Double
)