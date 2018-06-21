import React from 'react'

import style from './PostEditor.css'

import Constants from '../services/Constants'

import Frame from '../frame/Frame'
import Header from '../header/Header'
import Post from '../post/Post'

const PostEditor = ({ state, callbacks }) => {
  return (
    <Frame name={Constants.FRAMES.POST_EDITOR} state={state}>
      <Header callbacks={callbacks} />
      <Post state={state} callbacks={callbacks} />
      <div className='buttons'>
        <img src='/assets/btn-bg.png' alt='' title='' onClick={callbacks.navigate(Constants.FRAMES.BACKGROUND_EDITOR)} />
        <img src='/assets/btn-image.png' alt='' title='' onClick={callbacks.navigate(Constants.FRAMES.IMAGE_EDITOR)} />
        <img src='/assets/btn-text.png' alt='' title='' onClick={callbacks.navigate(Constants.FRAMES.TEXT_EDITOR)} />
        <img src='/assets/btn-cancel.png' alt='' title='' onClick={callbacks.resetState} />
        <img src='/assets/btn-upload.png' alt='' title='' onClick={callbacks.navigate(Constants.FRAMES.SUBMIT_EDITOR)} />
      </div>
    </Frame>
  )
}

export default PostEditor;
