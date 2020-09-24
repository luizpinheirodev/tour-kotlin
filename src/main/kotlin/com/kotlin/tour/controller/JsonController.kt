package com.kotlin.tour.controller

import com.kotlin.tour.model.Cliente
import com.kotlin.tour.model.SimpleObject
import com.kotlin.tour.model.Telefone
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class JsonController {
    @GetMapping("/json")
    fun getJson() = SimpleObject()

    @GetMapping("/cliente")
    fun getCliente(): Cliente {
        var telefone = Telefone("21", "123456789", "Fixo")
        var cliente = Cliente(1, "Luiz", Date(), telefone)
        return cliente
    }
}