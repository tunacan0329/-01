package com.artcard.gallery.backend.controller;

import com.artcard.gallery.backend.entity.Member;
import com.artcard.gallery.backend.repository.MemberRepository;
import com.artcard.gallery.backend.service.JwtService;
import com.artcard.gallery.backend.service.JwtServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params, HttpServletResponse res){
        Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

        if(member != null){
            JwtService jwtService = new JwtServiceImpl();
            int id = member.getId();
            String token = jwtService.getToken("id", id);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);

            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
