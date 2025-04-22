package br.com.findpark.domain;

import br.com.findpark.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    private String id;
    private String email;
    private String senha;
    private String telefone;
    private UserRole role;
    private String refreshToken;

    public Usuario(String email, String senha, String telefone, UserRole role) {
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_PROPRIETARIO"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        } else if (this.role == UserRole.PROPRIETARIO) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_PROPRIETARIO")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
