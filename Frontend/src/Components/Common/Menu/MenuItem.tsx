import React, { FunctionComponent, MouseEventHandler  } from 'react'
import {Nav} from 'react-bootstrap';

type MenuItemProps = {
    id:string,
    href:string,
    icon?:string,
    label:string,
    isActive?:string // holds the css for active menuItem or not
    onClick?:MouseEventHandler
}

export const MenuItem: FunctionComponent<MenuItemProps> = (props) => 
    <Nav.Link id={props.id} href={"#" + props.href} className={props.isActive} onClick={props.onClick}>
        {props.icon}{props.label}    
    </Nav.Link>
    
