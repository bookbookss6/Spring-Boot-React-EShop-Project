import { Cookies } from "react-cookie";

const cookies = new Cookies();

export function setCookie(name,value)
{
    const expires = new Date();
    const TWO_WEEKS_TO_HOURS = 336 ;

    // 만료기간 2주 설정
    expires.setHours(TWO_WEEKS_TO_HOURS);

    const option = {
        path:"/",
        expires:expires
    };
    cookies.set(name,value,option);

}

export function getCookie(name)
{
    return cookies.get(name);
}

export function removeCookie(name)
{
    cookies.remove(name,{path:"/"});
}