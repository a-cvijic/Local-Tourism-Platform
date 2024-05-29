package com.ita.repository;

import com.ita.model.Subscription;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface SubscriptionRepository extends PanacheRepository<Subscription> {
}