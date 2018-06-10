import React from 'react'

const Text = ({ state = {}, fonts }) => {

  const divInline = {
    left: (state.textX || 0) + 'px',
    top: (state.textY || 0) + 'px',
    transform: `rotate(${state.textRotation || 0}deg)`
  }

  const inline = {
    fontFamily: (fonts || 'Serif'),
    fontSize: (state.textSize || 0) + 'px',
    color: state.textColor || '#fff'
  }

  return (
    <div className='text' style={divInline}>
      {state.text && <span style={inline}>{state.text}</span>}
    </div>
  )
}

export default Text;
