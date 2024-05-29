import React from "react";
import AddAttraction from "./components/AddAttraction";
import AttractionsList from "./components/AttractionsList";
import axios from "axios";

axios.defaults.baseURL = "http://localhost";

const Root = () => {
  return (
    <div>
      <h1>Hello from Attractions!</h1>
      <AddAttraction />
      <AttractionsList />
    </div>
  );
};

export default Root;
