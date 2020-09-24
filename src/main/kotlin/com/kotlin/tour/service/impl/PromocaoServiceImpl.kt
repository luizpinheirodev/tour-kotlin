package com.kotlin.tour.service.impl

import com.kotlin.tour.model.Promocao
import com.kotlin.tour.service.PromocaoService
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromocaoServiceImpl : PromocaoService {
    companion object {
        val initialPromocoes = arrayOf(
                Promocao(1, "Maravilhosa Viagem a Cancun", "Cancun", true, 7, 4200.99),
                Promocao(2, "Viagem radical com rapel e escalada", "Nova Zelandia", false, 12, 12000.0),
                Promocao(3, "Viagem espiritual", "Thailandia", false, 17, 15000.0),
                Promocao(4, "Viagem com a familia", "Gramado", false, 5, 3500.33)
        )
    }

    var promocoes = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))
    override fun create(promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }

    override fun getById(id: Long): Promocao? {
        return promocoes[id]
    }

    override fun delete(id: Long) {
        promocoes.remove(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        delete(id)
        create(promocao)
    }

    override fun searchByLocal(local: String): List<Promocao> =
            promocoes.filter {
                it.value.local.contains(local, true)
            }.map(Map.Entry<Long, Promocao>::value).toList()

}