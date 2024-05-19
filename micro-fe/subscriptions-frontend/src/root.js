import React from "react";
import AddAttraction from "./components/AddAttraction";
import AttractionsList from "./components/AttractionsList";
import Subscription from "./components/Subscription";
import axios from "axios";

axios.defaults.baseURL = "http://localhost";

const Root = () => {
  return (
    <div>
      <h1>Hello from Micro Frontends!</h1>
      <AddAttraction />
      <AttractionsList />
      <Subscription />
    </div>
  );
};

export default Root;
