import React from 'react'

import style from './SubmitEditor.css'

import Config from '../services/Config'
import Constants from '../services/Constants'
import Service from '../services/Service'

import Frame from '../frame/Frame'
import Header from '../header/Header'
import Post from '../post/Post'
import Nav from '../nav/Nav'
import Input from '../input/Input'

const SubmitEditor = ({ state, callbacks }) => {
  return (
    <Frame name={Constants.FRAMES.SUBMIT_EDITOR} state={state}>
      <Header icon={`${Config.ASSETS}/btn-upload.png`} callbacks={callbacks} />
      <Post state={state} callbacks={callbacks} readonly />
      <div className='view'>
        <Nav callbacks={callbacks} onOkClick={() => {

          let promise = Promise.resolve()

          if (state.image) {
            promise = promise.then(() => Service.postImage(state.imageFile))
          }

          promise = promise.then(
            imageId => Service.sendPost(state, imageId || '')
          ).then(text => callbacks.updateState({
            hideModalSubmit: false, modalSubmitMessage: text || 'Vielen Dank!'
          }))
        }} />
        <div className='content'>
          <Input name='by' label='Wer schickt die Grüße?' maxLength='36' value={state.postBy}
            onChange={(e) => { callbacks.setPostBy(e.target.value) }} />
        </div>
      </div>
    </Frame>
  )
}

export default SubmitEditor;
