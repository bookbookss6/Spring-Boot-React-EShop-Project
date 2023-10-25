import { changeUrl } from "../Utils/changeUrl";
import "./Products.css";

function Products({ products, roleName }) {
  return (
    <div className="product-container">
      {roleName === "ROLE_ADMIN" && (
        <div className="create-product-button">
          <button onClick={() => {
            changeUrl('http://localhost:3000/create-product');
          }}>+</button>
        </div>
      )}
      <div className="product-item-container">
        {products.map((product) => {
          return (
            <div className="product" key={product.id}>
              <img
                key={product.id}
                src={`data:image/jpg;base64, ${product.image}`}
                width={'200px'}
                height={'250px'}
                onClick={() => {
                  changeUrl(`/detail/${product.id}`);
                }}
              />
              <p>{product.title}</p>
              <p>{product.price}원</p>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Products;
