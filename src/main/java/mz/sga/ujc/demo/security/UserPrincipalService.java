package mz.sga.ujc.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mz.sga.ujc.demo.model.auth.Conta;
import mz.sga.ujc.demo.repository.auth.ContaRepository;

@Service
public class UserPrincipalService implements UserDetailsService{
    
    @Autowired
    private ContaRepository rep;

    public UserPrincipalService(ContaRepository rep){
        this.rep=rep;
    }

    @Override
    public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
        Conta conta=rep.getReferenceByCodigo(Integer.parseInt(codigo));
        if(conta==null){
            throw new UsernameNotFoundException(codigo);
        }
        UserPrincipal userPrincipal = new UserPrincipal(conta);
        return userPrincipal;
    }

}
