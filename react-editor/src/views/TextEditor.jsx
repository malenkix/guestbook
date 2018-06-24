import React from 'react'

import style from './TextEditor.css'

import Config from '../services/Config'
import Constants from '../services/Constants'

import Frame from '../frame/Frame'
import Header from '../header/Header'
import Post from '../post/Post'
import Nav from '../nav/Nav'
import Input from '../input/Input'
import Slider from '../input/Slider'

const TextEditor = ({ state, callbacks }) => {
  const fontTabSelected = state.textActiveTab === Constants.TABS.TAB_TEXT_FONT
  const positionTabSelected = state.textActiveTab === Constants.TABS.TAB_TEXT_POSITION
  return (
    <Frame name={Constants.FRAMES.TEXT_EDITOR} state={state}>
      <Header icon={`${Config.ASSETS}/btn-text.png`} callbacks={callbacks} />
      <Post state={state} callbacks={callbacks} compact />
      <div className='view'>
        <Nav callbacks={callbacks} hideOk />
        <div className='content'>
          <div className='tabs'>
            <button
              className={fontTabSelected ? 'selected' : ''}
              onClick={() => callbacks.setTextActiveTab(Constants.TABS.TAB_TEXT_FONT)}
            >
              Font
            </button>
            <button
              className={positionTabSelected ? 'selected' : ''}
              onClick={() => callbacks.setTextActiveTab(Constants.TABS.TAB_TEXT_POSITION)}
            >
              Position
            </button>
          </div>
          <div className='selection'>
            {fontTabSelected &&
              <React.Fragment>
                <Input name='text' label='Text' maxLength='36' value={state.text}
                  onChange={(e) => { callbacks.setText(e.target.value) }} />
                <Slider
                  name='slider-size' label='Schriftgröße'
                  min='16' max='48' value={state.textSize}
                  onChange={(e) => { callbacks.setTextSize(e.target.value) }}
                />
                <Input name='color' label='Farbe' maxLength='7' value={state.textColor}
                  onChange={(e) => { callbacks.setTextColor(e.target.value) }} />
              </React.Fragment>
            }
            {positionTabSelected &&
              <React.Fragment>
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
                <Slider
                  name='slider-rotation' label='Rotation'
                  min='-180' max='180' step='5' value={state.textRotation}
                  ticks={['-180°', '0°', '+180°']}
                  onChange={(e) => { callbacks.setTextRotation(e.target.value) }}
                />
              </React.Fragment>
            }
          </div>
        </div>
      </div>
    </Frame>
  )
}

export default TextEditor;
