import React from 'react'
import {Navbar} from "react-materialize";
import {Link} from "react-router-dom";
import {NavItem} from "react-bootstrap";

function Header() {
    return (
        <header>
            <Navbar
                alignLinks="right"
                brand={<a className="brand-logo" href="#">Cookbook</a>}
                id="mobile-nav"
                menuIcon={<>Menu</>}
            >
                <NavItem href="/">
                    <Link to="/">All recipes</Link>
                </NavItem>
                <NavItem href="components.html">
                    <Link to="/create">Add new recipe</Link>
                </NavItem>
            </Navbar>

        </header>
    )
}

export default Header