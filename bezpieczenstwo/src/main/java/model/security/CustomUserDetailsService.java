package model.security;

import model.entity.Rola;
import model.entity.Uzytkownik;
import model.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
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
    private SessionRegistry sessionRegistry;

    @Autowired
    private UzytkownikRepository uzytkownikRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        HttpSession request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();

        String wybranaRola = (String) request.getAttribute("role");
        Uzytkownik uzytkownik = uzytkownikRepository.display(uzytkownikRepository.findIdUsingEmail(email));


        if (uzytkownik == null) {
            request.setAttribute("wiadomosc", "Błędny login lub hasło");
            throw new RoleException("No such user: " + email);
        } else {

            List<Object> principals = sessionRegistry.getAllPrincipals();
            CustomUserDetails cs;
            List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
            List<String> nazwy = new ArrayList<String>();
            Collection<Rola> role = uzytkownik.getRole();
            boolean isRedirected = false;

            for (Rola r : role) {
                nazwy.add(r.getNazwa());
                auths.add(new SimpleGrantedAuthority(r.getNazwa()));
            }
            if (!nazwy.contains(wybranaRola)) {
                request.setAttribute("wiadomosc", "Użytkownik nie posiada roli: " + wybranaRola);
                throw new RoleException("Password incorrect");

            }

            for (Object principal : principals) {
                cs = (CustomUserDetails) principal;
                if (cs.getEmail().equals(uzytkownik.getEmail())) {
                    if (!wybranaRola.equals(cs.getWybranaRola())) {
                        request.setAttribute("wiadomosc", "Użytkownik już zalogowany na innej roli: " + cs.getWybranaRola());
                        throw new RoleException("user logged in with another computer: " + email);
                    }
                }
            }

            return new CustomUserDetails(uzytkownik.getId(), uzytkownik.getImieINazwisko(), uzytkownik.getEmail(), uzytkownik.getHaslo(), wybranaRola, true, true, true, isRedirected, true, auths);

        }

    }
}
