import React from 'react'

import style from './FancyBox.css'

const FancyBox = ({ color, image, selected, onClick }) => {
  const inline = {
    backgroundColor: color && color,
    backgroundImage: image && `url(${image})`
  }
  return (
    <div
      className={`fancyBox ${selected && 'selected' || ''}`}
      style={inline}
      onClick={onClick}>
    </div>
  )
}

export default FancyBox;
