package com.ita.service;

import com.ita.model.Subscription;
import com.ita.repository.SubscriptionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Inject
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.listAll();
    }

    @Transactional
    public Subscription createSubscription(Subscription subscription) {
        subscriptionRepository.persist(subscription);
        return subscription;
    }

    @Transactional
    public Subscription updateSubscription(Subscription subscription) {
        return subscriptionRepository.getEntityManager().merge(subscription);
    }

    @Transactional
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
