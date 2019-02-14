import React, { Component } from 'react';
import {
    Tab,
    TabBarItem,
    Article
} from 'react-weui';

import Home from "./pages/Home";
import Me from "./pages/Me";

class HomePage extends Component {

    render() {
        return (
            <Tab type="tabbar">
                <TabBarItem label="首页">
                    <Home/>
                </TabBarItem>
                <TabBarItem label="我">
                    <Me/>
                </TabBarItem>
            </Tab>

        );
    }
}

export default HomePage;