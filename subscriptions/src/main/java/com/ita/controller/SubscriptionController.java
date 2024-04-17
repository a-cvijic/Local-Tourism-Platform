package com.ita.controller;

import com.ita.model.Subscription;
import com.ita.service.SubscriptionService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Inject
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GET
    public Multi<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GET
    @Path("/{id}")
    public Uni<Subscription> getSubscriptionById(@PathParam("id") Long id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @POST
    public Uni<Subscription> createSubscription(Subscription subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> deleteSubscription(@PathParam("id") Long id) {
        return subscriptionService.deleteSubscription(id);
    }
}
