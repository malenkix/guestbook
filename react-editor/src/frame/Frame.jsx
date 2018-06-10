import React from 'react'

import style from './Frame.css'

const Frame = ({ state = {}, name, children }) => {
  const active = state.uiActiveFrame && state.uiActiveFrame === name;
  return (
    <section className={`frame ${name ? name : ''} ${active ? 'active' : ''}`}>
      {children}
    </section>
  )
}

export default Frame;
