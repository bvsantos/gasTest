import React, { Component } from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { MenuItem } from './MenuItem';
import { StorageNames, PathsLabel } from '../Utils/Paths';

export default class Menu extends Component {

    /**Verifies if the menuItem is the active one */
    getActiveMenuItem(id: string) {
        var className = "";
        if (sessionStorage.getItem(StorageNames.activeMenuItem) === id)
            className = "active";
        return className;
    }

    setActiveMenuItem(id: string): any {
        sessionStorage.setItem(StorageNames.activeMenuItem, id);
    }

    /** Renders the menu with the layout for anonymous users(not logged) */
    renderNotLogged() {
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="pl-4">
                        <MenuItem id="Home" href={PathsLabel.Home} label="Home" isActive={this.getActiveMenuItem("Home")} onClick={() => this.setActiveMenuItem("Home")}></MenuItem>
                        <MenuItem id="Register" href={PathsLabel.Register} label="Register" isActive={this.getActiveMenuItem("Register")} onClick={() => this.setActiveMenuItem("Register")}></MenuItem>
                        <MenuItem id="Login" href={PathsLabel.Login} label="Login" isActive={this.getActiveMenuItem("Login")} onClick={() => this.setActiveMenuItem("Login")}></MenuItem>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        );
    }

    /** Renders the menu with the layout prepared for the logged Sponsor */
    renderSponsorLogged() {
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="pl-4">
                        <MenuItem id="Calls" href={PathsLabel.Calls} label="Calls"></MenuItem>
                        <MenuItem id="DataItems" href={PathsLabel.DataItems} label="DataItems"></MenuItem>
                    </Nav>
                    <Nav className="ml-auto mr-4"><MenuItem id="Logout" href="" label="Logout"></MenuItem></Nav>
                </Navbar.Collapse>
            </Navbar>
        );
    }

    /** Renders the menu with the layout prepared for the logged Reviewer */
    renderReviewerLogged() {
        return (
            <div>
                <MenuItem id="Logout" href="" label="Logout"></MenuItem>
            </div>
        );
    }

    /** Renders the menu with the layout prepared for the logged Student */
    renderStudentLogged() {
        return (
            <div>
                <MenuItem id="Logout" href="" label="Logout"></MenuItem>
            </div>
        );
    }

    renderTest() {
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="pl-4">
                        <MenuItem id="Login" href={PathsLabel.Login} label="Login"></MenuItem>
                        <MenuItem id="Register" href={PathsLabel.Register} label="Register"></MenuItem>
                        <MenuItem id="Calls" href={PathsLabel.Calls} label="Calls"></MenuItem>
                        <MenuItem id="DataItems" href={PathsLabel.DataItems} label="DataItems"></MenuItem>
                    </Nav>
                    <Nav className="ml-auto mr-4"><MenuItem id="Logout" href="" label="Logout"></MenuItem></Nav>
                </Navbar.Collapse>
            </Navbar>
        );
    }

    render() {
        const isLogged = true; // session email ou assim
        const role = 0; // 0 = sponsor, 1 = reviewer, 2 = student
        /*if(!isLogged)
            return this.renderNotLogged();
        else if(role === 0)
            return this.renderSponsorLogged();
        else if(role === 1)
            return this.renderReviewerLogged();
        return this.renderStudentLogged();
        */
        return this.renderTest();
    }

}