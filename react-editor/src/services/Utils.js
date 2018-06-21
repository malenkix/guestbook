function delegateValue(callback) {
  return e => callback(e.target.value)
}

function delegateFile(callback) {
  return e => {
    const files = e.target.files
    if (FileReader && files && files.length) {
      const fr = new FileReader()
      fr.onload = () => callback(files[0], fr.result, true)
      fr.onerror = () => callback(files[0], undefined, false)
      fr.readAsDataURL(files[0])
    }
  }
}

export default {
  delegateValue,
  delegateFile
}
