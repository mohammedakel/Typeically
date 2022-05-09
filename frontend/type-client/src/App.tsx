import React, {useState} from "react";
import TypingPage from "./components/TypingPage";
import "./styles.css";
import Searcher from "./components/Searcher";
import * as ReactBootStrap from 'react-bootstrap';
// import Spinner from "./components/Spinner";

// Good source for spinner: https://hashnode.com/post/how-to-add-loader-or-spinner-using-react-hooks-while-fetching-data-from-apis-ckhv38mr902kdgms100vh3u86
// Look at all these cool spinners!: https://github.com/Mancancode/react-loader/tree/master/src/images/loaders

var geniusLyricsAPI = require("genius-lyrics-api")

// Here indices represents the search results
var lyrics = ' '
// @ts-ignore
var indices = []

const options = {
       title:  ' ',
       artist: ' ',
       apiKey: '3pTLkXn4VZpZXk3qJ61nkioYxvEaqzadxVGK1FNEN8-katQfRqngvvt1XzA06CaT', // Genius developer access token
       optimizeQuery: true // Setting this to true will optimize the query for best results
}

async function searchSongs() {
    // @ts-ignore
    indices = await geniusLyricsAPI.searchSong(options)
    console.log(indices)
}

async function getLyrics() {
//     // @ts-ignore
//     let string = 'https://cat-fact.herokuapp.com/' + indices[0].url
//     lyrics = await geniusLyricsAPI.getLyrics(string)
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[0].url)
}

    let albumArt = ' '
    let title = ' '
    let id = ' '

// ******************************************************************************** //

const App = () => {
  const [song, setSong] = useState<boolean>(false)
  const [loading, setLoading] = useState(false) // NOTE: for the loader } don't even need this — song does the same thing

  const handleSearch = async () => {
    // Source: https://bobbyhadz.com/blog/typescript-type-null-is-not-assignable-to-type-string
    // options.title = document.getElementById("search").value !== null ? options.title: ' '

    // @ts-ignore
    options.title = document.querySelector('input').value;
    console.log(options.title);

    await searchSongs()
    await getLyrics()
    setLoading(true)

    // @ts-ignore
    albumArt = indices[0].albumArt
    // @ts-ignore
    title = indices[0].title
    // @ts-ignore
    id = indices[0].id

    // @ts-ignore
    console.log(indices[0])
    setSong(true)

    // setLoading(false) // NOTE: for the loader
  }

  return (
      <div className="App">
        {song ?
            <TypingPage id={id} title={title} lyrics={lyrics} albumArt={albumArt}/> :
//             (<>
//             <Searcher onLoad={handleSearch} />
//             <ReactBootStrap.Spinner animation="border" />
//             </>)}
            <Searcher onLoad={handleSearch} />}
      </div>
  );

};

export default App;