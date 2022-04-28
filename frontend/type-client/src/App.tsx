import React, {useState} from "react";
import TypingPage from "./components/TypingPage";
import "./styles.css";
import Searcher from "./components/Searcher";

let albumArt = "https://t2.genius.com/unsafe/576x576/https%3A%2F%2Fimages.genius.com%2Fe42bf41b71339f636619de3f6a8eb04d.1000x1000x1.jpg"
let title = "FEEL A WAY by Amaarae"
let id = ""
let lyrics = "[Verse 1: Moliy]\n" +
    "I really wanna know know\n"

const App = () => {
  const [song, setSong] = useState<boolean>(false)

  const handleSearch = async () => {
    setSong(true);

  }

  return (
      <div className="App">
        {song ?
            <TypingPage id={id} title={title} lyrics={lyrics} albumArt={albumArt}/> :
            <Searcher onLoad={handleSearch} />}
      </div>
  );
};

export default App;
