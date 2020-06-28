package com.example.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    /**
     * Accessible without login
     *
     * @return
     */
    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    /**
     * Accessible by all users after login
     *
     * @return
     */
    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome user</h1>");
    }

    /**
     * Accessible after login only by admin user
     *
     * @return
     */
    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome admin</h1>");
    }

}
