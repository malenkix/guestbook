import React from 'react'

const Image = ({ state = {} }) => {

  const divInline = {
    left: (state.imageX || 0) + 'px',
    top: (state.imageY || 0) + 'px',
    transform: `rotate(${state.imageRotation || 0}deg)`
  }

  const inline = {
    widht: (state.imageWidth || 0) + 'px',
    height: (state.imageHeight || 0) + 'px'
  }

  return (
    <div className='image' style={divInline}>
      {state.image && <img id='post-image-preview' style={inline} src={state.image} alt='' title='' />}
    </div>
  )
}

export default Image;
