const getQueryParemeters = (attrs: Array<string>): Array<string>  => {
    const searchParams = new URLSearchParams(window.location.search);
    console.log(searchParams);
    let result: Array<string> = [];
    attrs.forEach((attr) => {
        result.push(searchParams.get(attr));
    });
    return result;
}

{/* {getQueryParemeters(['userName', 'userEmail']).map((value) => {
    return (
        <h1>
            {value}
        </h1>
        
    )
})} */}