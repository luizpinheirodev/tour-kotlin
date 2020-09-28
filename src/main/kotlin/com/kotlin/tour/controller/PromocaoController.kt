package com.kotlin.tour.controller

import com.kotlin.tour.model.ErrorMessage
import com.kotlin.tour.model.Promocao
import com.kotlin.tour.model.RespostaJSON
import com.kotlin.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("/menorQue9000")
    fun getAllMenores() = this.promocaoService.getAllByPrecoMenorQue9000()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
//        var promocao = this.promocaoService.getById(id) ?: throw PromocaoNotFoundException("Promoção ${id} não localizada") // Evitar usar contoller advice para exceção de negocio

        var promocao = this.promocaoService.getById(id)
        return if (promocao != null)
            return ResponseEntity(promocao, HttpStatus.OK)
        else
            return ResponseEntity(ErrorMessage("Promocao não localizada", "Promoção ${id} não localizada"), HttpStatus.NOT_FOUND)
    }

    @PostMapping()
    fun create(@RequestBody promocao: Promocao): ResponseEntity<RespostaJSON> {
        this.promocaoService.create(promocao)
        val respostaJSON = RespostaJSON("OK", Date())
        return ResponseEntity(respostaJSON, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            status = HttpStatus.ACCEPTED
            this.promocaoService.delete(id)
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            status = HttpStatus.ACCEPTED
            this.promocaoService.update(id, promocao)
        }
        return ResponseEntity(Unit, status)

    }

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "0") start: Int,
               @RequestParam(required = false, defaultValue = "3") size: Int)
            : ResponseEntity<List<Promocao>> {
        val listPromocoes = this.promocaoService.getAll(start, size)
        var status = if (listPromocoes.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(listPromocoes, status)
    }

    @GetMapping("/count")
    fun count(): ResponseEntity<Map<String, Long>> =
            ResponseEntity.ok().body(mapOf("count" to this.promocaoService.count()))

    @GetMapping("/ordenados")
    fun ordenados() = this.promocaoService.getAllSortedByLocal()

}