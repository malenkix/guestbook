import React from 'react'

import style from './ResetModal.css'

import Config from '../services/Config'

import Modal from './Modal'

const ResetModal = ({ state = {}, callbacks = {} }) => {
  return (
    <Modal key='reset-modal' className='reset-modal' hidden={state.hideModalReset}>
      <div><span>Achtung, hiermit setzt Du alles wieder auf Anfang!</span></div>
      <div className='buttons'>
        <img src={`${Config.ASSETS}/btn-ok.png`} alt='' title='' onClick={callbacks.resetState} />
        <img src={`${Config.ASSETS}/btn-back.png`} alt='' title='' onClick={() => callbacks.setHideModalReset(true)} />
      </div>
    </Modal>
  )
}

export default ResetModal
