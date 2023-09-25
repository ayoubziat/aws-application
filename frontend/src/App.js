import logo from './logo.svg';
import React, {useState, useEffect} from 'react';
import './App.css';
import axios from 'axios';

const Users = () => {

  const fetchUsers = () => {
    axios.get("http://localhost:8080/api/v1/users").then(
      response => {
        console.log(response);
      }
    );
  }

  useEffect(
    () => {
      fetchUsers();
    }, []
  )

  return <h1>Hello</h1>
}

function App() {
  return (
    <div className="App">
      <Users/>
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}
    </div>
  );
}

export default App;
