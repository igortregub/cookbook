import React, {useEffect} from "react";
import {Card, CardPanel} from "react-materialize";
import {Link, useParams} from "react-router-dom"
import {getRecipe, getRecipeChild} from "../actions/recipeActions";
import {connect} from 'react-redux'
import Recipes from "../components/Recipes";

function ViewPage(props) {
    let {id} = useParams()

    useEffect(() => {
        props.getRecipe(id)
        props.getRecipeChild(id, props.children.pagination.currentPage)
    }, [id])

    const parentRecipe = props.parent.id ? (
        <div><b>Parent recipe: </b> <Link to={"/recipe/" + props.parent.id}>{props.parent.name}</Link></div>) : ''

    return (
        <>
            <CardPanel className="view-page">

                <Card title={props.name}>
                    {props.description}

                    <div className="date">
                        <b>Created at:</b> {props.createdDate}
                    </div>

                    {parentRecipe}
                </Card>

                <Link to={"/edit/" + id} className="btn red">Edit recipe</Link>
                <Link to={"/create/" + id} className="btn">Add child recipe</Link>

            </CardPanel>

            <Recipes
                hasChild={true}
                currentPage={props.children.pagination.currentPage}
                totalPages={props.children.pagination.totalPages}
                recipes={props.children.content}
                updateHundler={(page) => props.getRecipeChild(id, page)}
            />
        </>
    )
}

const mapDispatchToProps = {
    getRecipe,
    getRecipeChild
};

const mapStateToProps = state => ({
    name: state.recipe.recipe.name,
    description: state.recipe.recipe.description,
    createdDate: state.recipe.recipe.createdDate,
    parent: state.recipe.parent,
    children: state.recipe.children
})

export default connect(mapStateToProps, mapDispatchToProps)(ViewPage)
