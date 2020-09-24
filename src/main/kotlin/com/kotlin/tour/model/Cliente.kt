package com.kotlin.tour.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL) //remove nulls
data class Cliente(
        @JsonProperty("matricula") val id: Long,
        val nome: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss") val dataNascimento: Date,
        var telefone: Telefone?
)

data class Telefone(
        val ddd: String = "",
        val number: String = "",
        @JsonIgnore val tipo: String = ""
)