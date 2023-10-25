import "./OrderList.css";
import { useState } from "react";
import { useEffect } from "react";
import { initUsername } from "../User/initUsername";
import { getOrderLists } from "./orderlist_manager";

function OrderList() {
  const [orderlists, setOrderLists] = useState([]);
  const [username, setUsername] = useState("");

  useEffect(() => {
    initUsername(setUsername);
    getOrderLists(username, setOrderLists);
  }, [username]);

  return (
    <div className="orderlist-container">
      {orderlists.map((orderlist) => {
        return (
          <div className="orderlist-list" key={orderlist.id}>
            <div className="orderlist-header">
              <div className="orderlist-title">
                <p>{orderlist.product.title}</p>
              </div>
            </div>
            <div className="orderlist-details">
              <img
                src={`data:image/jpg;base64, ${orderlist.product.image}`}
                width={100}
                height={100}
              />
              <p>수량: {orderlist.productCount}</p>
            </div>
            <div className="orderlist-total-price">
              <p>가격: {orderlist.product.price * orderlist.productCount}원</p>
            </div>
          </div>
        );
      })}
        {
            orderlists.length === 0 &&
            <div className="orderlist-notification-card">
                <h1>주문된 상품이 없습니다.</h1>
            </div>
        }
    </div>
  );
}

export default OrderList;
