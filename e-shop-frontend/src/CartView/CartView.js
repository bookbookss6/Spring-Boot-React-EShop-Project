import "./CartView.css";
import { useState } from "react";
import { useEffect } from "react";
import { initUsername } from "../User/initUsername";
import {
  deleteCartView,
  getCartViews,
  setProductCount,
} from "./cartview_manager";
import { saveOrderLists } from "../OrderList/orderlist_manager";

function CartView() {
  const [cartViews, setCartViews] = useState([]);
  const [username, setUsername] = useState("");

  useEffect(() => {
    initUsername(setUsername);
    getCartViews(username, setCartViews);
  }, [username]);

  return (
    <div className="cartview-container">
      {cartViews.map((cartview) => {
        return (
          <div className="cartview-list" key={cartview.id}>
            <div className="cartview-header">
              <div className="cartview-title">
                <p>{cartview.product.title}</p>
              </div>
              <div className="cartview-delete-button">
                <button
                  onClick={() => {
                    deleteCartView(cartview.product.id, username);
                  }}
                >
                  X
                </button>
              </div>
            </div>
            <div className="cartview-details">
              <img
                src={`data:image/jpg;base64, ${cartview.product.image}`}
                width={100}
                height={100}
              />
              <p>수량: {cartview.productCount}</p>
              <button
                onClick={() => {
                  setProductCount(
                    cartview.productCount+1,
                    cartview.product.id,
                    username
                  );
                }}
              >
                +
              </button>
              <button
                onClick={() => {
                  setProductCount(
                    cartview.productCount-1,
                    cartview.product.id,
                    username
                  );
                }}
              >
                -
              </button>
            </div>
            <div className="cartview-total-price">
              <p>가격: {cartview.product.price * cartview.productCount}원</p>
            </div>
          </div>
        );
      })}
        {
            cartViews.length !== 0 ?
            <div className="purchase-button">
                <button onClick={()=>{saveOrderLists(cartViews,username,true)}}>구매하기</button>
            </div>
            :
            <div className="cartview-notification-card">
                <h1>장바구니에 담긴 상품이 없습니다.</h1>
            </div>
        }
    </div>
  );
}

export default CartView;
