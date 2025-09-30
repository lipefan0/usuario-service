package br.com.upvisibility.usuarioservice.mapper

import br.com.upvisibility.usuarioservice.dtos.RegisterDTO
import br.com.upvisibility.usuarioservice.dtos.RegisterResponseDTO
import br.com.upvisibility.usuarioservice.entity.UserEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserMapper {

    fun RegisterDTO.toUserEntity(): UserEntity = UserEntity(
        name = this.name,
        email = this.email,
        passwordHash = this.password,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )

    fun UserEntity.toResponseDTO(): RegisterResponseDTO = RegisterResponseDTO(
        id = this.id!!,
        message = "Usuário '${this.name}' criado com sucesso"
    )
}