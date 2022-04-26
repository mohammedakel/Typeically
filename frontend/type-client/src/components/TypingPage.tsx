import React from "react";
import TypingThroughInput from "./TypingThroughInput";

interface PageProps {
    id: string
    artist: string
    songName: string
    lyrics: string
}
let idxs = [] as number[];
const TypingPage = ({id, artist, songName, lyrics}: PageProps) => {

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
                <h5>Esc to reset</h5>
                <div className="border-2 p-4 rounded-lg">
                    <h1 className="mb-2">{artist} - {songName}</h1>
                    <TypingThroughInput
                        text={
                            lyrics
                        }
                        indices={
                            idxs
                        }
                    />
                </div>
            </div >
            <div className="container2">
                <div id={"lbInstructions"}className="typed-out" hidden>Enter a user name and submit to leaderboard</div>
            </div>
            <div id={"lBoardInputs"} className={"lbInputs"}>
                <span><input  id="lbInput" className="search2" placeholder="username" autoComplete={"off"} hidden></input></span>
                <span><button id="lbButton"className={"button2"} hidden>submit</button></span>
            </div>
        </div>
    );
}
export default TypingPage