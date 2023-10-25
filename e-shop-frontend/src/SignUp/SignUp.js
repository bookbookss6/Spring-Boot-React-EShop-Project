import React from 'react';
import './SignUp.css';
import { useState } from 'react';
import { registerUser } from '../User/registerUser';

function SignUp()
{
    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");
    const [password2,setPassword2] = useState("");
    const [errorMessage,setErrorMessage] =  useState("");

    function handleSubmit(e)
    {
        e.preventDefault();

        const userData = { 
            username:username,
            password:password,
            password2:password2,
            setErrorMessage:setErrorMessage
        };

        registerUser(userData);
    }

    return(
        <div className="signup-container">
            <div className='signup'>
                <form onSubmit={handleSubmit}>
                    <div className='signup-item'>
                        <label>아이디: </label>
                        <input type="text" name="username" value={username} onChange={
                            (e) => setUsername(e.target.value)
                        }/>
                    </div>
                    <div className='signup-item'>
                        <label>비밀번호: </label>
                        <input type="password" name="password" value={password} onChange={
                            (e) => setPassword(e.target.value)
                        }/>
                    </div>
                    <div className='signup-item'>
                        <label>비밀번호 확인: </label>
                        <input type="password" name="password2" value={password2} onChange={
                            (e) => setPassword2(e.target.value)
                        }/>
                    </div>
                    <div className='signup-item'>
                        <input type='submit' value="가입하기"/>
                    </div>
                    <div className='signup-item'>
                        {errorMessage}
                    </div>
                </form>
            </div>
        </div>
    )
}

export default SignUp;