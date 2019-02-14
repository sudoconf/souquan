import React, { Component } from 'react';
import { Page } from 'react-weui';
import {
    MySearchBar,
    NavGrid,
    GoodList
} from '../components/index';

import { baseUrl } from '../config/Constants';

class Home extends Component{

    constructor(props) {
        super(props);
        this.state = {
            categories: null,
            coupons: null,
            pageNo: 2,
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.loadMore = this.loadMore.bind(this);
    }

    handleSubmit(path) {
        let history = this.props.history;
        history.push(path);
    }

    componentDidMount() {
        console.log("did mount");
        let url = baseUrl + '/api/';
        fetch(url).then(res => res.json())
            .then(data => {
                let categories = data.category;
                let coupons = data.coupons;
                console.log(categories);
                console.log(coupons);
                this.setState({
                    categories: categories,
                    coupons: coupons,
                })
            })
            .then(e => {
                console.log("fetch data error", e);
            });
    }


    loadMore(resolveLoading, finish) {
        let url = baseUrl + '/api/more/' + this.state.pageNo;
        fetch(url).then(res => res.json())
            .then(data => {
                console.log('enter fetch');
                let old = this.state.coupons;
                let newCoupons = old.concat(data);
                let curNo = this.state.pageNo + 1;
                this.setState({
                    coupons: newCoupons,
                    pageNo: curNo,
                });
                setTimeout(() => {
                    resolveLoading();
                    if (curNo > 4999) {
                        console.log('stop fetching new data');
                        finish();
                    }
                }, 5000);
            })
            .then(e => {
                console.log("error" + e);
            });
    }

    render() {
        let coupons = this.state.coupons;
        let categories = this.state.categories;
        console.log('pageNo:' + this.state.pageNo);
        console.log("new state of coupons:" + coupons);
        if (coupons !== null && categories !== null) {
            return (
                <Page
                    transition={true}
                    infiniteLoader={true}
                    ptr={false}
                    onLoadMore={ this.loadMore }
                >
                    <MySearchBar onSubmit={ this.handleSubmit }/>
                    <NavGrid categories={ categories } />
                    <GoodList coupons={ coupons }/>
                </Page>
            );
        }
        return (
            <div>Loadding...</div>
        )
    }
}

export default Home;