import "./Footer.css";
import facebook from "../images/facebook.png";
import instagram from "../images/instagram.png";
import tiktok from "../images/tiktok.png";
import twitter from "../images/twitter.png";

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-item-container">
        <div className="copyright-text">
          <h1>All Right Reserved.</h1>
        </div>
        <div className="social-media-item-container">
          <img src={facebook} width={50} height={50} />
          <img src={instagram} width={50} height={50} />
          <img src={tiktok} width={50} height={50} />
          <img src={twitter} width={50} height={50} />
        </div>
      </div>
    </footer>
  );
}

export default Footer;
