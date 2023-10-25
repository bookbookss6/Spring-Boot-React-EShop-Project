import { useState } from 'react';
import { changeUrl } from '../Utils/changeUrl';
import './Login.css';
import { loginUser } from '../User/loginUser'; 

function Login()
{

    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");
    const [errorMessage,setErrorMessage] =  useState("");

    function handleSubmit(e)
    {
        e.preventDefault();
        loginUser({username,password,setErrorMessage});
    }

    return(
        <div className="login-container">
            <div className='signin-signup-container'>
                <div className='signin'>
                    <form onSubmit={handleSubmit}>
                        <div className='signin-item'>
                            <label>아이디: </label>
                            <input type="text" name="username" value={username} onChange={
                                (e) => setUsername(e.target.value)
                            }/>
                        </div>
                        <div className='signin-item'>
                            <label>비밀번호: </label>
                            <input type="password" name="password" value={password} onChange={
                                (e) => setPassword(e.target.value)
                            }/>
                        </div>
                        <div className='signin-item'>
                            <input type='submit' value="로그인"/>
                        </div>
                        <div className='signin-item'>
                            {errorMessage}
                        </div>
                    </form>
                </div>
                <div className='signup'>
                    <button className='signup-button' onClick={()=>{changeUrl("/signup");}}>회원가입</button>
                </div>
            </div>
        </div>
    )
}

export default Login;