import React, { Component } from 'react';

import { LoadMore } from 'react-weui';
import { CopyToClipboard } from 'react-copy-to-clipboard';
import {
    Image,
} from 'react-bootstrap';

import { baseUrl } from '../config/Constants';

class Detail extends Component {

    constructor(props) {
        super(props);
        console.log(props);
        this.handleFetch = this.handleFetch.bind(this);
        this.back = this.back.bind(this);
        this.state = {
            coupon: null
        };
    }
    componentDidMount() {
        console.log("DidMount");
        this.handleFetch();

    }

    componentWillUnmount() {
        console.log("will unmount");
    }

    back() {
        //alert("go back?");
        //window.history.go(-1);
        //this.props.history.goBack();
    }

    handleFetch() {
        let url = baseUrl + '/api/detail/' + this.props.match.params.id;
        fetch(url).then(res => res.json())
            .then(data => {
                this.setState({
                    coupon: data
                });
                console.log('fetched data:' + this.state.coupon);
            })
            .then(e => console.log("fetch data error", e))
    }

    render() {
        const styleClass = {
            priceDesc: {
                width: '58%',
                backgroundColor: '#FF9600',
                color: '#ffffff',
                textAlign: 'center',
                float: 'left',
            },
            buyBtn: {
                width: '42%',
                backgroundColor: '#E3393C',
                color: '#ffffff',
                textAlign: 'center',
                float: 'left',
            },
            nowPrice: {
                width: '70%',
                lineHeight: '50px',
                fontSize: '28px',
                color: '#ffffff',
                textAlign: 'center',
            },
            oldPrice: {
                width: '30%',
                fontSize: '13px',
                color: '#ffffff',
            },
            hr: {
                width: '100%',
                height: '10px',
                backgroundColor: '#f2f2f2',
            }
        };
        let coupon = this.state.coupon;

        if (coupon !== null) {
            let imgs = {};
            if (coupon.smallImages !== null) {
                 imgs = coupon.smallImages.map((src, idx) => {
                    return (
                        <img key={idx} src={ src } alt='' style={{width: '100%'}}/>
                    )
                });
            }
            return (
                <div style={{ marginBottom: '52px'}}>
                    <Image src={ coupon.pictUrl } style={{ width: '100%'}}/>
                    <div style={{
                        backgroundColor: '#ED4D57',
                        height: '50px',
                        display: 'flex',
                        flexDirection: 'row'
                    }}>
                        <div style={styleClass.nowPrice}>
                            <span style={{fontSize: '1.2rem', marginRight: '0.1rem'}}>¥</span>
                            {coupon.priceAfterCoupon}
                            </div>
                        <div style={styleClass.oldPrice}>
                            <div style={{
                                fontSize: '15px',
                                textDecoration: 'line-through'
                            }}><span style={{fontSize: '0.68rem', marginRight: '0.2rem'}}>¥</span>{coupon.zkFinalPrice}</div>
                            <div style={{
                                border: '1px solid #ffffff',
                                width: '96%',
                                textAlign: 'center'
                            }}>优惠券：<span style={{fontSize: '0.5rem', marginRight: '0.1rem'}}>¥</span>{coupon.couponValue}</div>
                        </div>
                    </div>
                    <div style={{ fontSize: 'small', padding: '8px'}}>
                        <span><img src={'../images/tmtitleicon.png'} alt=''
                                    style={{ display: 'inline', width: '18px'}}/></span>
                        {coupon.title}
                    </div>
                    <div style={styleClass.hr} />
                    <div style={{
                        height: '50px',
                        display: 'flex',
                        flexDirection: 'row'}}>
                        <div style={{
                            width: '50%',
                            float: 'left',
                            lineHeight: '50px',
                            verticalAlign: 'middle',
                            paddingLeft: '5px',
                        }}>
                            <span><img src={'../images/fanli.png'} alt='' style={{ display: 'inline', width: '30px' }}/></span>
                            <span style={{ fontSize: 'small'}}>返现: </span>
                            <span style={{ color: 'orange' }}>{coupon.rebate}</span> 元
                        </div>
                        <div style={{
                            width: '50%',
                            float: 'left',
                            lineHeight: '50px',
                            verticalAlign: 'middle',
                            borderLeft: '1px solid black',
                            paddingLeft: '4px',
                        }}>
                            <span><img src={'../images/weixin0.png'} alt='' style={{ display: 'inline', width: '30px' }}/></span>
                            <span style={{ fontSize: 'small'}}>微信: </span> 348928762
                        </div>
                    </div>
                    <div style={styleClass.hr} />
                    <div style={{
                        height: '90px',
                        lineHeight: '90px',
                        verticalAlign: 'middle',
                        display: 'flex',
                        flexDirection: 'row'
                    }}>
                        <span><img src={coupon.shopLogo} alt='' style={{ width: '70px', display: 'inline'}}/></span>
                        <span style={{ marginLeft: '5px', marginRight: '5px'}}>{coupon.shopTitle}</span>
                        <span><img src={coupon.userType ? '../images/tmtitleicon.png' : '../images/tbtitleicon.png'} alt='' style={{ width: '18px', display: 'inline'}}/></span>
                    </div>
                    <div style={styleClass.hr} />
                    <div>
                        {imgs}
                    </div>
                    <div style={{
                        width: '100%',
                        height: '50px',
                        lineHeight: '50px',
                        position: 'fixed',
                        bottom: '0px',
                        textAlign: 'center',
                        verticalAlign: 'middle',
                        zIndex: '1000'
                    }}>
                        <div style={styleClass.priceDesc}>
                            ¥ {coupon.priceAfterCoupon} / 返现 {coupon.rebate}
                        </div>
                        <div style={styleClass.buyBtn}>
                            <CopyToClipboard text={coupon.taokouling}
                                onCopy={() => alert('淘口令复制成功，打开手机淘宝即可领券')}>
                                <span>立即领券</span>
                            </CopyToClipboard>
                        </div>
                    </div>
                </div>
            )
        }
        return (
            <div>
                <LoadMore loading>Loading</LoadMore>
            </div>
        );
    }
}

export default Detail;