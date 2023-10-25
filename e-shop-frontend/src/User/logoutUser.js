import { removeCookie } from "../Utils/cookie_manager";

export function logoutUser()
{
    fetch('http://localhost:8080/auth/logout')
    .then(data => {
        removeCookie("token");
        window.location.reload();
    });
}