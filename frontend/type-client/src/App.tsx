import React, {useState} from "react";
import TypingPage from "./components/TypingPage";
import "./styles.css";
import Searcher from "./components/Searcher";

var geniusLyricsAPI = require("genius-lyrics-api")

// Here indices represents the search results for the program
var lyrics = ' '
var indices : any

const options = {
       title:  document.getElementById("search"),
       artist: ' ',
       apiKey: '3pTLkXn4VZpZXk3qJ61nkioYxvEaqzadxVGK1FNEN8-katQfRqngvvt1XzA06CaT', // Genius developer access token
       optimizeQuery: true // Setting this to true will optimize the query for best results
}

async function searchSongs() {
    indices = await geniusLyricsAPI.searchSong(options)
    console.log(indices)
}

const searchResults = async () => {
    await searchSongs()
}

async function getLyrics() {
    lyrics = await geniusLyricsAPI.getLyrics(indices[0].url)
}

const lyricsResults = async () => {
    await getLyrics()
}

  searchResults()
  lyricsResults()

  let albumArt = indices[0].albumArt
  let title = indices[0].title
  let id = indices[0].id

// ******************************************************************************** //

const App = () => {
  const [song, setSong] = useState<boolean>(false)
  const handleSearch = async () => {
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