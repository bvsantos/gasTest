import React, { Component } from 'react';
import axios from 'axios';
import { Form, Button, Card } from 'react-bootstrap';
import { ServicePathsLabel, PathsLabel } from '../Utils/Paths';

export default class Login extends Component<any, any>{
    constructor(props: any) {
        super(props)
        this.state = {
            username: '',
            password: ''
        }
        this.handleLogin = this.handleLogin.bind(this);
        this.onChange = this.onChange.bind(this);
        this.test = this.test.bind(this)
    }

    async handleLogin() {
        // meter no redux
        // const tokenID = sessionStorage.getItem('tokenID');
        // Authorization: 'Bearer ' + tokenID,

        let json = {
            username: this.state.username,
            password: this.state.password
        }

        fetch(ServicePathsLabel.Login, {
            method: "POST",
            body: JSON.stringify(json)
        }).then(response => {
            const auth: string = response.headers.get("Authorization")?.replace("Bearer", "")!;
            console.log(auth)
            sessionStorage.setItem('auth', auth);
            // function that redirects the user depending of the role
        }).catch(error => {
            console.log(error)
        })

    }

    async test() {
        console.log(sessionStorage.getItem('auth'));
        var myHeaders = new Headers();
        myHeaders.append('Authorization', sessionStorage.getItem('auth')!);

        /*fetch("applications/taste", {
            method: "GET",
            headers: myHeaders
        }).then(response => {
            console.log(response)
        }).catch(error => {
            console.log("Error" + error)
        })*/

        await axios.get("/applications/taste", {
            headers: {
                Authorization: sessionStorage.getItem('auth')
            }
        }).then(response => {
            console.log(response)
        }).catch(error => {
            console.log(error.response)
        })
    }

    onChange(e: any) {
        const target = e.target;
        const value = target.value;
        const name = target.name;
        console.log(value);
        this.setState({ ...this.state, [name]: value });
    }

    render() {
        return (
            <div className="p-5 w-25 mx-auto">
                <Card>
                    <Card.Header className="h2">Log in to your account</Card.Header>
                    <Card.Body>
                        <Form>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Username</Form.Label>
                                <Form.Control type="text" name="username" placeholder="Username or email" onChange={this.onChange} value={this.state.username} />
                            </Form.Group>
                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" name="password" placeholder="Password" onChange={this.onChange} value={this.state.password} />
                            </Form.Group>
                            <Form.Row>
                                <Form.Group className="ml-2" controlId="formBasicCheckbox">
                                    <Form.Check type="checkbox" label="Remember me" />
                                </Form.Group>
                                <Form.Group className="float-right ml-auto mr-1">
                                    <a href={"#" + PathsLabel.ForgotPassword}>Forgot password?</a>
                                </Form.Group>
                            </Form.Row>
                            <Button variant="primary" size="lg" block onClick={this.handleLogin}>
                                Sign In
                            </Button>
                            <Button variant="primary" size="lg" block onClick={this.test}>
                                Test
                            </Button>
                        </Form>
                    </Card.Body>
                </Card>
            </div>
        );
    }
}
