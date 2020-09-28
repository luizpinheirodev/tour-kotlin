package com.kotlin.tour.repository

import com.kotlin.tour.model.Promocao
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PromocaoRepository : PagingAndSortingRepository<Promocao, Long> {
    @Query(value = "select p from Promocao p where p.preco <= :preco")
    fun findByPrecoMenorQue(@Param("preco") preco: Double): List<Promocao>

    @Query(value = "select * from TBL_PROMOCAO p where p.preco <= 9000", nativeQuery = true)
    fun findByPrecoMenorQue9000SQLPuro(): List<Promocao>

    @Query(value = "select p from Promocao p where p.local IN :names")
    fun findByLocalInList(@Param("names") names: List<String>): List<Promocao>

    @Query(value = "update Promocao p set p.preco = :valor where p.local = :local")
    @Transactional
    @Modifying
    fun updateByLocal(@Param("valor") valor: Double, @Param("local") local: String)
}