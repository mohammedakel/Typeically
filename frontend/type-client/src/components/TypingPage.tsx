import React from "react";
import TypingThroughInput from "./TypingThroughInput";

interface PageProps {
    id: string
    artist: string
    songName: string
    lyrics: string
}
const TypingPage = ({id, artist, songName, lyrics}: PageProps) => {

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
                    />
                </div>
            </div >
            <div id={"lBoardInputs"} className={"lbInputs"}>
                <input  id="search" className="search2" placeholder="username" autoComplete={"off"} ></input>
                <button className={"button2"}>submit</button>
            </div>
        </div>
    );
}
export default TypingPage