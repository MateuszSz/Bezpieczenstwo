package model.security;

import model.entity.Rola;
import model.entity.Uzytkownik;
import model.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mateu on 25.03.2017.
 */
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UzytkownikRepository uzytkownikRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        HttpSession request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();

        String wybranaRola = (String) request.getAttribute("role");
        Uzytkownik uzytkownik = uzytkownikRepository.display(uzytkownikRepository.findIdUsingEmail(email));


        if (uzytkownik == null) {
            throw new UsernameNotFoundException("No such user: " + email);
        } else {
            List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
            List<String> nazwy = new ArrayList<String>();
            Collection<Rola> role = uzytkownik.getRole();

            for (Rola r : role) {
                nazwy.add(r.getNazwa());
                auths.add(new SimpleGrantedAuthority(r.getNazwa()));
            }
            if (!nazwy.contains(wybranaRola)) {
                throw new UsernameNotFoundException("Uzytkownik nie posiada roli " + wybranaRola);
            }
            return new CustomUserDetails(uzytkownik.getId(), uzytkownik.getImieINazwisko(), uzytkownik.getEmail(), uzytkownik.getHaslo(), wybranaRola, true, true, true, true, auths);
        }

    }
}
