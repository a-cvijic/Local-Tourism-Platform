package com.ita.jms;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;

@ApplicationScoped
public class JMSContextProducer {

    @Produces
    @ApplicationScoped
    public JMSContext jmsContext(ConnectionFactory connectionFactory) {
        return connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
    }
}