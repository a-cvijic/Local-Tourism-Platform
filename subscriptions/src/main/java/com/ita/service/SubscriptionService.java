package com.ita.service;

import com.ita.model.Subscription;
import com.ita.repository.SubscriptionRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Uni<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public Multi<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Uni<Subscription> createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Uni<Void> deleteSubscription(Long id) {
        return subscriptionRepository.deleteById(id);
    }
}
