
import React, { useState, useEffect } from 'react'
import NavBar from './components/NavBar/NavBar'
import './index.scss'
import Profile from './components/Profile/Profile'

import { render } from '@testing-library/react'

function App() {

    
    return (
      <div id='App' className='full-height'>
        <NavBar />
        <div id='mainContainer'>
          <Profile />
        </div>
      </div>
    );
  }

  export default App;
