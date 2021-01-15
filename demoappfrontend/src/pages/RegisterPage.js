import React from 'react'
import {signup} from '../api/apiCall';
import {withTranslation} from 'react-i18next'
import { Language } from '@material-ui/icons';


class RegisterPage extends React.Component{

    state={   
        username:null,
        displayname:null,
        email:null,
        password:null,
        passwordrepeat:null,
        isAdmin:null,
        pendingApiCall: false,        
        errors:{

        }
    }

    onChange = event =>{
        const {t} =this.props
        const { name, value } = event.target;
        //js de bir state'in kopyasını üç nokta ile alabiliyoruz.
        const errors={...this.state.errors}
        errors[name]=undefined;
        if (name === 'password' || name === 'passwordrepeat') {
            if (name === 'password' && value !== this.state.passwordrepeat) {
                errors.passwordrepeat = t('Password mismatch');
            }
            else if (name === 'passwordrepeat' && value !== this.state.password) {
                errors.passwordrepeat = t('Password mismatch');
            }
            else {
                errors.passwordrepeat = undefined;
            }
        }


        this.setState({
            
            [name]:value,
            errors
        });
    } 

    onClickSignup =async event =>{
        event.preventDefault();
        const body={
            username:this.state.username,
            displayname:this.state.displayname,
            email:this.state.email,
            password:this.state.password,
            isAdmin:this.isAdmin
        };

        this.setState({pendingApiCall:true})

        try{
            const response = await signup(body);
        }catch(error){ 
            this.setState({errors : error.response.data.validationErrors});
        }
        this.setState({pendingApiCall:false})
        /*
        signup(body)
        .then(function(response){
            this.setState({pendingApiCall:false})
        }).catch(error=>{
            this.setState({pendingApiCall:false})
        });
        */
    }
      
    render() {
        const eVal=/^[a-zA-Z0-9]+@+[a-zA-Z0-9]+.+[A-z]/;
        const {t} =this.props
        return(
            <div className="container">
                <form>
                <h1 className="text-center">{t('SignUp')}</h1>
                <div className="form-group">
                    <label>{t('Username')}</label>
                    <input className={this.state.errors.username?"form-control is-invalid":"form-control"} name="username" onChange={this.onChange}></input>
                    <div className="invalid-feedback">{this.state.errors.username}</div>
                
                </div>
                <div className="form-group">
                    <label>{t('DisplayName')}</label>
                    <input className={this.state.errors.displayname?"form-control is-invalid":"form-control"} name="displayname" onChange={this.onChange}></input>
                    <div className="invalid-feedback">{this.state.errors.displayname}</div>

                </div>
                <div className="form-group">
                    <label>{t('Email')}</label>
                    <input type='email' className={this.state.errors.email?"form-control is-invalid":"form-control"} name="email" onChange={this.onChange}/>
                    <div className="invalid-feedback">{this.state.errors.email}</div>
                </div>
                <div className="form-group">
                    <label>{t('Password')}</label>
                    <input className={this.state.errors.password?"form-control is-invalid":"form-control"} name="password" type="password" onChange={this.onChange}></input>
                    <div className="invalid-feedback">{this.state.errors.password}</div>
                </div>
                <div className="form-group">
                    <label>{t('Password Repeat')}</label>
                    <input className={this.state.errors.passwordrepeat?"form-control is-invalid":"form-control"} name="passwordrepeat" type="password" onChange={this.onChange}></input>
                    <div className="invalid-feedback">{this.state.errors.passwordrepeat}</div>
                </div>
                <button 
                onClick={this.onClickSignup} 
                disabled={this.state.pendingApiCall || this.state.errors.passwordrepeat!==undefined}
                className="btn btn-primary btn-lg btn-block">
                {this.state.pendingApiCall && <span className="spinner-border spinner-border-sm"></span>}
                    {t('SignUp')}
                </button>
                
            </form>
            </div>
            
        ); 
    }
}

export default withTranslation()(RegisterPage)