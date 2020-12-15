import React, { Component } from 'react';
import axios from 'axios';
import { Form, Col, Button, Row } from 'react-bootstrap';
import { connect } from 'react-redux';
import { PanelStruct } from '../Common/Utils/StructTypes';
import { PathsLabel, ServicePathsLabel, SubServicesLable } from '../Common/Utils/Paths';

class PanelDetails extends Component<PanelStruct, PanelStruct>{
    constructor(props: PanelStruct) {
        super(props)
        this.state = {
            id: 0,
            name: ''
        }
        this.handleSavePanel = this.handleSavePanel.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    componentDidMount() {
        // mudar o service para retornar o nome do Sponsor
        var callId = this.props.id;
        axios.get(ServicePathsLabel.Api + ServicePathsLabel.Calls + SubServicesLable.Panels + callId)
            .then(response => {
                console.log(response.data);
                const panel = response.data;
                this.setState({
                    id: panel.id,
                    name: panel.name
                });
            })
            .catch(error => {
                console.log("Error:" + error);
            })
    }

    handleSavePanel() {
        let json = {
            id: this.state.id,
            name: this.state.name
        }

        axios.post(ServicePathsLabel.Api + ServicePathsLabel.Calls + SubServicesLable.Panels, json)
            .then(response => {
                console.log(response.data);
                //window.location.hash = PathsLabel.Calls;
            }).catch(error => {
                console.log("Error: " + error);
                //sweetAlert error
            });
    }

    onChange(e: any) {
        const target = e.target;
        const value = target.value;
        const name = target.name;
        this.setState({ ...this.state, [name]: value });
    }

    render() {
        var title = this.props.name + " - Panel";
        return (
            <body className="p-5">
                <h1 className="mb-5">{title}</h1>
                <Col xs="5">
                    <Form>
                        <Form.Group controlId="formBasicName">
                            <Form.Label>Name</Form.Label>
                            <Form.Control type="text" name="name" placeholder="Panel's name" onChange={this.onChange} value={this.state.name}/>
                        </Form.Group>
                        <Row>
                            <Col>
                                <Button variant="primary" size="lg" block onClick={this.handleSavePanel}>
                                    Save
                                </Button>
                            </Col>
                            <Col>
                                <Button href={"#" + PathsLabel.Calls} variant="secondary" size="lg" block >
                                    Cancel
                                </Button>
                            </Col>
                        </Row>
                    </Form>
                </Col>
            </body>
        );
    }
}

const mapStateToProps = (state: any) => ({
    id: state.job_reducer.callId,
    name: state.job_reducer.name
});

export default connect(mapStateToProps, {})(PanelDetails);