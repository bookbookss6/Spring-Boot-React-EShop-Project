import { getCookie } from "../Utils/cookie_manager";
import { changeUrl} from "../Utils/changeUrl";

export async function saveProduct(product)
{
    const response = window.confirm("등록 하시겠습니까?");
    if(response === true)
    {
        const token = getCookie("token");

        try{
            const response = await fetch('http://localhost:8080/products/save', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(
                    product
                ) 
            })
            if(response.status === 400)
            {
                alert("오류로 인해 상품이 등록되지 않았습니다");
                return;
            }

            alert("등록이 완료되었습니다.");
            changeUrl("http://localhost:3000/home/1");
        }
        catch(error)
        {
            console.log(`error: ${error}`);
        }
        
    }
}

export async function deleteProduct(productId) {

    const response = window.confirm("삭제 하시겠습니까?");
    if(response === true)
    {
        const token = getCookie("token");
       
        try{
            const response = fetch(`http://localhost:8080/products/delete?id=${productId}`, {
            method: 'DELETE',
            headers: {
                Authorization: `Bearer ${token}`
            },
        })

            alert("삭제가 완료되었습니다.");
            changeUrl("http://localhost:3000/home/1");
        }
        catch(error)
        {
            console.log(`error: ${error}`);
        }

    }
    
 }
