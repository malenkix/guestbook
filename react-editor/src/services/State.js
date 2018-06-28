import Constants from './Constants'
import Service from './Service'

function createState() {
  return {
    hideModalReset: true,
    hideModalSubmit: true,
    modalSubmitMessage: '',
    uiActiveFrame: Constants.FRAMES.POST_EDITOR,
    postMessage: '',
    backgroundActiveTab: Constants.TABS.TAB_BG_COLOR,
    backgroundColors: [],
    backgroundColorIndex: undefined,
    backgroundColor: undefined,
    backgroundImages: [],
    backgroundImageIndex: undefined,
    backgroundImage: undefined,
    image: undefined,
    imageFile: undefined,
    imageX: 0,
    imageY: 0,
    imageWidth: 300,
    imageHeight: 310,
    imageScale: 100,
    imageRotation: 0,
    text: '',
    textX: 0,
    textY: 0,
    textSize: 16,
    textRotation: 0,
    textColor: '#000',
    textFont: 'sans-serif',
    textFontIndex: undefined,
    textFonts: [],
    textActiveTab: Constants.TABS.TAB_TEXT_FONT,
    postBy: '',
    postAdditional: ''
  }
}

function initState(component) {
  component.setState(createState(), () => {
    Service.getColorsImagesAndFonts().then(values => {

      const colors = values[0]
      const images = values[1]
      const fonts = values[2]

      component.setState({
        backgroundColors: colors,
        backgroundImages: images,
        textFont: fonts[0],
        textFontIndex: 0,
        textFonts: fonts,
      })
    })
  })
}

function capitalize(string) {
  return string.charAt(0).toUpperCase() + string.slice(1);
}

function createStateUpdater(component) {
  return values => component.setState({ ...values })
}

function createStateSetter(component, key) {
  return value => component.setState({ [key]: value })
}

function setupState(component) {
  component.state = createState();
  component.callbacks = component.callbacks || {}
  component.callbacks.resetState = () => {
    document.getElementById('image-upload').value = null
    initState(component)
  }
  component.callbacks.updateState = createStateUpdater(component)
  component.callbacks.showStateAndCallbacks = () => {
    const jsonState = JSON.stringify(component.state, (key, val) => {
      if (key === 'image' || key === 'imageFile') {
        return val.slice(0, 10) || ''
      }
      return val
    })
    const jsonCallbacks = JSON.stringify(component.callbacks, (key, val) => {
      if (typeof val === 'function') {
        return ''
      }
      return val
    })
    console.info(jsonState)
  }
  Object.keys(component.state).forEach(prop => {
    component.callbacks[`set${capitalize(prop)}`] = createStateSetter(component, prop)
  })
  component.callbacks.navigate = (to) => () => component.callbacks.setUiActiveFrame(to)
}

export default {
  createState,
  initState,
  setupState
}
