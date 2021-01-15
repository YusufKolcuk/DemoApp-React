import React, { Component } from 'react';
import axios from 'axios';
import MaterialTable from 'material-table';
import { Button } from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';
import {withTranslation} from 'react-i18next'




class Table extends Component {
  constructor(){
    super()
    this.state={
        cities:[],
          column: [
            { title: 'Id'      , field: 'id'       },
            { title: 'Name'    , field: 'name'     },
            { title: 'Temp'    , field: 'temp'     },
            { title: 'TempMin' , field: 'tempMin'  },
            { title: 'TempMax' , field: 'tempMax'  },
            { title: 'Pressure', field: 'pressure' },
            { title: 'Humidity', field: 'humidity' }
          
        ],
        user:JSON.parse(localStorage.getItem("user"))
    }
  }
  
  


  componentDidMount(){
    const {user,column} = this.state
    axios.post('/showtable').then(response=>{
        this.setState({cities:response.data.map(value=>({
          id:value.id,
          name:value.name,
          temp:value.temp,
          tempMin:value.tempMin,
          tempMax:value.tempMax,
          pressure:value.pressure,
          humidity:value.humidity,
          action:user.isAdmin? <Button
          onClick={
            (event)=>{
              event.preventDefault();
              axios.get('/table/delete/'+value.id);
              window.location.reload(false);
            }
          }
          ><DeleteIcon/></Button>:null
        }))})
    }); 
    if(user.isAdmin){
      column.push({
        title: 'Action',
        field: 'action',
      })

      this.setState({
        column
      })
    }


    
  };

  

  render() {
    const {cities,column}=this.state
    return (
      <div>

      <MaterialTable
          title="Cities"
          columns={column}
          data={cities}
          
          />
      </div>
    );
  }
}

export default withTranslation()(Table)
