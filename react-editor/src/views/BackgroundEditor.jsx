import React from 'react'

import style from './BackgroundEditor.css'

import Constants from '../services/Constants'

import Frame from '../frame/Frame'
import Header from '../header/Header'
import Nav from '../nav/Nav'
import FancyBox from '../box/FancyBox'

const BackgroundEditor = ({ state, callbacks }) => {
  const colorTabSelected = state.backgroundActiveTab === Constants.TABS.TAB_BG_COLOR
  const imageTabSelected = state.backgroundActiveTab === Constants.TABS.TAB_BG_IMAGE
  return (
    <Frame name={Constants.FRAMES.BACKGROUND_EDITOR} state={state}>
      <Header icon='/assets/btn-bg.png' callbacks={callbacks} />
      <div className='view'>
        <Nav callbacks={callbacks} />
        <div className='content'>
          <div className='tabs'>
            <button
              className={colorTabSelected ? 'selected' : ''}
              onClick={() => callbacks.setBackgroundActiveTab(Constants.TABS.TAB_BG_COLOR)}
            >
              Farbe
            </button>
            <button
              className={imageTabSelected ? 'selected' : ''}
              onClick={() => callbacks.setBackgroundActiveTab(Constants.TABS.TAB_BG_IMAGE)}
            >
              Grafik
            </button>
          </div>
          <div className='selection'>
            {colorTabSelected &&
              state.backgroundColors.map((c, index) =>
                <FancyBox
                  key={index} color={c}
                  selected={state.backgroundColorIndex === index}
                  onClick={() => callbacks.updateState({ backgroundColorIndex: index, backgroundColor: c })}
                />)}
            {imageTabSelected &&
              state.backgroundImages.map((i, index) =>
                <FancyBox
                  key={index} image={i}
                  selected={state.backgroundImageIndex === index}
                  onClick={() => callbacks.updateState({ backgroundImageIndex: index, backgroundImage: i })}
                />)}
          </div>
        </div>
      </div>
    </Frame>
  )
}

export default BackgroundEditor;
