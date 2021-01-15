import React, { Component } from 'react';
import { Map, GoogleApiWrapper, Marker } from 'google-maps-react';
import axios from 'axios';
import Clouds from '../assets/wfimg.ico'
import Rain from '../assets/rainn.ico'
import Sunny from '../assets/sunny.ico'

const icons = {
  Clouds:       Clouds, 
  Clear:        Sunny,
  Rain:         Rain,
  Snow:         Rain,
  Drizzle:      Rain,
  Thunderstorm: Rain,
  Mist:         Rain,
  Smoke:        Rain,
  Haze:         Rain,
  Ash:          Rain,
  Dust:         Rain,
  Fog:          Rain,
  Sand:         Rain,
  Squall:       Rain,
  Dust:         Rain
};
class MapContainer extends Component {
    
    constructor(props) {
      super(props);
      this.state = {
        stores: []
      }
    }

    componentDidMount(){
      axios.post('/showtable').then(response=>{
          this.setState({stores:response.data.map(value=>({
            latitude:value.lat,
            longitude:value.lon,
            desc:value.desc
          }))})
          console.log(this.state.cities)
      });     
    };

    displayMarkers = () => {
      return this.state.stores.map((store, index) => {
        return <Marker key={index} id={index} position={{
         lat: store.latitude,
         lng: store.longitude,
         type:store.desc
       }}
       icon={{url:icons[this.state.stores[index].desc],scaledSize:new this.props.google.maps.Size(50,50)}}
       onClick={() => console.log(this.state.stores[0].desc)} />
      })
    }
  
    render() {
      
        var mapStyles = {
            width: '1150px',
            height: '750px',
          };    
      return (
        <Map
          google={this.props.google}
          zoom={4}
          style={mapStyles}
          initialCenter={{ lat: 51.260197, lng: 4.402771}} 
          >
          {this.displayMarkers()}
        </Map>
      );
    }
  }
  export default GoogleApiWrapper({
    apiKey: 'AIzaSyD_n_0mOPCs7DxlW4t6rzSiD0KyUXQktVY'
  })(MapContainer);