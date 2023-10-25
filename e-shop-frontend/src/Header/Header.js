import "./Header.css";
import shop from "../images/shop.png";
import { changeUrl } from "../Utils/changeUrl";
import { useContext } from "react";
import { ValidateTokenContext } from "../User/ValidateTokenContext";
import { confirmDeleteUser } from "../User/confirmDeleteUser";
import { logoutUser } from "../User/logoutUser";

function Header() {
  const isTokenValid = useContext(ValidateTokenContext);

  return (
    <header className="header">
      <div className="header-item-container">
        <div className="header-shop-container">
          <img src={shop} width={50} height={50} />
          <h1>E-SHOP</h1>
        </div>
        <div className="header-button-container">
          <button
            onClick={() => {
              changeUrl("/home/1");
            }}
          >
            홈
          </button>
          <button
            onClick={() => {
              changeUrl("/orderlist");
            }}
          >
            주문내역
          </button>
          <button
            onClick={() => {
              changeUrl("/cartview");
            }}
          >
            장바구니
          </button>
          {isTokenValid ? (
            <button
              onClick={() => {
                logoutUser();
              }}
            >
              로그아웃
            </button>
          ) : (
            <button
              onClick={() => {
                changeUrl("/login");
              }}
            >
              로그인
            </button>
          )}
          {isTokenValid && (
            <p
              className="delete-user-button"
              onClick={() => {
                confirmDeleteUser();
              }}
            >
              탈퇴하기
            </p>
          )}
        </div>
      </div>
    </header>
  );
}

export default Header;
