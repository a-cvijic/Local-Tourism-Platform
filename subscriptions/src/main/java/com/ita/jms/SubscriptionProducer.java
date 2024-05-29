package com.ita.jms;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import io.quarkus.logging.Log;

@ApplicationScoped
public class SubscriptionProducer {

    @Inject
    private JMSContext jmsContext;

    public void sendSubscriptionDetails(String subscriptionDetails) {
        Queue queue = jmsContext.createQueue("subscriptions");
        jmsContext.createProducer().send(queue, subscriptionDetails);
        Log.infof("Subscription details sent: %s", subscriptionDetails);
    }
}
