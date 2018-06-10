import React from 'react'

import style from './Header.css'

const empty = <div className='icon'></div>

const Header = ({ callbacks = {}, icon, alt, title }) => {
  return (
    <div className='header'>
      {icon != null ? <img className='icon' src={icon} alt={alt} title={title} /> : empty}
      <img className='logo' src='/assets/logo.svg' alt='' title='' onClick={callbacks.showStateAndCallbacks} />
      {empty}
    </div>
  )
}

export default Header;
