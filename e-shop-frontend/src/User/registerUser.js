import { changeUrl } from '../Utils/changeUrl';

export async function registerUser(userData)
{

    const {username,password,password2,setErrorMessage} = userData;

    if(username.length < 1)
    {
        setErrorMessage("아이디가 입력되지 않았습니다.");
    }
    else if(password.length < 1)
    {
        setErrorMessage("비밀번호가 입력되지 않았습니다.");
    }
    else if(password !== password2)
    {
        setErrorMessage("비밀번호가 일치하지 않습니다.");
    }
    else{
        try{

            const response = await fetch('http://localhost:8080/auth/register', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    username: username,
                    password: password,
                })
            });
            
            if(response.status === 201)
            {
                alert("회원가입에 완료했습니다.");
                changeUrl("/home/1");
            }
            if(response.status === 400)
            {
                setErrorMessage("이미 존재하는 유저가 있습니다.");
            }
        }
        catch(error)
        {
            console.log(`error: ${error}`);
        } 
    }
    
}