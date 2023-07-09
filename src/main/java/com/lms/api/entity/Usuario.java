package com.lms.api.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lms.api.dto.DadosAtualizacaoUsuario;
import com.lms.api.dto.DadosCadastroUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;

    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.ativo = true;
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoUsuario dados) {
        if (dados.email() != null) {
            this.email = dados.email();
        }
    }

    public void excluir() {
        this.ativo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getAuthorities'");
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getPassword'");
        return senha;
    }

    @Override
    public String getUsername() {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getUsername'");
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'isAccountNonExpired'");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'isAccountNonLocked'");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'isCredentialsNonExpired'");
        return true;
    }

    @Override
    public boolean isEnabled() {
        // throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
        return true;
    }
}
