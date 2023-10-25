import { useEffect, useState } from "react";
import "./CreateProduct.css";
import { getUser } from "../User/getUser";
import { saveProduct } from "./product_manager";

function CreateProduct() {
  const [roleName, setRoleName] = useState("");
  const [product, setProduct] = useState({
    title: "",
    category: "",
    price: 0,
    image: "",
  });

  useEffect(() => {
    getUser().then((data) => {
      setRoleName(data.roleName);
    });
  }, []);

  function handleSubmit(e) {
    e.preventDefault();
    saveProduct(product);
  }

  function handleCategoryChange(event) {
    setProduct((prevProduct) => ({
      ...prevProduct,
      category: event.target.value,
    }));
  }

  function handleTitleChange(event) {
    setProduct((prevProduct) => ({
      ...prevProduct,
      title: event.target.value,
    }));
  }

  function handlePriceChange(event) {
    setProduct((prevProduct) => ({
      ...prevProduct,
      price: event.target.value,
    }));
  }

  function handleImageChange(event) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();

      reader.onload = function (e) {
        const base64Data = e.target.result;
        const base64Image = base64Data.split(",")[1];
        setProduct((prevProduct) => ({
          ...prevProduct,
          image: base64Image,
        }));
      };

      reader.readAsDataURL(file);
    }
  }

  return (
    <div className="create-product-container">
      <div className="create-product-title">
        {roleName === "ROLE_ADMIN" ? (
          <h1>상품 등록</h1>
        ) : (
          <h1>상품 등록 권한이 없습니다.</h1>
        )}
      </div>
      {roleName === "ROLE_ADMIN" && (
        <form
          className="create-product-form"
          onSubmit={(e) => {
            handleSubmit(e, product);
          }}
          encType="multipart/form-data"
        >
          <div className="create-product-title-item">
            <label htmlFor="title">제목: </label>
            <input
              type="text"
              id="title"
              name="title"
              onChange={(e) => {
                handleTitleChange(e);
              }}
            />
          </div>
          <div className="create-product-category-item">
            <label htmlFor="category">카테고리: </label>
            <select
              name="category"
              id="category"
              onChange={(e) => {
                handleCategoryChange(e);
              }}
            >
              <option value="FOODS">음식</option>
              <option value="BEAUTY">뷰티</option>
              <option value="KITCHEN">주방용품</option>
              <option value="ELECTRONICS">전자기기</option>
              <option value="BATHROOM">욕실</option>
              <option value="FASHION">패션</option>
            </select>
          </div>
          <div className="create-product-price-item">
            <label htmlFor="price">가격: </label>
            <input
              type="number"
              id="price"
              name="price"
              onChange={(e) => {
                handlePriceChange(e);
              }}
            />
          </div>
          <div className="create-product-image-item">
            <label htmlFor="img">사진 선택: </label>
            <input
              type="file"
              id="img"
              name="img"
              accept="image/*"
              onChange={(e) => {
                handleImageChange(e);
              }}
            />
          </div>
          <div className="create-product-submit-item">
            <input type="submit" value="제출" />
          </div>
        </form>
      )}
    </div>
  );
}

export default CreateProduct;
