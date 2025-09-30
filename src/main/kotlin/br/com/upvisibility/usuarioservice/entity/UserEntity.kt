package br.com.upvisibility.usuarioservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
@Table(name = "tb_users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    val name: String,

    @Column(unique = true)
    val email: String,

    val password: String,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String = password

    override fun getUsername(): String = email

}
