import React from 'react'

import style from './Post.css'

import Utils from '../services/Utils'

import Image from './Image'
import Text from './Text'

const Post = ({
  state = {}, callbacks = {}, upload = false, compact = false, readonly = false
}) => {
  const showImage = state.backgroundActiveTab === 'image' && state.backgroundImageIndex != null
  const inline = {
    cursor: upload && 'pointer',
    backgroundColor: state.backgroundColor || '#000',
    backgroundImage: showImage ? `url(${state.backgroundImage})` : ''
  }
  return (
    <div className='post'>
      <div className='screen' style={inline}
        onClick={upload ? () => document.getElementById('image-upload').click() : null}>
        {upload &&
          <input id='image-upload'
            accept='image/*' type="file" value={state.imageFile ? state.imageFile.name : ''} hidden
            onChange={Utils.delegateFile((file, data, success) => {
              if (success) callbacks.updateState({ image: data, imageFile: file })
            })} />}
        {state.image && <Image state={state} />}
        {state.text && <Text state={state} />}
      </div>
      <div className='sub'>
        {compact ? <span></span> :
          readonly ? <span>{state.postMessage}</span> :
            <input
              type='text' placeholder='Antippen und hier Text eingeben.'
              value={state.postMessage} maxLength='20'
              onChange={e => callbacks.setPostMessage(e.target.value)}
            />}
      </div>
    </div>
  )
}

export default Post;
