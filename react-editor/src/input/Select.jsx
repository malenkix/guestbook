import React from 'react'

import style from './Select.css'

const Select = ({ name, label, options, value, onChange }) => {
  return (
    <div className={`select ${name}`}>
      <div className='label'>
        <span>{label}</span>
      </div>
      <select name={name} value={value} onChange={onChange}>
        {options &&
          Object.keys(options).map((k, i) => <option key={i} value={options[k]}>{k}</option>)
        }
      </select>
    </div>
  )
}

export default Select
