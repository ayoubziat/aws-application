import logo from './logo.svg';
import React, {useState, useEffect, useCallback} from 'react';
import {useDropzone} from 'react-dropzone';
import './App.css';
import axios from 'axios';

const Users = () => {

  const [users, setUsers] = useState([]);

  const fetchUsers = () => {
    axios.get("http://localhost:8080/api/v1/users").then(
      response => {
        console.log(response);
        setUsers(response.data);
      }
    );
  }

  useEffect(
    () => {
      fetchUsers();
    }, []
  )

  return users.map((user, index) => {
    return (
      <div key={index}>
        {
          user.userId ? (
            <img 
              src={`http://localhost:8080/api/v1/users/${user.userId}/image/download`}
            /> 
          ) : null
        }
        <br/>
        <br/>
        <h1>{user.username}</h1>
        <p>{user.userId}</p>
        <Dropzone userId={user.userId} />
        <br/>
      </div>
    )
  })
}

function Dropzone({ userId }) {
  const onDrop = useCallback(acceptedFiles => {
    // Do something with the files
    const file = acceptedFiles[0];
    const formData = new FormData();
    formData.append("file", file);

    axios.post(
      `http://localhost:8080/api/v1/users/${userId}/image/upload`, 
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }
    )
    .then(() => {
        console.log("File uploaded successfully")
    })
    .catch(error => {
      console.log(error);
    });
    
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the image here ...</p> :
          <p>Drag and drop image here, or click to select an image</p>
      }
    </div>
  )
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
