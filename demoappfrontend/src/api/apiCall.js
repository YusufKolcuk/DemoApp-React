import axios from 'axios';

export const signup = (body) =>{
    return axios.post('/register/user',body);
}

export const login = (creds) =>{
    return axios.post('/login/user',{}, {auth:creds});
}

export const changeLanguage = language=>{
    axios.defaults.headers['accept-language'] = language;
}

