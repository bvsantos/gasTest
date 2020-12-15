import React, { Component } from 'react';

export default class StudentDetail extends Component<any, any>{
    constructor(props: any) {
        super(props)
        this.state = {
            institution_id:0,
            name:'',
            email:'',
            address:'',
            password:'',
        }
        // Functions
    }

}