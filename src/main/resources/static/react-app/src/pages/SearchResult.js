import React, { Component } from 'react';
import {
    Page,
    SearchBar
} from 'react-weui';
import { GoodList } from '../components/index';
import { postData } from '../services/FetchData';
import { baseUrl } from '../config/Constants';

class SearchResult extends Component {
    constructor(props) {
        super(props);
        this.state = {
            searchText: this.props.match.params.q,
            coupons: null,
            pageNo: 2,
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.loadMore = this.loadMore.bind(this);
    }

    componentDidMount() {
        console.log(this.state.searchText);
        let url = baseUrl + '/api/search';
        let word = this.state.searchText ? this.state.searchText : 'any';
        let data = {
            q: word
        };
        postData(url, data)
            .then(res => res.json())
            .then(data => {
                this.setState({
                    coupons: data
                })
            })
            .then(e => {
                console.log('error' + e);
            });

    }

    handleChange(text, e) {
        this.setState({
            searchText: text
        });
    }

    handleSubmit() {
        console.log('enter handleSubmit()');
        let url = baseUrl + '/api/search';
        let word = this.state.searchText !== '' ? this.state.searchText : 'any';
        let data = {
            q: word
        };
        postData(url, data)
            .then(res => res.json())
            .then(data => {
                console.log('searchData:' + data);
                this.setState({
                    coupons: data,
                    searchText: word,
                    pageNo: 2,
                })
            })
            .then(e => {
                console.log('error' + e);
            })
    }

    loadMore(resolveLoading, finish) {
        let url = baseUrl + '/api/search/more/' + this.state.pageNo;
        let word = this.state.searchText !== '' ? this.state.searchText : 'any';
        let data = {
            q: word
        };
        postData(url, data)
            .then(res => res.json())
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
                    if (curNo > 4) {
                        console.log('stop fetching new data');
                        //finish();
                    }
                }, 5000);
            })
            .then(e => {
                console.log("error" + e);
            });
    }


    render() {
        let coupons = this.state.coupons;
        if (coupons !== null) {
            return (
                <Page
                    transition={true}
                    infiniteLoader={true}
                    ptr={false}
                    onLoadMore={this.loadMore}
                >
                    <SearchBar
                        onChange = {this.handleChange.bind(this)}
                        onSubmit={ this.handleSubmit }
                        defaultValue = {this.state.searchText}
                        placeholder='粘贴宝贝标题 或 关键字'
                        lang={{
                            cancel: '取消'
                        }}
                    />
                    <GoodList coupons={coupons}/>
                </Page>
            )
        }
        return (
            <div>
                Loading...
            </div>
        );
    }

}

export default SearchResult;