import React, {useState} from "react";
import TypingPage from "./components/TypingPage";
import "./styles.css";
import Searcher from "./components/Searcher";

var geniusLyricsAPI = require("genius-lyrics-api")

// Here indices represents the search results
var lyrics = ' '
// @ts-ignore
var indices = []

const options = {
       //title:  document.getElementById("search"),
       title:  'Halo',
       artist: ' ',
       apiKey: '3pTLkXn4VZpZXk3qJ61nkioYxvEaqzadxVGK1FNEN8-katQfRqngvvt1XzA06CaT', // Genius developer access token
       optimizeQuery: true // Setting this to true will optimize the query for best results
}

async function searchSongs() {
    // @ts-ignoree
    indices = await geniusLyricsAPI.searchSong(options)
    console.log(indices)
}

async function getLyrics() {
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[0].url)
}

    let albumArt = ' '
    let title = ' '
    let id = ' '

// ******************************************************************************** //

const App = () => {
  const [song, setSong] = useState<boolean>(false)
  const handleSearch = async () => {
    await searchSongs()
    await getLyrics()

      // @ts-ignore
      albumArt = indices[0].albumArt
      // @ts-ignore
      title = indices[0].title
      // @ts-ignore
      id = indices[0].id

    // @ts-ignore
    console.log(indices[0])
    setSong(true)
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