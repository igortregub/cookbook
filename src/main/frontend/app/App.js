import React from "react";
import {HashRouter, Route, Switch} from "react-router-dom"
import {Provider} from 'react-redux'

import {Container} from "react-bootstrap";
import 'materialize-css/dist/css/materialize.min.css'
import './App.css';
import 'materialize-css';

import Header from './components/Header'
import HomePage from "./pages/HomePage";
import CreatePage from "./pages/CreatePage";
import EditPage from "./pages/EditPage";
import ViewPage from "./pages/ViewPage";
import store from "./store";


function App() {

    return (
        <Provider store={store}>
            <HashRouter>
                <Header/>

                <Container className="content">
                    <Switch>
                        <Route path="/create/:id">
                            <CreatePage/>
                        </Route>
                        <Route path="/create">
                            <CreatePage/>
                        </Route>
                        <Route path="/edit/:id">
                            <EditPage/>
                        </Route>
                        <Route path="/recipe/:id">
                            <ViewPage/>
                        </Route>
                        <Route path="/">
                            <HomePage/>
                        </Route>
                    </Switch>
                </Container>
            </HashRouter>
        </Provider>
    )
}

export default App;