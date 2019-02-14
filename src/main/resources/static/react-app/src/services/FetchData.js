
export const fetchData = (url) => {
    console.log('enter fetchData service');
    fetch(url).then(res => res.json())
        .then(data => {
            console.log('fetched data:' + data);
            return data;
        })
        .then(e => {
            console.log("fetch data error", e);
            //return null;
        })
};

export const postData = (url, data) => {
    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
};
