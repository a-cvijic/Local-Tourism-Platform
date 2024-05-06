import React from "react";
import AddAttraction from "./components/AddAttraction";
import AttractionsList from "./components/AttractionsList";

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
