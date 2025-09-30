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

    val passwordHash: String,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? = null
    override fun getPassword(): String = this.passwordHash

    override fun getUsername(): String = this.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}
