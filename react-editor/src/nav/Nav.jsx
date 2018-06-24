import React from 'react'

import style from './Nav.css'

import Config from '../services/Config'
import Constants from '../services/Constants'

const Nav = ({ callbacks, hideOk, onOkClick }) => {
  return (
    <div className='nav'>
      <img src={`${Config.ASSETS}/btn-back.png`} alt='' title='' onClick={() => callbacks.setUiActiveFrame(Constants.FRAMES.POST_EDITOR)} />
      <img src={`${Config.ASSETS}/btn-ok.png`} alt='' title='' hidden={hideOk} onClick={onOkClick} />
    </div>
  )
}

export default Nav;
