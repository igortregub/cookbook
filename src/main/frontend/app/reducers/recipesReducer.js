import {
    GET_ALL_RECIPES,
    GET_RECIPE,
    GET_RECIPE_CHILD, RECIPE_CREATED, RECIPE_SAVED
} from '../actions/types'

const initialState = {
    recipe: {},
    recipeUpdated: false,
    recipes: {
        content: [],
        pagination: {
            currentPage: 0,
            totalPages: 0
        }
    },
    children: {
        content: [],
        pagination: {
            currentPage: 0,
            totalPages: 0
        }
    },
    parent: {
        id: null,
        name: null
    }
}

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_ALL_RECIPES:
            return {
                ...state,
                recipeUpdated: false,
                parent: {
                    id: null,
                    name: null
                },
                recipes: {
                    content: action.payload.content,
                    pagination: {
                        currentPage: action.payload.number,
                        totalPages: action.payload.totalPages
                    }
                },
            }
        case RECIPE_CREATED:
        case RECIPE_SAVED:
            return {
                ...state,
                recipe: action.payload,
                recipeUpdated: true,
                children: {
                    content: [],
                    pagination: {
                        currentPage: 0,
                        totalPages: 0
                    }
                },
                parent: {
                    id: action.payload.parent,
                    name: action.payload.parentName
                }
            }
        case GET_RECIPE:
            return {
                ...state,
                recipe: action.payload,
                recipeUpdated: false,
                children: {
                    content: [],
                    pagination: {
                        currentPage: 0,
                        totalPages: 0
                    }
                },
                parent: {
                    id: action.payload.parent,
                    name: action.payload.parentName
                }
            }
        case GET_RECIPE_CHILD:
            return {
                ...state,
                recipeUpdated: false,
                children: {
                    content: action.payload.content,
                    pagination: {
                        currentPage: action.payload.number,
                        totalPages: action.payload.totalPages
                    }
                },
            }
        default:
            return state
    }
};