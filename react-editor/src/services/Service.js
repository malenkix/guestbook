import Config from './Config'

function fetchGET(path, defaults) {
  return fetch(`${Config.API}${path}`).then(
    res => {
      if (!res.ok) throw new Error(`GET ${path} failed`)
      return res.json() || defaults
    },
    err => defaults
  ).catch(err => defaults)
}

function fetchPOST(path, data, headers) {
  return fetch(`${Config.API}${path}`, {
    method: 'POST', headers, body: data
  }).then(
    res => {
      if (!res.ok) throw new Error(`POST ${path} failed`)
      return res.text() || ''
    },
    err => ''
  ).catch(err => '')
}

function getBackgroundColors() {
  return fetchGET('/colors', ['#000', '#f00', '#0f0', '#00f', '#ff0', '#0ff', '#f0f', '#fff'])
}

function getBackgroundImages() {
  return fetchGET('/backgrounds', [
    `${Config.ASSETS}/bg1.jpeg`,
    `${Config.ASSETS}/bg2.jpeg`,
    `${Config.ASSETS}/bg3.jpeg`,
    `${Config.ASSETS}/bg4.jpeg`
  ])
}

function getTextFonts() {
  return fetchGET('/fonts', ['sans-serif'])
}

function getColorsImagesAndFonts() {
  return getBackgroundColors().then(
    colors => getBackgroundImages().then(
      images => getTextFonts().then(
        fonts => [colors, images, fonts]
      )
    )
  )
}

function postImage(file) {
  const formData = new FormData()
  formData.append('image', file, file.name)
  return fetchPOST('/posts/upload', formData)
}

function sendPost(state, imageId) {
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
      font: state.textFont
    },
    background: {
      isImage: state.backgroundImageIndex != null,
      color: state.backgroundColor,
      imageId: state.backgroundImageIndex
    },
    subtext: state.postMessage,
    wishes: state.postAdditional,
    name: state.postBy
  }

  return fetchPOST('/posts', JSON.stringify(data), {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  })
}

export default {
  getBackgroundColors,
  getBackgroundImages,
  getTextFonts,
  getColorsImagesAndFonts,
  postImage,
  sendPost
}
