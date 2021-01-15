import React from 'react'
import {login} from '../api/apiCall'
import WeatherForecast from './WeatherForecast';
import {withTranslation} from 'react-i18next'

class LoginPage extends React.Component{
    state={
        username:null,
        password:null,
        error:null,
    }

    onChange = event =>{
        const { name, value } = event.target;
        this.setState({
            [name]:value,
            error:null
        });
    } 

    onClickLogin= async event=>{
        event.preventDefault();
        const {onLoginSuccess} = this.props
        const {username,password}=this.state
        const creds={
            username,
            password
        }
        const {push} = this.props.history
        this.setState({
            error:null,
            
        })
        try{
            const user=await login(creds)
            localStorage.setItem("user", JSON.stringify(user.data))
            onLoginSuccess()
            console.log(user.data)
            push('/wf')
        }catch(apiError){
            this.setState({
                error: apiError.response.data.message
            })
        }   
    }

    render(){
        const buttonEnabled=this.state.username && this.state.password;
        const {t} = this.props
        return(
            <div className="container">
                <form>
                <h1 className="text-center">{t('Login')}</h1>

                <div className="form-group">
                    <label>{t('Username')}</label>
                    <input name="username" className="form-control" onChange={this.onChange}></input>
                </div>

                <div className="form-group">
                    <label>{t('Password')}</label>
                    <input name="password" className="form-control" type="password" onChange={this.onChange}></input>
                </div>
                
                {this.state.error && <div className="alert alert-danger">
                   {this.state.error}
                </div>}
                <button 
                onClick={this.onClickLogin} 
                disabled={!buttonEnabled}
                className="btn btn-primary btn-lg btn-block">
                {this.state.pendingApiCall && <span className="spinner-border spinner-border-sm"></span>}
                    {t('Sign In')}
                </button>
                
                
            </form>

            </div>
        );
    }

}
export default withTranslation()(LoginPage)
