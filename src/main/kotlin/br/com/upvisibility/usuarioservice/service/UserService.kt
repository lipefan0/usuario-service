package br.com.upvisibility.usuarioservice.service

import br.com.upvisibility.usuarioservice.dtos.RegisterDTO
import br.com.upvisibility.usuarioservice.dtos.RegisterResponseDTO
import br.com.upvisibility.usuarioservice.entity.UserEntity
import br.com.upvisibility.usuarioservice.mapper.UserMapper
import br.com.upvisibility.usuarioservice.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userMapper: UserMapper
) {

    fun save(user: RegisterDTO): RegisterResponseDTO {
        if (userRepository.findByEmail(user.email) != null) {
            throw IllegalArgumentException("Email already in use")
        }

        val userWithEncodedPassword = user.copy(
            password = passwordEncoder.encode(user.password)
        )

        return with(userMapper) {
            val userEntity = userWithEncodedPassword.toUserEntity()
            val savedUser = userRepository.save(userEntity)
            savedUser.toResponseDTO()
        }
    }

    fun findUserById(id: String): UserEntity? = userRepository.findById(id).orElse(null)

}