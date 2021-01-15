import React, { Component } from 'react';
import logo from '../assets/Logo.png'
import {Link} from 'react-router-dom'
import axios from 'axios'
import {withTranslation} from 'react-i18next'
import {changeLanguage} from '../api/apiCall';


class TopBar extends Component {
    
    onChangeLanguage=language=>{
        const { i18n }=this.props;
        i18n.changeLanguage(language)
        changeLanguage(language)
    }
    render() {
        const {isLoggedIn,onLogoutSuccess,t} = this.props;
        let links=(
            <ul className="navbar-nav ml-auto">
                    <li>
                    <h5><b><Link className="nav-link" to="/login">{t('Login')}</Link></b></h5>
                    </li>
                    <li>
                    <h5><b><Link className="nav-link" to="/register">{t('SignUp')}</Link></b></h5>
                    </li>
            </ul>
        )
        if(isLoggedIn){
            links=(
                <ul className="navbar-nav ml-auto">
                    <li>
                    <h5><b>
                    <Link className="nav-link" to="/login" onClick={onLogoutSuccess}>
                    {t('Logout')}
                    </Link>
                    </b></h5>
                    </li>
                </ul>
            )
        }
        return (
            <div className="shadow-sm bg-light mb-2">
                <div className="text-right">
                    <img src="https://www.countryflags.io/tr/flat/24.png" alt="Turkish Flag" onClick={()=>this.onChangeLanguage('tr')} style={{cursor:'pointer'}}></img>
                    <img src="https://www.countryflags.io/us/flat/24.png" alt="USA Flag" onClick={()=>this.onChangeLanguage('en')} style={{cursor:'pointer'}}></img>
                    <img src="https://www.countryflags.io/de/flat/24.png" alt="Germany Flag" onClick={()=>this.onChangeLanguage('de')} style={{cursor:'pointer'}}></img>

                </div>
                <nav className="navbar navbar-light navbar-expand">
                <Link className="navbar-brand mb-3" to="/"><img src={logo} width="250" alt="LOGO"/>
                </Link>
                {links}
                </nav>
                
                
            </div>
        );
    }
}

export default withTranslation()(TopBar);