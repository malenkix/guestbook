import React from 'react'

import style from './Header.css'

const Header = ({ callbacks = {}, icon, alt, title }) => {
  return (
    <div className='header'>
      {icon != null ? <img className='icon' src={icon} alt={alt} title={title} /> : <div className='icon'></div>}
      <img className='logo' src='/assets/logo.svg' alt='' title='' onClick={callbacks.showStateAndCallbacks} />
      <div className='icon'></div>
    </div>
  )
}

export default Header;
