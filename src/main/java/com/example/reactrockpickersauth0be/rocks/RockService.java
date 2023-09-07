package com.example.reactrockpickersauth0be.rocks;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RockService {
    private RockRepository repository;

    public RockService(RockRepository repository) {
        this.repository = repository;
        repository.saveAll(defaultRocks());
    }

    public static List<Rock> defaultRocks() {
        return List.of(
                new Rock("Obsidian", false),
                new Rock("Basalt", false),
                new Rock("Gneiss", true),
                new Rock("Quartz", false),
                new Rock("Marble", true)
        );
    }

    public Rock saveRock(Rock rock) {
        this.repository.save(rock);
        return rock;
    }

    public List<Rock> processRocksBasedOnUserRole() throws AccessDeniedException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities();

        System.out.println(authorities);

        boolean isAdmin = authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));

        boolean isUser = authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("USER"));

        if (isAdmin) {
            return getAllRocks();
        } else if (isUser) {
            return getAllRocksForUser();
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }

    public List<Rock> getAllRocks() {
        // make say something about admin
        List<Rock> list = new ArrayList<>();
        Iterable<Rock> rocks = repository.findAll();
        rocks.forEach(list::add);
        return list;
    }

    public List<Rock> getAllRocksForUser() {
        List<Rock> list = new ArrayList<>();
        Iterable<Rock> rocks = repository.findBySecretFalse();
        rocks.forEach(list::add);
        return list;
    }
}
