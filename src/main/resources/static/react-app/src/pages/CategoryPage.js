import React, { Component } from 'react';
import {
    LoadMore,
    Page
} from 'react-weui';

import { GoodList } from '../components/index';

import { baseUrl } from '../config/Constants';


class CategoryPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            coupons: null,
            category: this.props.match.params.id,
            pageNo: 2,
        };

        this.fetchData = this.fetchData.bind(this);
        this.loadMore = this.loadMore.bind(this);
    }

    componentDidMount() {
        this.fetchData();
        console.log("did mount");
    }

    fetchData() {
        let label = this.state.category;
        let url = baseUrl + '/api/category/' + label;
        fetch(url)
            .then(res => res.json())
            .then(data => {
                console.log('category data' + data);
                this.setState({
                    coupons: data,
                })
            })
            .then(e => {
                console.log('some error:' + e)
            });

    };

    loadMore(resolveLoading, finish) {
        let label = this.state.category;
        let url = baseUrl + '/api/category/' + label + "/" + this.state.pageNo;
        fetch(url)
            .then(res => res.json())
            .then(data => {
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
                        //finish();
                    }
                }, 5000);
            })
            .then(e => {
                console.log('fetch data error' + e);
            });
    }

    render() {
        let coupons = this.state.coupons;
        if(coupons !== null) {
            return (
                <Page
                    transition={true}
                    infiniteLoader={true}
                    ptr={false}
                    onLoadMore={this.loadMore}
                >
                    <GoodList coupons={ coupons }/>
                </Page>
            );
        }
        return (
            <div>
                <LoadMore loading>Loading</LoadMore>
            </div>
        );
    }

}

export default CategoryPage;