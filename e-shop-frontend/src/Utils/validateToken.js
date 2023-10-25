import { getCookie } from "./cookie_manager";

export async function validateToken(isTokenValid,setIsTokenValid)
  {

    const token = getCookie("token");
    if(token === undefined)
    {
      return;
    }

    try{
      const response = await fetch('http://localhost:8080/auth/validate-token', {
          method: "POST",
          headers: {
              "Content-Type": "application/json",
          },
          body: JSON.stringify({
            token:token
          })
        });
        
      const {isValid} = await response.json();
      
      if(isValid !== undefined)
      {
        setIsTokenValid(isValid);
      }
          
    }
    catch(error)
    {
        console.log(`error: ${error}`);
    } 

  }