import {Table} from "react-bootstrap";
import {CardPanel, Pagination} from "react-materialize";
import React, {useEffect} from "react";
import {Link} from "react-router-dom";

function Recipes(props) {

    const onSelect = (current) => {
        props.updateHundler(current - 1)
    }

    useEffect(() => {
        onSelect(0)
    }, [])

    const recipes = props.recipes.map(recipe => (
        <tr key={recipe.id}>
            <td>{recipe.id}</td>
            <td><Link to={"/recipe/" + recipe.id}>{recipe.name}</Link></td>
            <td>{recipe.description}</td>
        </tr>
    ))

    return (
        <>
            {props.recipes.length > 0 ? <CardPanel>
                {props.hasChild === true ? <h5>Child recipes</h5> : ''}

                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Created</th>
                    </tr>
                    </thead>
                    <tbody>
                    {recipes}
                    </tbody>
                </Table>

                <Pagination
                    activePage={props.currentPage + 1}
                    items={props.totalPages}
                    leftBtn="<"
                    maxButtons={5}
                    rightBtn=">"
                    onSelect={onSelect}
                />
            </CardPanel> : ''}</>

    )
}

export default Recipes