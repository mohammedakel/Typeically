import React, {useState} from "react";
import TypingPage from "./components/TypingPage";
import "./styles.css";
import Searcher from "./components/Searcher";

let artist = "Amaarae"
let name = "FEEL A WAY"
let id = ""
let lyrics = "[Verse 1: Moliy]\n" +
    "I really wanna know know\n" +
    "Are you feeling the vibes?\n" +
    "Hennessy on the rocks, Sweet Fernando\n" +
    "Maybe you could be my commando\n" +
    "I wanna bring you to my condo\n" +
    "I wanna fuck ya, but I don’t know\n" +
    "Might wanna rock you like Calypso\n" +
    "If you do me sweet like Haribo"
const App = () => {
  const [song, setSong] = useState<boolean>(false)

  const handleSearch = async () => {
    setSong(true);

  }

  return (
      <div className="App">
        {song ?
            <TypingPage id={id} artist={artist} songName={name} lyrics={lyrics} /> :
            <Searcher onLoad={handleSearch} />}
      </div>
  );
};

export default App;
