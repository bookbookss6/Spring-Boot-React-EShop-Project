import React, { useEffect, useState } from "react";
import "./Detail.css";
import { useParams } from "react-router-dom";
import { getProductDetail } from "../Product/getProductDetail";
import { saveCartView } from "../CartView/cartview_manager"; 
import { initUsername } from "../User/initUsername";
import { changeUrl } from "../Utils/changeUrl";
import { getUser } from "../User/getUser";
import {deleteProduct} from "../Product/product_manager";

function Detail() {
  const [product, setProduct] = useState({});
  const [username,setUsername] = useState("");
  const [roleName, setRoleName] = useState("");
  let { productId } = useParams();


  useEffect(() => {
    initUsername(setUsername);
    getProductDetail(productId).then(data=>{
        setProduct(data);
    });
    getUser().then((data) => {
      setRoleName(data.roleName);
    });
  }, []);



  return (
    <div className="detail-container">
      {roleName === "ROLE_ADMIN" && (
        <div className="delete-product-button">
          <button onClick={() => {
            deleteProduct(productId);
          }}>x
          </button>
        </div>
      )}
      <div className="product-detail" key={product.id}>
        <div className="product-detail-image">
            <img src={`data:image/jpg;base64, ${product.image}`}/>            
        </div>
        <div className="product-detail-info">
            <p>{product.title}</p>
            <p>{product.price}원</p>
            <button onClick={()=>{changeUrl(`http://localhost:3000/purchase-detail/${productId}`);}}>바로 구매</button>
            <br/>
            <button onClick={()=>{saveCartView(productId,username)}}>장바구니 담기</button>
        </div>
      </div>
    </div>
  );
}

export default Detail;
