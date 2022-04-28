import React from "react";
import TypingThroughInput from "./TypingThroughInput";

interface PageProps {
    id: string
    title: string
    lyrics: string
    albumArt: string
}
let idxs = [] as number[];
var Filter = require('bad-words'),
    userFilter = new Filter(), userSubstrings = [''], wasProfane = false;
userFilter.removeWords('xxx', 'hell', 'yed');


const TypingPage = ({id, title, lyrics, albumArt}: PageProps) => {
    //replace last ' by ' in title with hyphen ("-")
    var n = title.lastIndexOf(" by ");
    title = title.slice(0, n) + title.slice(n).replace("by", "-");

    lyrics = lyrics.replace(/ *\[[^\]]*]/g, ''); //remove all strings in [square brackets] (ie, [intro], [verse 1], etc.)
    while(lyrics.charAt(0) === '\n') { //remove all leading \n line breaks at start of lyrics
        lyrics = lyrics.substring(1);
    }
    lyrics = lyrics.replace(/\n\s*\n/g, '\n') //remove double line breaks
    lyrics = lyrics.toLowerCase(); //set all lyrics to lowercase

    for (let i = 0; i < lyrics.length; i++) { //store location/index of line breaks of string in idxs array
        if (lyrics.charAt(i) === '\n') {
            idxs.push(i);
        }
    }
    lyrics = lyrics.replace(/\n/g, ' ') //replace the line breaks with a space
    lyrics = lyrics.normalize("NFD").replace(/[\u0300-\u036f]/g, "")
    lyrics = lyrics.replace(/ /g, ' ')
    lyrics = lyrics.replace(/’/g, '\'')
    lyrics = lyrics.replace(/е/g, 'e') //replace cyrillic e with regular e
    lyrics = lyrics.replace(/—/g, '-') //replace weird longer hyphen with standard hyphen
    lyrics = lyrics.replace(/[^0-9a-z!\s@#$%^&*()_+={}|:;'"<>,.?/~`-]/gi, '?') //replace any unrecognized characters not on english keyboards with ?

    return (
        <div>
            <h1><a className={"t1"} href='.' onClick={(event: React.MouseEvent<HTMLElement>) => {
                window.location.reload();
            }}>Typeically</a></h1>
            <div className="container mx-auto flex flex-col p-4">
                <h5 id={"escape-instruct"}>Esc to reset</h5>
                <div className="border-2 p-4 rounded-lg">
                    <h1 onMouseOver={showImage} onMouseLeave={hideImage} className="mb-2">{title}</h1>

                    <div id={"lyrics"} >
                        <TypingThroughInput
                            text={
                                lyrics
                            }
                            indices={
                                idxs
                            }
                        />
                    </div>
                    <div id={"image"} hidden > <img id={"albumArt"}src={albumArt} alt={""} ></img></div>
                    <div id={"caps-alert"} hidden> Warning: Caps Lock Detected </div>
                </div>
            </div >
            <div className="container2">
                <div id={"lbInstructions"}className="typed-out" hidden>Enter a user name and submit to leaderboard</div>
            </div>
            <div id={"lBoardInputs"} className={"lbInputs"}>
                <span><input id="lbInput" className="search2" placeholder="username" autoComplete={"off"} maxLength={12} hidden></input></span>
                <span><button id="lbButton"className={"button2"} onClick={submitUser} hidden>submit</button></span>
            </div>
            <div className="labelContainer">
                <div id={"invalidUserLabel"}className="typed-out2" hidden>profanity detected in username ;( please try something else</div>
            </div>
        </div>
    );

    function submitUser() {
        let lbInput = document.getElementById("lbInput") as HTMLInputElement;
        let invalidLabel = document.getElementById("invalidUserLabel") as HTMLElement;
        if (contiguousValid()) {
            //ADD RESULTS TO LEADERBOARD
            lbInput.className = lbInput.className.replace(" invalid", "");
            lbInput.className = lbInput.className.replace(" valid", "");
            lbInput.className = lbInput.className + " valid";
            invalidLabel.hidden = true;
        } else {
            lbInput.className = lbInput.className.replace(" invalid", "");
            lbInput.className = lbInput.className.replace(" valid", "");
            lbInput.className = lbInput.className + " invalid";
            invalidLabel.hidden = false;
        }
    }

    function delay(time: number) {
        return new Promise(resolve => setTimeout(resolve, time));
    }

    async function hideImage() {
        let img = document.getElementById("image") as HTMLDivElement;
        let spans = document.getElementsByTagName("span");
        img.style.animation = 'fadeOut .65s, searchmate .9s steps(75, end) forwards';
        for (let i = 0; i < spans.length; i++) {
            spans[i].style.opacity = "1";
        }
        await delay(500);
        img.hidden = true;
    }

    async function showImage() {
        let img = document.getElementById("image") as HTMLDivElement;
        let spans = document.getElementsByTagName("span");
        let albumArt = document.getElementById("albumArt") as HTMLImageElement;
        img.hidden = false;
        img.style.animation = 'fadeIn .65s';

        for (let i = 0; i < spans.length; i++) {
            spans[i].style.opacity = "0";
        }
    }

    function contiguousValid() {
        userSubstrings = [];
        let lbInput = document.getElementById("lbInput") as HTMLInputElement;
        contiguousSubstrings(lbInput.value)
        for (let j = 0; j < userSubstrings.length; j++) {
            if (userFilter.isProfane(userSubstrings[j])) {
                wasProfane = true;
                break;
            }
            if (userFilter.isProfane(userSubstrings[j].replace("x", ""))) {
                wasProfane = true;
                break;
            }
            if (userFilter.isProfane(userSubstrings[j].replace("-", ""))) {
                wasProfane = true;
                break;
            }
            if (userFilter.isProfane(userSubstrings[j].replace("_", ""))) {
                wasProfane = true;
                break;
            }
        }
        if (wasProfane) {
            wasProfane = false;
            return false;
        } else {
            return true;
        }
    }

    function contiguousSubstrings(str: string) {
        for (let i = 0; i < str.length; i++) {
            for (let j = i; j < str.length; j++) {
                userSubstrings.push(str.slice(i, j + 1));
            }
        }
    }

}
export default TypingPage