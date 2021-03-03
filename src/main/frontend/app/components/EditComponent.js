import {Button, CardPanel, TextInput} from "react-materialize";
import React, {useState} from "react";
import {Form} from "react-bootstrap";

function EditComponent(props) {
    const [name, setName] = useState(props.name);
    const [description, setDescription] = useState(props.description);

    const saveHandler = (e) => {
        e.preventDefault()

        props.saveHandler(name, description)
    }

    return (
        <CardPanel>
            <Form onSubmit={saveHandler}>
                <TextInput
                    onChange={e => setName(e.target.value)}
                    id="recipe-name"
                    label="Name"
                    name="name"
                    defaultValue={props.name}
                    validate
                    required="required"
                    aria-required="true"

                />
                <TextInput
                    onChange={e => setDescription(e.target.value)}
                    id="recipe-description"
                    label="Description"
                    name="description"
                    defaultValue={props.description}
                />

                <Button>Save</Button>
            </Form>
        </CardPanel>
    )
}

export default EditComponent