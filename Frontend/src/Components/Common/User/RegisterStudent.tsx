import React, { Component } from 'react';
import axios from 'axios';
import { Form, Button, Col, Card } from 'react-bootstrap';
import { UserStruct } from '../Utils/StructTypes';
import { ServicePathsLabel, PathsLabel } from '../Utils/Paths';

export default class Register extends Component<any, any>{
    constructor(props: any) {
        super(props)
        this.state = {
            institutions: [{ name: 'A', id: 1 }, { name: 'B', id: 2 }],// List of all Institutions
            institution_id: 0,
            name: '',
            email: '',
            address: '',
            password: '',
            confirmation: ''
        }
        this.handleRegister = this.handleRegister.bind(this);
        this.handleInstitutionChange = this.handleInstitutionChange.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    async handleRegister() {
        if (this.state.password !== this.state.confirmation) {
            console.log("passowrd and cofirmation are different!");
            return;
        }

        let json = {
            name: this.state.name,
            email: this.state.email,
            address: this.state.address,
            institutionId: this.state.institutionId,
            password: this.state.password
        }

        await axios.post(ServicePathsLabel.Api + ServicePathsLabel.Students, json)
            .then(response => {
                console.log(response);
                window.location.hash = PathsLabel.Login;
            })
            .catch(error => {
                console.log("Error:" + error);
            })
    }

    handleInstitutionChange(e: React.FormEvent<HTMLSelectElement>) {
        const target = e.target as HTMLSelectElement;
        this.setState({ institution_id: target.value });
    }

    onChange(e: any) {
        const target = e.target;
        const value = target.value;
        const name = target.name;
        this.setState({ ...this.state, [name]: value });
    }

    render() {
        return (
            <div className="p-5 w-50 mx-auto">
                <Card>
                    <Card.Header className="h2">Register now</Card.Header>
                    <Card.Body>
                        <Form className="p-4">
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridName">
                                    <Form.Label>Name</Form.Label>
                                    <Form.Control type="text" name="name" placeholder="Enter full name" onChange={this.onChange} value={this.state.name} />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridEmail">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control type="email" name="email" placeholder="Enter email" onChange={this.onChange} value={this.state.email} />
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridAddress">
                                    <Form.Label>Address</Form.Label>
                                    <Form.Control type="text" name="address" placeholder="Enter address" onChange={this.onChange} value={this.state.address} />
                                </Form.Group>
                                <Form.Group as={Col} onChange={this.handleInstitutionChange}>
                                    <Form.Label>Institution</Form.Label>
                                    <Form.Control as="select" className="mr-sm-2" id="inlineFormCustomSelect" custom onChange={this.onChange} value={this.state.institutionId}>
                                        {this.state.institutions.map((institution: any) => {
                                            return (
                                                <option key={institution.id} value={institution.id}>{institution.name}</option>
                                            );
                                        })}
                                    </Form.Control>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridPassword">
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control type="password" name="password" placeholder="Enter password" onChange={this.onChange} value={this.state.password} />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridConfirmationPassword">
                                    <Form.Label>Confirmation Password</Form.Label>
                                    <Form.Control type="password" name="confirmation" placeholder="Enter confirmation" onChange={this.onChange} value={this.state.confirmation} />
                                </Form.Group>
                            </Form.Row>
                            <Form.Group id="formGridCheckbox">
                                <Form.Check type="checkbox" label="Remind me" />
                            </Form.Group>
                            <Button variant="primary" size="lg" block onClick={this.handleRegister}>
                                Register now
                            </Button>
                        </Form>
                    </Card.Body>
                </Card>
            </div>

        );
    }

}