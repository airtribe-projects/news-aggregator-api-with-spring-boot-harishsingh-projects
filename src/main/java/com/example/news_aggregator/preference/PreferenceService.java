package com.example.news_aggregator.preference;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreferenceService {

    private final PreferenceRepository repository;

    public PreferenceService(PreferenceRepository repository) {
        this.repository = repository;
    }

    public Optional<Preference> getByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Preference save(Preference preference) {
        return repository.save(preference);
    }

    public Preference saveOrUpdate(String username, Preference incoming) {

        Preference pref = repository.findByUsername(username)
                .orElse(new Preference());

        pref.setUsername(username);
        pref.setCountry(incoming.getCountry());
        pref.setCategory(incoming.getCategory());
        pref.setLanguage(incoming.getLanguage());

        return repository.save(pref);
    }
}
