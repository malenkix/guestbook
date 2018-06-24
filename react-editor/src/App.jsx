import React from 'react'

import style from './App.css'

import State from './services/State'

import PostEditor from './views/PostEditor'
import BackgroundEditor from './views/BackgroundEditor'
import ImageEditor from './views/ImageEditor'
import TextEditor from './views/TextEditor'
import SubmitEditor from './views/SubmitEditor'

class App extends React.PureComponent {

  constructor(props) {
    super(props)
    State.setupState(this)
  }



  componentDidMount() {
    State.initState(this)
  }

  render() {
    return (
      <React.Fragment>
        <PostEditor state={this.state} callbacks={this.callbacks} />
        <BackgroundEditor state={this.state} callbacks={this.callbacks} />
        <ImageEditor state={this.state} callbacks={this.callbacks} />
        <TextEditor state={this.state} callbacks={this.callbacks} />
        <SubmitEditor state={this.state} callbacks={this.callbacks} />
      </React.Fragment>
    )
  }
}

export default App;
