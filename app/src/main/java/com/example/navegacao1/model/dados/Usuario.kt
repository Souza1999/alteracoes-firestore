package com.example.navegacao1.model.dados

import com.google.firebase.firestore.DocumentId

data class Usuario(
    @DocumentId
    var id: String = "",   // campo id mapeado automaticamente pelo Firestore
    val nome: String = "",
    val senha: String = ""
)
