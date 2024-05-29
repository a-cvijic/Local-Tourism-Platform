import React, { useState, useEffect } from 'react';

const Subscriptions = () => {
  const [subscriptions, setSubscriptions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchSubscriptions = async () => {
      try {
        const response = await fetch('http://localhost:8082/subscriptions');
        const data = await response.json();
        setSubscriptions(data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching subscriptions:', error);
        setLoading(false);
      }
    };
    fetchSubscriptions();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Subscriptions</h1>
      <ul>
        {subscriptions.map(subscription => (
          <li key={subscription.id}>
            <h2>User ID: {subscription.userId}</h2>
            <p>Attraction ID: {subscription.attractionId}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Subscriptions;
