package model.security;

import model.entity.Rola;
import model.entity.Uprawnienie;
import model.service.RolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by mateu on 25.03.2017.
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private RolaService rolaService;

    @Transactional
    public boolean hasPermission(Authentication authentication, Object o, Object permission) {
        boolean hasPermission = false;
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if (customUserDetails != null && permission instanceof String) {

            Rola rola = rolaService.display(rolaService.findIdUsingName(customUserDetails.getWybranaRola()));
            for (Uprawnienie uprawnienie : rola.getUprawnienia()) {
                if (uprawnienie.getNazwa().equals(permission))
                    hasPermission = true;

            }
        }
        return hasPermission;
    }

    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
