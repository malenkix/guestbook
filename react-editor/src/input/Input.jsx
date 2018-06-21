import React from 'react'

import style from './Input.css'

const Input = ({ name, label, maxLength, value, onChange }) => {
  return (
    <div className={`text-input ${name}`}>
      <div className='label'>
        <span>{label}</span>
      </div>
      <input name={name} type='text'
        maxLength={maxLength} value={value != null ? value : ''}
        onChange={onChange} />
    </div>
  )
}

export default Input
