import { deleteCartViews } from "../CartView/cartview_manager";
import { getCookie } from "../Utils/cookie_manager";
import { changeUrl } from '../Utils/changeUrl';

export async function saveOrderList(productId,productCount,username)
{
    const response = window.confirm("구매 하시겠습니까?");
    if(response === true)
    {
        const token = getCookie("token");
       
        try{
            await fetch(`http://localhost:8080/order-list/save/product-id=${productId}/product-count=${productCount}/username=${username}`, {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${token}`
                },

            })

            alert("구매가 완료되었습니다.");
            changeUrl("http://localhost:3000/orderlist");
            
        }
        catch(error)
        {
            console.log(`error: ${error}`);
        }
        
    }
}


export async function saveOrderLists(cartViews, username, deleteCartView = true)
{
    const response = window.confirm("구매 하시겠습니까?");
    if(response === true)
    {
        const token = getCookie("token");

        try{
            await fetch('http://localhost:8080/order-list/save-all', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(
                    cartViews
                ) 
            })

            if(deleteCartView === true)
            {
                deleteCartViews(username);
            }
            alert("구매가 완료되었습니다.");
            changeUrl("http://localhost:3000/orderlist");
        }
        catch(error)
        {
            console.log(`error: ${error}`);
        }
        
    }
}

export async function getOrderLists(username , setOrderLists)
{
      
    if(username.length === 0)
    {
        return ;        
    }

    const token = getCookie("token");

    try{
        const response = await fetch(`http://localhost:8080/order-list/username=${username}`, {
            method: "GET",
            headers: {
                Authorization: `Bearer ${token}`,
            }
        });
        if(response.status === 401)
        {
            return;
        }

        const data = await response.json();  
        setOrderLists(data);
    }

    catch(error)
    {
        console.log(`error: ${error}`);
    }

}