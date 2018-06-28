import React from 'react'

import style from './SubmitModal.css'

import Config from '../services/Config'

import Modal from './Modal'

const SubmitModal = ({ state = {}, callbacks = {} }) => {
  return (
    <Modal key='submit-modal' className='submit-modal' hidden={state.hideModalSubmit}>
      <div><span>{state.modalSubmitMessage}</span></div>
      <div className='buttons'>
        <img src={`${Config.ASSETS}/btn-ok.png`} alt='' title='' onClick={callbacks.resetState} />
      </div>
    </Modal>
  )
}

export default SubmitModal
