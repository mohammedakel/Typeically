import React from "react"
import {useState} from "react";

// @ts-ignore
const Searcher = ({onLoad: handleSearch}) => {
    //prevent pressing space from scrolling the page (for longer songs)
    document.documentElement.addEventListener('keydown', function (e) {
        // @ts-ignore
        if ( ( e.keyCode || e.which ) === 32
            && document.activeElement !== document.getElementById("search")
            && document.activeElement !== document.getElementById("lbInput")) {
            e.preventDefault();
        }
    }, false);

    const [query, setQuery] = useState("")

    return (
        <div id="load-page" >
            <h1 className="t1">Typeically</h1>
            <div className={"barButton"}>
                <input id="search" className="search" placeholder="Search..." autoComplete={"off"} onChange={event => setQuery(event.target.value)} value={query} ></input>
                <button className="button" onClick={handleSearch}>Enter</button>
            </div>
            <div id ="popup">
                <button className="res" id ="0">Enter</button>
                <button className="res" id ="1">Enter</button>
                <button className="res" id ="2">Enter</button>
                <button className="res" id ="3">Enter</button>
                <button className="res" id ="4">Enter</button>
                <button className="res" id ="5">Enter</button>
                <button className="res" id ="6">Enter</button>
                <button className="res" id ="7">Enter</button>
                <button className="res" id ="8">Enter</button>
                <button className="res" id ="9">Enter</button>
            </div>
            <div id="page">
                <h1 className="t2"></h1>
            </div>
        </div>
    )
}

export default Searcher