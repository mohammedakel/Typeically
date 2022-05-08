import React, {useState} from "react";
import TypingPage from "./components/TypingPage";
import "./styles.css";
import Searcher from "./components/Searcher";

var geniusLyricsAPI = require("genius-lyrics-api")

// Here indices represents the search results
var lyrics = ' '
var indices  : any

const options = {
       //title:  document.getElementById("search"),
       title:  'Halo',
       artist: ' ',
       apiKey: '3pTLkXn4VZpZXk3qJ61nkioYxvEaqzadxVGK1FNEN8-katQfRqngvvt1XzA06CaT', // Genius developer access token
       optimizeQuery: true // Setting this to true will optimize the query for best results
}

async function searchSongs() {
    await geniusLyricsAPI.searchSong(options)
    console.log(indices)
}

await searchSongs()

async function getLyrics() {
    await geniusLyricsAPI.getLyrics(indices[0].url)
}

await getLyrics()

// const searchResults = async () => {
//     await searchSongs()
// }
//
// const lyricsResults = async () => {
//     await getLyrics()
// }
//
//   searchResults()
//   lyricsResults()

  let albumArt = indices[0].albumArt
  let title = indices[0].title
  let id = indices[0].id

//     let albumArt = ' '
//     let title = ' '
//     let id = ' '

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