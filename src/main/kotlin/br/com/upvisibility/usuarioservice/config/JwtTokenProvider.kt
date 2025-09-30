package br.com.upvisibility.usuarioservice.config

import br.com.upvisibility.usuarioservice.entity.UserEntity
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.Base64
import java.util.Date

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private val secret: String,
    @Value("\${jwt.expiration-in-ms}")
    private val expirationInMs: Long
) {
    private val key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret.toByteArray()))

    fun generateToken(user: UserEntity): String {
        val now = Date()
        val expiryDate = Date(now.time + expirationInMs)
        return Jwts.builder()
            .subject(user.email)
            .issuedAt(now)
            .expiration(expiryDate)
            .signWith(key)
            .compact()
    }

    fun getEmailFromToken(token: String): String {
        return getClaimsFromToken(token).subject
    }

    fun getClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
            return true
        } catch (e: Exception) {
            println(e.message)
        }
        return false
    }
}