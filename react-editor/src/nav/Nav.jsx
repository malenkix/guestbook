import React from 'react'

import style from './Nav.css'

import Constants from '../services/Constants'

const Nav = ({ callbacks, onOkClick }) => {
  return (
    <div className='nav'>
      <img src='assets/btn-back.png' alt='' title='' onClick={() => callbacks.setUiActiveFrame(Constants.FRAMES.POST_EDITOR)} />
      <img src='assets/btn-ok.png' alt='' title='' onClick={onOkClick} />
    </div>
  )
}

export default Nav;
