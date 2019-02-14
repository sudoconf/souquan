import React, { Component } from 'react';
import { Link } from "react-router-dom";
import {
    Grids,
    Flex,
    FlexItem
} from 'react-weui';


const NavGrid = (props) => {
    const categories = Object.entries(props.categories);
    console.log(categories);
    const rowData = [categories.slice(0, 5), categories.slice(5, 10)];

    const flexContent = rowData.map((row, idx) => {
        const flexItems = row.map((cat, idx) => {
            return (
                <FlexItem key={idx}>
                    <Link to={`/category/${cat[0]}`} style={{textDecoration: 'none', outline: 'none', color: '#0a0a0b'}}>
                        <div align="center">
                            <img src={cat[1]} width={'60px'} height={'60px'}/>
                            <p align="center"
                               style={{
                                   whiteSpace: 'nowrap',
                                   overflow: 'hidden',
                                   textOverflow: 'ellipsis',
                                   fontSize: 'small'
                               }}>{cat[0]}</p>
                        </div>
                    </Link>
                </FlexItem>
            );
        });
        return (
            <Flex key={idx}>
                { flexItems }
            </Flex>
        );
    });

    return (
        <div style={{ paddingTop: '10px'}}>
            { flexContent }
        </div>
    );
};

export default NavGrid;
