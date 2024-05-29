import React from "react";
import ReactDOM from "react-dom";
import "./index.scss";

// Import components from the micro frontends
const Attractions = React.lazy(() => import("attractions/Attractions"));
const Subscriptions = React.lazy(() => import("subscriptions/Subscriptions"));
const Users = React.lazy(() => import("users/Users"));

const App = () => (
  <div className="mt-10 text-3xl mx-auto max-w-6xl">
    <div>Name: host</div>
    <div>Framework: react</div>
    <div>Language: JavaScript</div>
    <div>CSS: Tailwind</div>
    <React.Suspense fallback="Loading Attractions...">
      <Attractions />
    </React.Suspense>
    <React.Suspense fallback="Loading Subscriptions...">
      <Subscriptions />
    </React.Suspense>
    <React.Suspense fallback="Loading Users...">
      <Users />
    </React.Suspense>
  </div>
);

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById("app")
);
