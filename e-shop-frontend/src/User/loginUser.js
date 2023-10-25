import { changeUrl } from "../Utils/changeUrl";
import { setCookie } from "../Utils/cookie_manager";

export async function loginUser(userData)
{

    const {username,password,setErrorMessage} = userData;

    if(username.length < 1)
    {
        setErrorMessage("아이디가 입력되지 않았습니다.");
    }
    else if(password.length < 1)
    {
        setErrorMessage("비밀번호가 입력되지 않았습니다.");
    }
    else{
        try{

            const response = await fetch('http://localhost:8080/auth/login', {
                method: "POST",
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    username: username,
                    password: password,
                })
            });
            


            if(response.status === 200)
            {

                const data  = await response.json();
                const token = data.token;

                setCookie("token",token);
                alert("로그인에 성공했습니다.");
                changeUrl("/home/1");
            }
            if(response.status === 500)
            {
                setErrorMessage("비밀번호가 일치하지 않습니다.");
            }
            if(response.status === 400)
            {
                setErrorMessage("존재하지 않는 아이디 입니다.");
            }
        }
        catch(error)
        {
            console.log(`error: ${error}`);
        } 
    }

    
}