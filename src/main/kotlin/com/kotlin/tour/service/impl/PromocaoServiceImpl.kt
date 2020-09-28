package com.kotlin.tour.service.impl

import com.kotlin.tour.model.Promocao
import com.kotlin.tour.repository.PromocaoRepository
import com.kotlin.tour.service.PromocaoService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class PromocaoServiceImpl(val promocaoRepository: PromocaoRepository) : PromocaoService {

    override fun create(promocao: Promocao) {
        this.promocaoRepository.save(promocao)
    }

    override fun getById(id: Long): Promocao? {
        return this.promocaoRepository.findById(id).orElseGet(null)
    }

    override fun delete(id: Long) {
        this.promocaoRepository.deleteById(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        create(promocao)
    }

    override fun searchByLocal(local: String): List<Promocao> =
            listOf()

    override fun getAll(start: Int, size: Int): List<Promocao> {
        val pages = PageRequest.of(start, size, Sort.by("local").ascending())
        return this.promocaoRepository.findAll(pages).toList()
    }

    override fun count(): Long {
        return this.promocaoRepository.count()
    }

    override fun getAllSortedByLocal(): List<Promocao> {
        return this.promocaoRepository.findAll(Sort.by("local").descending()).toList()
    }

    override fun getAllByPrecoMenorQue9000(): List<Promocao> {
        return this.promocaoRepository.findByPrecoMenorQue()
    }

}