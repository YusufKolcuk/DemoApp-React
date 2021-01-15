import React, { Component } from 'react';
import Table from '../components/Table'
import MapContainer from '../components/MapContainer'
import excel from '../assets/excel.ico'
import pdf from '../assets/pdf.ico'
import {Link} from 'react-router-dom'
import axios from 'axios';
import '../css/map.css'
import { Button } from '@material-ui/core';
import IconButton from '@material-ui/core/IconButton';
import AddIcon from '@material-ui/icons/Add';



class WeatherForecast extends Component {
    
    constructor(){
        super()
        this.state={
            cities:[],
            cityId:null,
            user:JSON.parse(localStorage.getItem("user"))
        }
        console.log(this.state.user)
    }
    
    onClickExc= async event=>{
         axios.post('/generate/excel.xls', null,
            {
                headers:
                {
                    'Content-Disposition': "attachment; filename=Report.xls",
                    'Content-Type': 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
                },
                responseType: 'arraybuffer',
            }
        ).then((response) => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'Report.xls');
            document.body.appendChild(link);
            link.click();
        })
            .catch((error) => console.log(error));
    }

    onClickPdf(){
        axios.post('/table/pdf', null,
        {
            headers:
            {
                'Content-Disposition': "attachment; filename=Report.pdf",
                'Content-Type': 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
            },
            responseType: 'arraybuffer',
        }
    ).then((response) => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'Report.pdf');
        document.body.appendChild(link);
        link.click();
    })
        .catch((error) => console.log(error));
    }

   componentDidMount(){
    document.body.style.overflowX = 'hidden';

        axios.post('/showtable').then(response=>{
            this.setState({cities:response.data})
            console.log(this.state.cities)
        });       
    };

    onChange = event =>{
        const { name, value } = event.target;
        this.setState({
            [name]:value
        });
    } 

    onClickCityId=event=>{
        event.preventDefault();
        const {cityId}=this.state
        axios.post('/table/add/'+cityId);
        if(cityId!=null){
            window.location.reload(false);
        }    
    }

    render() {
        const {cities,user} =this.state;
        return (       
            <div>
                 {user.isAdmin && (
                     <div>
                     <input type="number" name="cityId" onChange={this.onChange} className="addCity"/>
                     <IconButton aria-label="add"  onClick={this.onClickCityId}>
                         <AddIcon fontSize="small" />
                     </IconButton>
                     </div>
                 )
                 }
                <div className="text-right ml-auto">
                    <a href="#">
                     <img src={excel} onClick={this.onClickExc} width="40" alt="LOGO"/>
                     </a>
                    {" "}
                    <a href="#"><img onClick={this.onClickPdf} src={pdf} width="40" alt="LOGO"/></a>      
                </div>
                <Table cit={cities}/>
                <div className="frame">
                    <MapContainer/>
                </div>
            </div>
        );
    }
}

export default WeatherForecast;