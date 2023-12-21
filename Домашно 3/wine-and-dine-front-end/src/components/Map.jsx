import "./Map.css"
import { MapContainer, TileLayer, Marker, Popup, useMap } from 'react-leaflet'
import 'leaflet/dist/leaflet.css';
import React, {useContext, useEffect, useState} from "react";
import {MapContext} from "../MapContext";
import L, {Icon} from 'leaflet'

delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
    iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
    iconUrl: require('leaflet/dist/images/marker-icon.png'),
    shadowUrl: require('leaflet/dist/images/marker-shadow.png')
});


function Highlight({mark, map}){
    if(map===null) return;

    if(mark===null) {
        map.setView([41.609, 21.745], 8);
    }else map.setView([mark.latitude, mark.longitude], 13)
}
function LocationMarker() {
    const [position, setPosition] = useState(null);

    const map = useMap();

    useEffect(() => {
        map.locate().on("locationfound", function (e) {
            setPosition(e.latlng);
            map.flyTo(e.latlng, map.getZoom());
            const radius = e.accuracy;
            const circle = L.circle(e.latlng, radius);
            circle.addTo(map);
       });
    }, [map]);

    return position === null ? null : (
        <Marker position={position} icon={new Icon({iconUrl: require('../images/marker.png'), iconAnchor: [24, 48]})}>
            <Popup>
                Your location.
            </Popup>
        </Marker>
    );
}

export default function Map(){
    const {wineries} = useContext(MapContext)
    const {highlighted, setHighlighted} = useContext(MapContext);

    const [map, setMap] = useState(null)
    const [location, setLocation] = useState(null)


    useEffect(() => {
        if(wineries.length===1){
            console.log("yes")
            setHighlighted(wineries[0])
        }else{
            setHighlighted(null)
        }
        console.log("size: ", wineries.length)
    }, [wineries]);

    useEffect(() => {

    }, []);

    return (<MapContainer center={[41.609, 21.745]}
                          zoom={8}
                          ref={setMap}>

            <TileLayer
                attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a>'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {wineries.map(mar=><Marker position={[mar.latitude, mar.longitude]}>
                <Popup>
                    <h3>{mar.name}</h3>
                    <div>{mar.phone}</div>
                    <div>{mar.openHours} - {mar.closeHours}</div>
                </Popup>
            </Marker>)}

            <Highlight mark={highlighted} map={map}/>
        <LocationMarker/>
        </MapContainer>);
}

const MapWithDirections = () => {
    const [startLocation, setStartLocation] = useState([42.139607, 21.7415446]); // Initial start location
    const [endLocation, setEndLocation] = useState([42.6639, 21.1655]); // Initial end location
    const [directions, setDirections] = useState(null);

    const handleGetDirections = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/directions', {
                startLocation,
                endLocation,
            });
            setDirections(response.data);
        } catch (error) {
            console.error('Error getting directions:', error);
        }
    };

    return (
        <div>
            <h2>Map with Directions</h2>
            <button onClick={handleGetDirections}>Get Directions</button>
            <MapContainer center={startLocation} zoom={13} style={{ height: '400px', width: '100%' }}>
                <TileLayer
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                />
                <Marker position={startLocation}>
                    <Popup>Start Location</Popup>
                </Marker>
                <Marker position={endLocation}>
                    <Popup>End Location</Popup>
                </Marker>
                {directions && (
                    <Polyline
                        pathOptions={{ color: 'blue' }}
                        positions={directions} // assuming directions is an array of coordinates
                    />
                )}
            </MapContainer>
        </div>
    );
};

export default MapWithDirections;