import React, {useEffect} from "react";
import {useHistory, useParams} from 'react-router-dom';

import EditComponent from "../components/EditComponent";
import {createRecipe} from "../actions/recipeActions";
import {connect} from "react-redux";

function CreatePage(props) {
    const history = useHistory();
    let {id} = useParams()

    useEffect(() => {
        if (props.updated) {
            history.push('/recipe/' + props.recipe.id)
        }
    }, [props.updated])

    return (
        <EditComponent saveHandler={(name, description) => props.createRecipe({
            parent: id,
            name: name,
            description: description
        })}/>
    )
}

const mapDispatchToProps = {
    createRecipe
};

const mapStateToProps = state => ({
    parent: state.recipe.parent,
    recipe: state.recipe.recipe,
    updated: state.recipe.recipeUpdated
})

export default connect(mapStateToProps, mapDispatchToProps)(CreatePage)