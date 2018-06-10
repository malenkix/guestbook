import React from 'react'

import style from './ImageEditor.css'

import Constants from '../services/Constants'

import Frame from '../frame/Frame'
import Header from '../header/Header'
import Post from '../post/Post'
import Nav from '../nav/Nav'
import Slider from '../input/Slider'

const ImageEditor = ({ state, callbacks }) => {
  return (
    <Frame name={Constants.FRAMES.IMAGE_EDITOR} state={state}>
      <Header icon='/assets/btn-image.png' callbacks={callbacks} />
      <Post state={state} callbacks={callbacks} upload compact />
      <div className='view'>
        <Nav callbacks={callbacks} />
        <div className='content'>
          <div className='line'>
            <Slider
              name='slider-scale' label='Skalierung'
              min='10' max='200' step='10' value={state.imageScale}
              ticks={['10%', '100%', '200%']} onChange={(e) => {
                const scale = e.target.value
                callbacks.updateState({
                  imageScale: scale,
                  imageWidth: 300 * (scale / 100),
                  imageHeight: 310 * (scale / 100)
                })
              }}
            />
            <Slider
              name='slider-rotate' label='Rotation'
              min='-180' max='180' step='5' value={state.imageRotation}
              ticks={['-180°', '0°', '+180°']}
              onChange={(e) => {
                callbacks.setImageRotation(e.target.value)
              }}
            />
          </div>
          <div className='line'>
            <Slider
              name='slider-x' label='Position horizontal'
              min='-100' max='350' value={state.imageX}
              onChange={(e) => {
                callbacks.setImageX(e.target.value)
              }}
            />
            <Slider
              name='slider-y' label='Position vertikal'
              min='-100' max='360' value={state.imageY}
              onChange={(e) => {
                callbacks.setImageY(e.target.value)
              }}
            />
          </div>
        </div>
      </div>
    </Frame>
  )
}

export default ImageEditor;
