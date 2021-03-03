import React, {useEffect} from "react";
import Recipes from "../components/Recipes";
import {getRecipes} from "../actions/recipeActions";
import {connect} from "react-redux";

function HomePage(props) {
    useEffect(() => {
        props.getRecipes(0)
    }, [])

    return (
        <>
            <Recipes
                hasChild={false}
                currentPage={props.pagination.currentPage}
                totalPages={props.pagination.totalPages}
                recipes={props.recipes}
                updateHundler={(page) => props.getRecipes(page)}
            />
        </>
    )
}

const mapDispatchToProps = {
    getRecipes
};

const mapStateToProps = state => ({
    recipes: state.recipe.recipes.content,
    pagination: state.recipe.recipes.pagination
})

export default connect(mapStateToProps, mapDispatchToProps)(HomePage)
