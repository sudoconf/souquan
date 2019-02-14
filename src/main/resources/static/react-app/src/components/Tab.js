import React from 'react'

const Tab = (props) => {
    const {children, ...others} = props;
    const divProps = Object.assign({}, others);
    return (
        <div style={{
            width: '100%',
            height: '100px',
            position: 'absolute',
            bottom: '0px',
            display: 'flex',
            flexDirection: 'row',
            backgroundColor: '#F7F7FA'
        }} {...divProps}>
            {children}
        </div>
    );
};

export default Tab;