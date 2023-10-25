import React, { useEffect, useState } from "react";
import "./Home.css";
import { useParams } from "react-router-dom";
import Products from "../Product/Products";
import SearchBar from "../Search/SearchBar";
import PageContainer from "../Page/PageContainer";
import { sortPage } from "../Utils/sortPage";
import { getProducts } from "../Product/getProducts";
import { getUser } from "../User/getUser";

function Home() {
  const [roleName,setRoleName] = useState("");
  const [products, setProducts] = useState([]);
  const [pages, setPages] = useState({});
  const [category, setCategory] = useState(
    new URLSearchParams(document.location.search).get("category")
  );
  const [title, setTitle] = useState(
    new URLSearchParams(document.location.search).get("title")
  );
  let { page } = useParams();

  useEffect(() => {
    let currentPage = parseInt(page);
    setPages(sortPage(currentPage));

    getUser().then(data=> {setRoleName(data.roleName);});

    if (products.length === 0) {
      
      const productInfo = {
        category:category,
        title:title,
        page:page,
        setCategory:setCategory,
        setTitle:setTitle
      };

      getProducts(productInfo).then((data) => {
        setProducts(data);
      });
    }
  }, []);

  return (
    <div className="home-container">
      <SearchBar />
      <Products products={products} roleName={roleName}/>
      <PageContainer pages={pages} category={category} title={title} />
    </div>
  );
}

export default Home;
