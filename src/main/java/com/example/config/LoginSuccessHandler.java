package com.example.config;

import com.example.model.Role;
import com.example.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {    //, ServletException
        /*HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("user", authentication.getPrincipal());
        if (authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
            httpServletResponse.sendRedirect("/admin");
        } else {
            httpServletResponse.sendRedirect("/user");
        }*/
        User user = (User) authentication.getPrincipal();
        System.out.println(user);
        List<String> roleList = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roleList.add(role.getRole());
        }
        System.out.println(roleList);
        if (roleList.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        } else if (roleList.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/hello");
        } else {
            System.out.println("Для такого пользователя нет кабинета");
        }
    }
}