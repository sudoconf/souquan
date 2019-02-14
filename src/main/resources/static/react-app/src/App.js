import React, { Component } from 'react';
import { HashRouter as Router, Route, Switch } from "react-router-dom";
import CacheRoute, { CacheSwitch } from 'react-router-cache-route';

import {
    Page
} from 'react-weui';

import Home from './pages/Home';
import Detail from './pages/Detail';
import CategoryPage from './pages/CategoryPage';
import SearchResult from "./pages/SearchResult";
//import './App.css';

//import styles
import 'weui';
import 'react-weui/build/packages/react-weui.css';



class App extends Component {

    componentDidMount() {
        console.log("APP did mount");
    }

    render() {
        return (
            <Router>
                <CacheSwitch>
                    <CacheRoute path="/" exact component={ Home } />
                    <CacheRoute path="/detail/:id" exact component={ Detail } />
                    <CacheRoute path="/category/:id" exact component={ CategoryPage }/>
                    <CacheRoute path="/search-result/:q" exact component={ SearchResult }/>
                </CacheSwitch>
            </Router>
        );
    }
}

export default App;
