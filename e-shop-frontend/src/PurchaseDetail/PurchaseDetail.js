import { useEffect, useState } from "react";
import "./PurchaseDetail.css";
import { useParams } from "react-router-dom";
import { initUsername } from "../User/initUsername";
import { getProductDetail } from "../Product/getProductDetail";
import { saveOrderList } from "../OrderList/orderlist_manager";

function PurchaseDetail() {
  const [product, setProduct] = useState({});
  const [productCount, setProductCount] = useState(1);
  const [username, setUsername] = useState("");
  let { productId } = useParams();

  useEffect(() => {
    initUsername(setUsername);
    getProductDetail(productId).then((data) => {
      setProduct(data);
    });
  }, []);

  return (
    <div className="purchase-detail-container">
      <div className="product-detail" key={product.id}>
        <div className="product-detail-image">
          <img src={`data:image/jpg;base64, ${product.image}`} />
        </div>
        <div className="product-detail-info">
          <p>{product.title}</p>
          <p>{product.price}원</p>
          <p>수량 : {productCount}개</p>
        </div>
        <div className="product-count-button">
          <button
            onClick={() => {
              setProductCount((count) => count + 1);
            }}
          >
            +
          </button>
          <button
            onClick={() => {
              setProductCount((count) => {
                if(count === 1 )
                    return 1;
                return count - 1;
              });
            }}
          >
            -
          </button>
        </div>
        <div className="purchase-button">
          <button
            onClick={() => {
              saveOrderList(product.id, productCount, username);
            }}
          >
            구매하기
          </button>
        </div>
      </div>
    </div>
  );
}

export default PurchaseDetail;
