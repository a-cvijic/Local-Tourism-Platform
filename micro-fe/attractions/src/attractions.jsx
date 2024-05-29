import React, { useState, useEffect } from 'react';

const Attractions = () => {
  const [attractions, setAttractions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchAttractions = async () => {
      try {
        const { get: getAxios } = await __webpack_init_sharing__("default");
        const container = await window["webpackContainerattractions"].get("./axios");
        const axios = getAxios();
        const response = await axios.get('http://localhost:8080/api/attractions');
        console.log('Fetched data:', response.data);
        setAttractions(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching attractions:', error);
        setLoading(false);
      }
    };
    fetchAttractions();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Attractions</h1>
      <ul>
        {attractions.map(attraction => (
          <li key={attraction.id}>
            <h2>{attraction.name}</h2>
            <p>{attraction.description}</p>
            <p>{attraction.location}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Attractions;
