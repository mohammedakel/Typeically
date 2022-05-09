import React from "react"

// @ts-ignore
const Searcher = ({onLoad: handleSearch, onClick}) => {
    //prevent pressing space from scrolling the page (for longer songs)
    document.documentElement.addEventListener('keydown', function (e) {
        // @ts-ignore
        if ( ( e.keyCode || e.which ) == 32
            && document.activeElement !== document.getElementById("search")
            && document.activeElement !== document.getElementById("lbInput")) {
            e.preventDefault();
        }
    }, false);

    return (
        <div id="load-page" >
            <h1 className="t1">Typeically</h1>
            <div className={"barButton"}>
                <input id="search" className="search" placeholder="Search..." autoComplete={"off"} ></input>
                <button className="button" onClick={handleSearch}>Enter</button>
            </div>
            <div id ="popup">
                <button className="res" id ="0"onClick={onClick}>E</button>
                <button className="res" id ="1">E</button>
                <button className="res" id ="2">E</button>
                <button className="res" id ="3">E</button>
                <button className="res" id ="4">E</button>
                <button className="res" id ="5">E</button>
                <button className="res" id ="6">E</button>
                <button className="res" id ="7">E</button>
                <button className="res" id ="8">E</button>
                <button className="res" id ="9">E</button>
            </div>
            <div id="page">
                <h1 className="t2"></h1>
            </div>
        </div>
    )
}

export default Searcher