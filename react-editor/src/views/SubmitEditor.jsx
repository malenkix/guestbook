import React from 'react'

import style from './SubmitEditor.css'

import Constants from '../services/Constants'

import Frame from '../frame/Frame'
import Header from '../header/Header'
import Post from '../post/Post'
import Nav from '../nav/Nav'
import Slider from '../input/Slider'

const SubmitEditor = ({ state, callbacks }) => {
  return (
    <Frame name={Constants.FRAMES.SUBMIT_EDITOR} state={state}>
      <Header icon='/assets/btn-upload.png' callbacks={callbacks} />
      <Post state={state} callbacks={callbacks} readonly />
      <div className='view'>
        <div></div>
        <Nav callbacks={callbacks} onOkClick={() => {

          let promise = Promise.resolve()

          if (state.image) {

            const formData = new FormData()
            formData.append('file', state.imageFile)
            formData.append('image', state.imageFile)

            promise = promise.then(() => fetch('/posts/upload', {
              method: 'POST',
              body: formData
            })).then(response => response.ok ? response.text() : '')
          }

          promise = promise.then(imageId => {
            const data = {
              image: {
                file: imageId,
                posX: state.imageX,
                posY: state.imageY,
                width: state.imageWidth,
                height: state.imageHeight,
                rotation: state.imageRotation
              },
              message: {
                content: state.text,
                color: state.textColor,
                size: state.textSize,
                posX: state.textX,
                posY: state.textY,
                rotation: state.textRotation,
                font: 'Arial-Black'
              },
              background: {
                isImage: state.backgroundImageIndex != null,
                color: state.backgroundColor,
                imageId: state.backgroundImageIndex
              },
              subtext: state.postMessage,
              wishes: '',
              name: 'anonymous'
            }

            return fetch('/posts', {
              method: 'POST',
              headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
              },
              body: JSON.stringify(data)
            })
          }).then(() => callbacks.resetState())
        }} />
      </div>
    </Frame>
  )
}

export default SubmitEditor;
