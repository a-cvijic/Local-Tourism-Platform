package com.ita.repository;

import com.ita.model.Subscription;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SubscriptionRepositoryImpl implements SubscriptionRepository {
    @Override
    public Uni<Subscription> findById(Long id) {
        // Logic
        return null;
    }

    @Override
    public Multi<Subscription> findAll() {
        // Logic
        return null;
    }

    @Override
    public Uni<Subscription> save(Subscription subscription) {
        // Logic
        return null;
    }

    @Override
    public Uni<Void> deleteById(Long id) {
        // Logic
        return null;
    }
}