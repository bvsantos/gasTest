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

        await axios.post(ServicePathsLabel.Api + ServicePathsLabel.Login, json)
            .then(response => {
                console.log(this.state.username)
                console.log(this.state.username)
                console.log(response.headers['authorization'])
                //sessionStorage.setItem('token', response.headers.tokenID);
            })
            .catch(error => {
                console.log("Error:" + error);
            })
            
    }

    async test() {
        await axios.get("http://localhost:8080/applications/taste", {
            headers: {
                Authorization: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzZWN1cmUtYXBpIiwiYXVkIjoic2VjdXJlLWFwcCIsInN1YiI6ImZyZWRlcmljby5sb3BlczFAaG90bWFpbC5jb20iLCJleHAiOjE2MDg5MzE3NzgsInJvbCI6WyJST0xFX1JFR0lTVEVSRUQiLCJST0xFX1NUVURFTlQiXX0.6CarIPiB0BfBR0Yx7N7PV0ypBNI8j6AQgRHRMA4gVmBDgspnn_wxvozAzEQZNqqQ5hH4nurvH4k1M3xGXFTurA"
            }
        });
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