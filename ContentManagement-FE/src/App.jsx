import { useState } from "react";

import "./App.css";
import Navbar from "./componets/Navbar/Navbar";
import { Route, Routes } from "react-router-dom";
import Home from "./componets/Home/Home";
import Dashboard from "./componets/Dashboard/Dashboard";
import Footer from "./componets/Footer/Footer";
import Blog from "./componets/Blog/Blog";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/blog" element={<Blog />} />
      </Routes>
      <Footer />
    </>
  );
}

export default App;
