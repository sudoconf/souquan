import React, { Component } from 'react';
import { Link } from "react-router-dom";
import {
    Table,
    Card
}from 'react-bootstrap'

import {
    Cells,
    Cell,
    CellHeader,
    CellBody,
} from 'react-weui'


const GoodList = ( props ) => {
    console.log("enter GoodList render()");
    const coupons = props.coupons;
    console.log('new coupons:' + coupons);


    const contents = coupons.map((coupon, idx) => {
            return (
                <Link to={`/detail/${coupon.numIid}`} style={{textDecoration: 'none', outline: 'none', color: '#0a0a0b'}}>
                    <div style={{ width: '50%', float: 'left', position: 'relative'}}>
                        <Card key={idx} style={{ padding: '8px' }}>
                            <Card.Img variant="top" src={coupon.pictUrl}/>
                            <Card.Body style={{ padding: '1px' }}>
                                <Card.Text>
                                    <Cell style={{ padding: '1px'}}>
                                        <CellHeader>
                                            <img src={ coupon.userType === 1 ? '../images/tmtitleicon.png' : '../images/tbtitleicon.png'} alt=""
                                                 style={{ display: 'block', width: '18px'}}/>
                                        </CellHeader>
                                        <CellBody style={{
                                            fontSize: 'small',
                                            whiteSpace: 'nowrap',
                                            overflow: 'hidden',
                                            textOverflow: 'ellipsis'}}>
                                            {coupon.title}
                                        </CellBody>
                                    </Cell>
                                    <Table responsive='sm' style={{ marginTop: '2px', marginBottom: '0'}}>
                                        <tbody>
                                        <tr>
                                            <td style={{ padding: '0.8px', color: '#f62e2e', fontSize: 'small'}}>券后:
                                                <span style={{ fontSize: '0.88rem', marginLeft: '.05rem'}}> ¥
                                                    <span style={{ fontSize: '1.2rem', marginLeft: '.10rem'}}>{coupon.priceAfterCoupon}</span>
                                                </span>
                                            </td>
                                            <td style={{ padding: '0.8px', color: '#f62e2e', fontSize: 'small'}}>优惠券:
                                                <span style={{ fontSize: '0.88rem', marginLeft: '.05rem'}}> ¥
                                                    <span style={{ fontSize: '1.2rem', marginLeft: '.10rem'}}>{coupon.couponValue}</span>
                                                </span>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </Table>
                                </Card.Text>
                            </Card.Body>
                        </Card>
                        <div style={{
                            width: '3.48rem',
                            height: '3.68rem',
                            background: 'url(../images/fanxian.png) no-repeat',
                            backgroundSize: 'cover',
                            textAlign: 'center',
                            color: 'white',
                            fontSize: 'small',
                            position: 'absolute',
                            right: '4px',
                            top: '0px',
                            zIndex: '999'
                        }}>
                            <p style={{
                                marginTop: '1.2rem',
                                color: '#fff',
                                fontSize:'.88rem',
                                textAlign: 'center'}}>
                                ¥
                                <span style={{ fontSize: '1.2rem', marginLeft: '.05rem'}}>{coupon.rebate}</span>
                            </p></div>
                    </div>
                </Link>
            );
        });

    return(
        <div>
            {contents}
        </div>
    );
};

export default GoodList;

