import React from 'react'

import style from './Slider.css'

const Slider = ({ name, label, min = 0, max = 100, step = 1, value, onChange, ticks }) => {
  return (
    <div className={`slider ${name}`}>
      <div className='label'>
        <span>{label}</span>
      </div>
      <input
        name={name} type='range'
        min={min} max={max} step={step}
        value={value != null ? value : (max - min) / 2}
        onChange={onChange}
      />
      <div className='ticks'>
        {ticks && ticks.map((value, index) => <span key={index}>{value}</span>)}
      </div>
    </div>
  )
}

export default Slider
