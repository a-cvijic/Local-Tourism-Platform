import React, { Suspense, lazy } from "react";
import ReactDOM from "react-dom";

import "./index.scss";

const Attractions = lazy(() => import("attractions/Attractions"));

const App = () => (
  <div className="mt-10 text-3xl mx-auto max-w-6xl">
    <div>Name: host</div>
    <div>Framework: react</div>
    <div>Language: JavaScript</div>
    <div>CSS: Tailwind</div>
    <Suspense fallback={<div>Loading...</div>}>
      <Attractions />
    </Suspense>
  </div>
);

ReactDOM.render(<App />, document.getElementById("app"));
