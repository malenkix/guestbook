import React from 'react'

import style from './Modal.css'

const Modal = ({ className, hidden, children }) => {
  return (
    <div className={`modal ${className}`} hidden={hidden}>
      <div className='modal-content'>
        {children}
      </div>
    </div>
  )
}

export default Modal
