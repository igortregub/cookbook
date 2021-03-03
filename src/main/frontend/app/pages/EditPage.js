import React, {useEffect} from "react";
import EditComponent from "../components/EditComponent";
import {useHistory, useParams} from "react-router-dom";
import {getRecipe, saveRecipe} from "../actions/recipeActions";
import {connect} from "react-redux";

function EditPage(props) {
    const history = useHistory();
    let {id} = useParams()

    useEffect(() => {
        props.getRecipe(id)
    }, [id])

    useEffect(() => {
        if (props.updated) {
            history.push('/recipe/' + id)
        }
    }, [props.updated])

    return (
        <EditComponent
            name={props.recipe.name}
            description={props.recipe.description}
            saveHandler={(name, description) => props.saveRecipe({id: id, name: name, description: description})}
        />
    )
}

const mapDispatchToProps = {
    getRecipe,
    saveRecipe
};

const mapStateToProps = state => ({
    recipe: state.recipe.recipe,
    updated: state.recipe.recipeUpdated
})

export default connect(mapStateToProps, mapDispatchToProps)(EditPage)