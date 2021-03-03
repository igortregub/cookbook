import {applyMiddleware, combineReducers, compose, createStore} from 'redux'
import thunk from 'redux-thunk'
import recipe from './reducers/recipesReducer'

const initialState = {}

const middleware = [thunk]

const devTools = (process.env.NODE_ENV === 'development' && window.__REDUX_DEVTOOLS_EXTENSION__)
    ? window.__REDUX_DEVTOOLS_EXTENSION__()
    : f => f

const store = createStore(
    combineReducers({recipe: recipe}),
    initialState,
    compose(
        applyMiddleware(...middleware),
        devTools
    )
)

export default store