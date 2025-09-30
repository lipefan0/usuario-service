package br.com.upvisibility.usuarioservice.dtos

data class RegisterDTO(
    val name: String,
    val email: String,
    val password: String
)