import React, {useState} from "react";
import TypingPage from "./components/TypingPage";
import "./styles.css";
import Searcher from "./components/Searcher";

let artist = "Lauryn Hill"
let name = "Ex-Factor"
let id = ""
let lyrics = "it could all be so simple but you'd rather make it hard loving you is like a battle and we both end up with scars tell me, who i have to be? to get some reciprocity see, no one loves you more than me and no one ever will is this just a silly game that forces you to act this way, forces you to scream my name, then pretend that you can't stay?"
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
