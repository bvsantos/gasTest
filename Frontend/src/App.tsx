import React, { Component } from 'react';
import {Provider} from 'react-redux';
import {HashRouter, Route, Switch} from 'react-router-dom';
import Store from './Components/Common/Utils/Redux/Store';

// PathsInterface
import {PathsLabel} from "./Components/Common/Utils/Paths";
import Menu from '../src/Components/Common/Menu/Menu';

// Styles
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

// Components
import Login from './Components/Common/User/Login';
import Register from './Components/Common/User/RegisterStudent';
import Calls from './Components/Calls/CallList';
import CallDetails from './Components/Calls/CallDetails';
import DataItems from './Components/DataItems/DataItemsList';
import DataItemDetails from './Components/DataItems/DataItemDetails';
import PanelDetails from './Components/Panels/PanelDetails';

export default class App extends Component{

  render() {
      return (
            <HashRouter>
                  <Provider store={Store}>
                  <Menu/>
                        <Switch>
                              <Route exact path = {PathsLabel.Login} component = {Login}/>
                              <Route exact path = {PathsLabel.Register} component = {Register}/>
                              <Route exact path = {PathsLabel.Calls} component = {Calls}/>
                              <Route exact path = {PathsLabel.Calls + PathsLabel.Details} component = {CallDetails}/>
                              <Route exact path = {PathsLabel.DataItems} component = {DataItems}/>
                              <Route exact path = {PathsLabel.DataItems + PathsLabel.Details} component = {DataItemDetails}/>
                              <Route exact path = {PathsLabel.Panels + PathsLabel.Details} component = {PanelDetails}/>
                              {/**<Route exact path = '/' component = {Home}/>*/}
                        </Switch>
                  </Provider>
            </HashRouter>
    );
}

}
