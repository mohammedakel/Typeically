import React, {useEffect, useState} from "react";
import TypingPage from "./components/TypingPage";
import "./styles.css";
import Searcher from "./components/Searcher";
import axios from 'axios';

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
let id = 'A'

// ******************************************************************************** //

const App = () => {
  const HOST_URL: string = "http://localhost:4567"
  const [topSongs, setTopSongs] = useState<string[]>([])
  const [topArtists, setTopArtists] = useState<string[]>([])

  const getTopSongs = () => {
    axios.get(HOST_URL + "/spotify")
        .then((response: any) => {
          if(response.data['topTracks'] != null){
            setTopSongs(response.data['topTracks'])
            setTopArtists(response.data['artists'])
          }
        })
        .catch((e: any) => {
          console.log(e)
        });
    return topSongs
  }

  const [song, setSong] = useState<boolean>(false)
  const [loading, setLoading] = useState(false) // NOTE: for the loader } don't even need this — song does the same thing

  const handleSearch = async () => {
    // Source: https://bobbyhadz.com/blog/typescript-type-null-is-not-assignable-to-type-string
    // options.title = document.getElementById("search").value !== null ? options.title: ' '

    // @ts-ignore
    options.title = document.querySelector('input').value;
    console.log(options.title);

    await searchSongs()

    //setLoading(true)

    let resBtn = document.getElementsByClassName('res');
    for(let i = 0; i< resBtn.length; i++) {
      let bttn = resBtn[i] as HTMLButtonElement
      bttn.style.display = "block";
      // @ts-ignore
      bttn.innerText = indices[i].title
      // @ts-ignore
    }
    let bttn0 = resBtn[0] as HTMLButtonElement
    bttn0.onclick = onClick
    let bttn1 = resBtn[1] as HTMLButtonElement
    bttn1.onclick = onClick1
    let bttn2 = resBtn[2] as HTMLButtonElement
    bttn2.onclick = onClick2
    let bttn3 = resBtn[3] as HTMLButtonElement
    bttn3.onclick = onClick3
    let bttn4 = resBtn[4] as HTMLButtonElement
    bttn4.onclick = onClick4
    let bttn5 = resBtn[5] as HTMLButtonElement
    bttn5.onclick = onClick5
    let bttn6 = resBtn[6] as HTMLButtonElement
    bttn6.onclick = onClick6
    let bttn7 = resBtn[7] as HTMLButtonElement
    bttn7.onclick = onClick7
    let bttn8 = resBtn[8] as HTMLButtonElement
    bttn8.onclick = onClick8
    let bttn9 = resBtn[9] as HTMLButtonElement
    bttn9.onclick = onClick9


    // @ts-ignore
    //albumArt = indices[0].albumArt
    // @ts-ignore
    //title = indices[0].title
    // @ts-ignore
    //id = indices[0].id

    // @ts-ignore
    console.log(indices[0])


    // setLoading(false) // NOTE: for the loader
  }

  const handleChooseTopSong = async (value: string) => {
    // Source: https://bobbyhadz.com/blog/typescript-type-null-is-not-assignable-to-type-string
    // options.title = document.getElementById("search").value !== null ? options.title: ' '
    options.title = value;
    options.artist = topArtists[topSongs.indexOf(value)]
    console.log(options.title);

    await searchSongs()
    await getLyrics()

    // @ts-ignore
    albumArt = indices[0].albumArt
    // @ts-ignore
    title = indices[0].title
    // @ts-ignore
    id += indices[0].id

    // @ts-ignore
    console.log(indices[0])
    setSong(true)
  }

  useEffect(() => {
    getTopSongs();
  }, []);

  async function onClick() {
    // @ts-ignore
    id += indices[0].id
    // @ts-ignore
    albumArt = indices[0].albumArt
    //@ts-ignore
    title = indices[0].title
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[0].url)

    setSong(true)
  }

  async function onClick1() {
    // @ts-ignore
    id += indices[1].id
    // @ts-ignore
    albumArt = indices[1].albumArt
    //@ts-ignore
    title = indices[1].title
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[1].url)

    setSong(true)
  }
  async function onClick2() {
    // @ts-ignore
    id += indices[2].id
    // @ts-ignore
    albumArt = indices[2].albumArt
    //@ts-ignore
    title = indices[2].title
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[2].url)

    setSong(true)
  }
  async function onClick3() {
    // @ts-ignore
    id += indices[3].id
    // @ts-ignore
    albumArt = indices[3].albumArt
    //@ts-ignore
    title = indices[3].title
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[3].url)

    setSong(true)
  }
  async function onClick4() {
    // @ts-ignore
    id += indices[4].id
    // @ts-ignore
    albumArt = indices[4].albumArt
    //@ts-ignore
    title = indices[4].title
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[4].url)

    setSong(true)
  }
  async function onClick5() {
    // @ts-ignore
    id += indices[5].id
    // @ts-ignore
    albumArt = indices[5].albumArt
    //@ts-ignore
    title = indices[5].title
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[5].url)

    setSong(true)
  }
  async function onClick6() {
    // @ts-ignore
    id += indices[6].id
    // @ts-ignore
    albumArt = indices[6].albumArt
    //@ts-ignore
    title = indices[6].title
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[6].url)

    setSong(true)
  }
  async function onClick7() {
    // @ts-ignore
    id += indices[7].id
    // @ts-ignore
    albumArt = indices[7].albumArt
    //@ts-ignore
    title = indices[7].title
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[7].url)

    setSong(true)
  }
  async function onClick8() {
    // @ts-ignore
    id += indices[8].id
    // @ts-ignore
    albumArt = indices[8].albumArt
    //@ts-ignore
    title = indices[8].title
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[8].url)

    setSong(true)
  }
  async function onClick9() {
    // @ts-ignore
    id += indices[9].id
    // @ts-ignore
    albumArt = indices[9].albumArt
    //@ts-ignore
    title = indices[9].title
    // @ts-ignore
    lyrics = await geniusLyricsAPI.getLyrics(indices[9].url)

    setSong(true)
  }

  return (
      <div className="App">
        {song ?
            <TypingPage id={id} title={title} lyrics={lyrics} albumArt={albumArt}/> :
            <div>
              <select id={"dropdown"} onChange = {(event) => handleChooseTopSong(event.target.value)}>
                <option selected> -- select a top song -- </option>
                {topSongs.map((title) => <option value={title} key={title}>{title + " by " + topArtists[topSongs.indexOf(title)]}</option>)}
              </select>
              <Searcher onLoad={handleSearch} onClick={onClick} />
            </div>}
      </div>
  );

};

export default App;