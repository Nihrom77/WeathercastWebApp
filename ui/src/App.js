import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListWeatherComponent from "./component/ListWeatherComponent";

function App() {
    return (
        <div className="container">
            <Router>
                <div className="col-md-6">
                    <h2 className="text-center" style={style}>Погодные сводки за период</h2>
                    <Switch>
                        <Route path="/" exact component={ListWeatherComponent}/>
                    </Switch>
                </div>
            </Router>
        </div>
    );
}

const style = {
    color: 'red',
    margin: '10px'
}

export default App;
