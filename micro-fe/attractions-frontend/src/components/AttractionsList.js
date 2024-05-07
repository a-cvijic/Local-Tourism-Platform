import React, { useEffect, useState } from "react";
import axios from "axios";

function AttractionsList() {
  const [attractions, setAttractions] = useState([]);

  useEffect(() => {
    axios
      .get("/api/attractions")
      .then((response) => setAttractions(response.data))
      .catch((error) => console.error("Error fetching attractions", error));
  }, []);

  return (
    <div>
      <h1>Attractions</h1>
      <ul>
        {attractions.map((attraction) => (
          <li key={attraction.id}>
            {attraction.name} - {attraction.location}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default AttractionsList;
