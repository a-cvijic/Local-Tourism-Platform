package com.ita.repository;

import com.ita.model.Subscription;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface SubscriptionRepository {
    Uni<Subscription> findById(Long id);

    Multi<Subscription> findAll();

    Uni<Subscription> save(Subscription subscription);

    Uni<Void> deleteById(Long id);
}
