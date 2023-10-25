import React, { useEffect, useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import Home from "./Home/Home";
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import Detail from "./Detail/Detail";
import NotFound404 from "./NotFound/NotFound404";
import Login from "./Login/Login";
import SignUp from "./SignUp/SignUp";
import { ValidateTokenContext } from "./User/ValidateTokenContext";
import { CookiesProvider } from "react-cookie";
import { validateToken } from "./Utils/validateToken";
import CartView from "./CartView/CartView";
import OrderList from "./OrderList/OrderList";
import PurchaseDetail from "./PurchaseDetail/PurchaseDetail";
import CreateProduct from "./Product/CreateProduct";

function App() {

  const [isTokenValid , setIsTokenValid] = useState(false);

  useEffect(()=>{
    validateToken(isTokenValid,setIsTokenValid);
  });


  return (
    <BrowserRouter>
      <CookiesProvider>
        <ValidateTokenContext.Provider value={isTokenValid} >
          <Header />
            <Routes>
              <Route path="/home/:page" Component={Home}/>
              <Route path="/detail/:productId" Component={Detail}/>
              <Route path="/cartview" Component={CartView}/>
              <Route path="/orderlist" Component={OrderList}/>
              <Route path="/purchase-detail/:productId" Component={PurchaseDetail}/>
              <Route path="/create-product" Component={CreateProduct}/>
              <Route path="/login" Component={Login}/>
              <Route path="/signup" Component={SignUp}/>
              <Route path="*" Component={NotFound404}/>
            </Routes>
          <Footer />
        </ValidateTokenContext.Provider>
      </CookiesProvider>
    </BrowserRouter>
  );

}

export default App;
