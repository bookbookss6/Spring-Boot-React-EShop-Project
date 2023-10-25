import { getCookie, removeCookie } from "../Utils/cookie_manager";
import { getUser } from "./getUser";

export async function confirmDeleteUser() {

    const response = window.confirm("탈퇴 하시겠습니까?");
    if(response === true)
    {
        const token = getCookie("token");
        const {username} = await getUser();
        alert("탈퇴가 완료되었습니다.");
       
        fetch('http://localhost:8080/auth/delete', {
            method: 'DELETE',
            headers: {
                Authorization: `Bearer ${token}`
            },
            body:username 
        })
        removeCookie("token");
        window.location.reload();
    }
    
 }

