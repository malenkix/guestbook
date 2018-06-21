import Constants from './Constants'

function createState() {
  return {
    uiActiveFrame: Constants.FRAMES.POST_EDITOR,
    postMessage: '',
    backgroundActiveTab: Constants.TABS.TAB_BG_COLOR,
    backgroundColors: ['#f00', '#0f0', '#00f', '#ff0', '#0ff', '#f0f', '#fff', '#000'],
    backgroundColorIndex: undefined,
    backgroundColor: '',
    backgroundImages: ['/assets/bg1.jpeg', '/assets/bg2.jpeg', '/assets/bg3.jpeg', '/assets/bg4.jpeg'],
    backgroundImageIndex: undefined,
    backgroundImage: '',
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
    textActiveTab: Constants.TABS.TAB_TEXT_FONT
  }
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
  component.callbacks.resetState = () => component.setState(createState())
  component.callbacks.updateState = createStateUpdater(component)
  component.callbacks.showStateAndCallbacks = () => {
    const jsonState = JSON.stringify(component.state)
    const jsonCallbacks = JSON.stringify(component.callbacks, (key, val) => {
      if (typeof val === 'function') {
        return ''
      }
      return val
    })
    console.info(`State ${jsonState}, Callbacks ${jsonCallbacks}!`)
  }
  Object.keys(component.state).forEach(prop => {
    component.callbacks[`set${capitalize(prop)}`] = createStateSetter(component, prop)
  })
  component.callbacks.navigate = (to) => () => component.callbacks.setUiActiveFrame(to)
}

export default {
  createState,
  setupState
}
