
package mz.sga.ujc.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import mz.sga.ujc.demo.model.auth.Conta;

/**
 * UserPrincipal
 */
public class UserPrincipal implements UserDetails {

    @Autowired
    private Conta conta;

    public UserPrincipal(Conta conta) {
        this.conta = conta;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles[] = { "ADMIN", "USER" };
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return conta.getSenha();
    }

    @Override
    public String getUsername() {
        return conta.getCodigo().toString();
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
