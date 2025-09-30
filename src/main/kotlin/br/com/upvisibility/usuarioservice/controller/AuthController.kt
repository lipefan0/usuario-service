package br.com.upvisibility.usuarioservice.controller

import br.com.upvisibility.usuarioservice.config.JwtTokenProvider
import br.com.upvisibility.usuarioservice.dtos.JwtResponseDTO
import br.com.upvisibility.usuarioservice.dtos.LoginDTO
import br.com.upvisibility.usuarioservice.dtos.RegisterDTO
import br.com.upvisibility.usuarioservice.dtos.RegisterResponseDTO
import br.com.upvisibility.usuarioservice.entity.UserEntity
import br.com.upvisibility.usuarioservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwt: JwtTokenProvider
) {

    @PostMapping("/register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<RegisterResponseDTO> {
        return ResponseEntity.ok(userService.save(body))
    }

    @PostMapping("/login")
    fun login(@RequestBody body: LoginDTO): ResponseEntity<JwtResponseDTO> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(body.email, body.password)
        )
        SecurityContextHolder.getContext().authentication = authentication

        val user = authentication.principal as UserEntity
        val token = jwt.generateToken(user)
        return ResponseEntity.ok(JwtResponseDTO(token))
    }
}