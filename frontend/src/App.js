import './App.css';
import React, { Component } from 'react';
import Users from './components/test';
import Movies from  './components/movies'
import { Typography } from '@mui/material';
import { useState } from 'react';

class App extends Component {
  render() {
    return (
      <>
      <Movies movies={this.state.movies} />
      </>
    )
  }

  state = {
    movies: []
};
  componentDidMount() {
      fetch('http://localhost:8080/api/movies')
          .then(res => res.json())
          .then((data) => {
              this.setState({ movies: data })
          })
          .catch(console.log)
  }

}

export default App;
