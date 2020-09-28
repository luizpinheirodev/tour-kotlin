package com.kotlin.tour.model

import javax.persistence.*

// "data" cria automaticamente getter, setter, equals, hascode, toString

@Entity
@Table(name = "TBL_PROMOCAO")
data class Promocao(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 1,
        val descricao: String = "",
        val local: String = "",
        val isAllInclusive: Boolean = false,
        @Column(name = "quantidade_dias")
        val qtdDias: Int = 1,
        val preco: Double = 0.0
)