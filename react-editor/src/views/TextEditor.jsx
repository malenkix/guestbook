import React from 'react'

import style from './TextEditor.css'

import Constants from '../services/Constants'

import Frame from '../frame/Frame'
import Header from '../header/Header'
import Post from '../post/Post'
import Nav from '../nav/Nav'
import Slider from '../input/Slider'

const TextEditor = ({ state, callbacks }) => {
  return (
    <Frame name={Constants.FRAMES.TEXT_EDITOR} state={state}>
      <Header icon='/assets/btn-text.png' callbacks={callbacks} />
      <Post state={state} callbacks={callbacks} compact />
      <div className='view'>
        <Nav callbacks={callbacks} />
        <div className='content'>
          <div className='line'>
            <input
              className='input' name='input-text'
              type='text' maxLength='36' value={state.text}
              onChange={(e) => { callbacks.setText(e.target.value) }}
            />
            <Slider
              name='slider-size' label='Schriftgröße'
              min='16' max='48' value={state.textSize}
              onChange={(e) => { callbacks.setTextSize(e.target.value) }}
            />
          </div>
          <div className='line'>
            <Slider
              name='slider-x' label='Position horizontal'
              min='-100' max='350' value={state.textX}
              onChange={(e) => { callbacks.setTextX(e.target.value) }}
            />
            <Slider
              name='slider-y' label='Position vertikal'
              min='-100' max='360' value={state.textY}
              onChange={(e) => { callbacks.setTextY(e.target.value) }}
            />
          </div>
          <div className='line'>
            <Slider
              name='slider-rotation' label='Rotation'
              min='-180' max='180' step='5' value={state.textRotation}
              ticks={['-180°', '0°', '+180°']}
              onChange={(e) => { callbacks.setTextRotation(e.target.value) }}
            />
            <input
              className='input' name='input-color'
              type='text' maxLength='7' value={state.textColor}
              onChange={(e) => { callbacks.setTextColor(e.target.value) }}
            />
          </div>
        </div>
      </div>
    </Frame>
  )
}

export default TextEditor;
