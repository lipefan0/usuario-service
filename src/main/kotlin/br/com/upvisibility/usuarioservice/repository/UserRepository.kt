package br.com.upvisibility.usuarioservice.repository

import br.com.upvisibility.usuarioservice.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, String> {

    fun findByEmail(email: String): UserEntity?
}