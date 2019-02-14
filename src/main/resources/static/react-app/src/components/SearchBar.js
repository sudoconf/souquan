import React, { Component } from 'react';
import { SearchBar } from 'react-weui';

class MySearchBar extends Component {

    constructor(props) {
        super(props);
        this.state = {
            searchText: ''
        };

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(text, e) {
        this.setState({
            searchText: text
        })
    }

    handleSubmit() {
        let searchText = this.state.searchText !== '' ? this.state.searchText : 'any';
        let path = "/search-result/" + searchText;
        this.props.onSubmit(path);
    }

    render() {
        return (
            <SearchBar
                onChange = {this.handleChange.bind(this)}
                onSubmit={ this.handleSubmit }
                defaultValue = {this.state.searchText}
                placeholder='粘贴宝贝标题 或 关键字'
                lang={{
                    cancel: '取消'
                }}
            />
        )
    }
}

export default MySearchBar;