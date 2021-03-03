import axios from 'axios'

import {
    GET_ALL_RECIPES,
    GET_RECIPE, GET_RECIPE_CHILD, RECIPE_CREATED, RECIPE_SAVED
} from './types'

export const getRecipes = (current = 0, size = 10) => dispatch => {
    axios.get('/api/recipes/?page=' + current + '&size=' + size + '&sort=name,ASC')
        .then(res => {
            dispatch({
                type: GET_ALL_RECIPES,
                payload: res.data
            })
        })
}

export const getRecipeChild = (id, current = 0, size = 10) => dispatch => {
    axios.get('/api/recipes/' + id + '/child?page=' + current + '&size=' + size + '&sort=name,ASC')
        .then(res => {
            dispatch({
                type: GET_RECIPE_CHILD,
                payload: res.data
            })
        })
}

export const getRecipe = recipeId => dispatch => {
    axios.get('/api/recipes/' + recipeId)
        .then(res => {
            dispatch({
                type: GET_RECIPE,
                payload: res.data
            })
        })
}

export const createRecipe = (recipe) => dispatch => {
    axios.post('/api/recipes/', recipe)
        .then(res => {
            dispatch({
                type: RECIPE_CREATED,
                payload: res.data
            })
        })
}

export const saveRecipe = (recipe) => dispatch => {
    axios.put('/api/recipes/', recipe)
        .then(res => {
            dispatch({
                type: RECIPE_SAVED,
                payload: res.data
            })
        })
}