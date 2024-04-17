package com.ita.controller;

import com.ita.model.Subscription;
import com.ita.service.SubscriptionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

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
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GET
    @Path("/{id}")
    public Response getSubscriptionById(@PathParam("id") Long id) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription != null) {
            return Response.ok(subscription).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response createSubscription(Subscription subscriptionData) {
        Subscription subscription = new Subscription();
        subscription.setUserId(subscriptionData.getUserId());
        subscription.setAttractionId(subscriptionData.getAttractionId());
        Subscription createdSubscription = subscriptionService.createSubscription(subscription);
        return Response.created(URI.create("/subscriptions/" + createdSubscription.id)).entity(createdSubscription)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateSubscription(@PathParam("id") Long id, Subscription subscriptionData) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        subscription.setUserId(subscriptionData.getUserId());
        subscription.setAttractionId(subscriptionData.getAttractionId());
        subscriptionService.updateSubscription(subscription);
        return Response.ok(subscription).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSubscription(@PathParam("id") Long id) {
        subscriptionService.deleteSubscription(id);
        return Response.noContent().build();
    }
}
