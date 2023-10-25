import { getCookie } from "../Utils/cookie_manager";

export async function saveCartView(productId,username)
{
    
    if(username.length === 0)
    {
        alert("로그인 후에 이용해주세요.");
        return;        
    }
    const token = getCookie("token");
    try{
        const response = await fetch(`http://localhost:8080/cartview/product-id=${productId}/username=${username}`, {
            method: "POST",
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        if(response.status === 200)
        {
            alert("장바구니에 추가되었습니다.");
        }
        if(response.status === 401)
        {
            alert("올바르지 않은 접근 입니다.");
        }
    }
    catch(error)
    {
        console.log(`error: ${error}`);
    }
}

export async function getCartViews(username , setCartViews)
{
      
    if(username.length === 0)
    {
        return;        
    }

    const token = getCookie("token");

    try{
        const response = await fetch(`http://localhost:8080/cartview/username=${username}`, {
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
        setCartViews(data);
    }

    catch(error)
    {
        console.log(`error: ${error}`);
    }

}
export async function deleteCartView(productId,username)
{
    if(username.length === 0)
    {
        return;        
    }

    const token = getCookie("token");

    try{
        await fetch(`http://localhost:8080/cartview/product-id=${productId}/username=${username}`, {
            method: "GET",
            headers: {
                Authorization: `Bearer ${token}`,
            }
        });

        window.location.reload();

    }

    catch(error)
    {
        console.log(`error: ${error}`);
    }
}

export async function deleteCartViews(username)
{
    if(username.length === 0)
    {
        return;        
    }

    const token = getCookie("token");

    try{
        await fetch(`http://localhost:8080/cartview/username=${username}`, {
            method: "DELETE",
            headers: {
                Authorization: `Bearer ${token}`,
            }
        });

        window.location.reload();

    }

    catch(error)
    {
        console.log(`error: ${error}`);
    }
}

export async function setProductCount(productCount,productId,username)
{
    if(username.length === 0)
    {
        return;        
    }

    const token = getCookie("token");

    try{
        await fetch(`http://localhost:8080/cartview/product-id=${productId}/username=${username}/product-count=${productCount}`, {
            method: "POST",
            headers: {
                Authorization: `Bearer ${token}`,
            }
        });

       window.location.reload();
        

    }

    catch(error)
    {
        console.log(`error: ${error}`);
    }
}