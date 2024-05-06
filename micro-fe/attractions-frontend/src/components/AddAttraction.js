import React, { useState } from "react";
import axios from "axios";

function AddAttraction() {
  const [name, setName] = useState("");
  const [location, setLocation] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post("/api/attractions", { name, location })
      .then((response) => {
        console.log("Attraction added", response.data);
        setName("");
        setLocation("");
      })
      .catch((error) => console.error("Error adding attraction", error));
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <input
        type="text"
        placeholder="Location"
        value={location}
        onChange={(e) => setLocation(e.target.value)}
      />
      <button type="submit">Add Attraction</button>
    </form>
  );
}

export default AddAttraction;
