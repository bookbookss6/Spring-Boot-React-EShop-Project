import { getCookie, removeCookie } from "../Utils/cookie_manager";

export async function getUser() {

    const token = getCookie("token");

    if(token === undefined)
      return "";

    try {
      const response = await fetch(
        `http://localhost:8080/auth/user-info/token=${token}`,
        {
          method:'GET',
        }
      );
      if(response.status === 400 || response.status === 500)
      {
        removeCookie("token");
        return "";
      }
      
      const data = await response.json();
      return data;
      
    } catch (error) {
      console.log(`error: ${error}`);
      return null;
    }
  }