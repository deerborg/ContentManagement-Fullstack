import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <>
      <section id="navbar-section" className="navbar-section">
        <nav className="navbar">
          <li>
            <div>LOGO</div>
          </li>

          <li>
            <Link to="/">Home</Link>
          </li>

          <li>
            <Link to="/blog">Blog</Link>
          </li>

          <li>
            <Link to="/dash">Other</Link>
          </li>

          <li>
            <Link to="/dash">Login</Link>
          </li>
        </nav>
      </section>
    </>
  );
};
export default Navbar;
