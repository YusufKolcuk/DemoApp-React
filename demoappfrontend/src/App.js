import React from 'react';
import RegisterPage from './pages/RegisterPage'
import LoginPage from './pages/LoginPage'
import HomePage from './pages/HomePage'
import WeatherForecast from './pages/WeatherForecast'
import {BrowserRouter, Route, Redirect, Switch, HashRouter} from 'react-router-dom'
import TopBar from './components/TopBar'


class App extends React.Component{
  state={
    isLoggedIn: false,
  }
  onLoginSuccess = () =>{
    this.setState({
      isLoggedIn:true
    })
  }
  onLogoutSuccess = () =>{
    localStorage.removeItem("user")
    this.setState({
      isLoggedIn:false
    })
  }
  componentDidMount(){
    if(localStorage.getItem("user")){
      this.setState({
        isLoggedIn:true
      })
    }
  }

  render(){
   
    const {isLoggedIn,fakeUser}=this.state;
    return (
      <div>
        <BrowserRouter>
          <TopBar isLoggedIn={isLoggedIn} onLogoutSuccess={this.onLogoutSuccess }/>
          <Switch>
            <Route exact path="/" component={HomePage}/>

            {!isLoggedIn&&(<Route  path="/login" component={(props)=>{
              return <LoginPage {...props} onLoginSuccess={this.onLoginSuccess}/>;
            }}/>)}
            <Route  path="/register" component={RegisterPage}/>
            <Route  path="/wf" component={WeatherForecast}/>
          <Redirect to="/" />
          </Switch>
          
        
        </BrowserRouter>
      </div>
      );
  }
  
}

export default App;
