package com.example.news_aggregator.preference;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {

    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @GetMapping
    public Preference getPreference() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName(); // 👈 JWT se username

        return preferenceService
                .getByUsername(username)
                .orElseGet(() -> {
                    Preference p = new Preference();
                    p.setUsername(username);
                    p.setCategory("general");
                    p.setCountry("in");
                    p.setLanguage("en");
                    return preferenceService.save(p);
                });
    }

    @PostMapping
    public Preference savePreference(@RequestBody Preference preference) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return preferenceService.saveOrUpdate(username, preference);
    }
}
