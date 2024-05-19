import React, { useState, useEffect } from "react";
import axios from "axios";

function Subscription() {
  const [subscriptions, setSubscriptions] = useState([]);
  const [email, setEmail] = useState("");
  const [responseMessage, setResponseMessage] = useState("");

  useEffect(() => {
    fetchSubscriptions();
  }, []);

  const fetchSubscriptions = () => {
    axios
      .get("/subscriptions/api/subscriptions")
      .then((response) => setSubscriptions(response.data))
      .catch((error) => console.error("Error fetching subscriptions", error));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post("/subscriptions/api/subscriptions", { email })
      .then((response) => {
        setResponseMessage("Subscription successful!");
        fetchSubscriptions();
        setEmail("");
      })
      .catch((error) => {
        console.error("Error subscribing", error);
        setResponseMessage("Subscription failed.");
      });
  };

  return (
    <div>
      <h1>Subscriptions</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <button type="submit">Subscribe</button>
        {responseMessage && <p>{responseMessage}</p>}
      </form>
      <ul>
        {subscriptions.map((subscription) => (
          <li key={subscription.id}>{subscription.email}</li>
        ))}
      </ul>
    </div>
  );
}

export default Subscription;
