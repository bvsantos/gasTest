import React, { Component } from 'react';
import axios from 'axios';
import { Form, Col, Button, Row } from 'react-bootstrap';
import { connect } from 'react-redux';
import { CallStruct } from '../Common/Utils/StructTypes';
import { PathsLabel, ServicePathsLabel } from '../Common/Utils/Paths';

class CallDetails extends Component<CallStruct, CallStruct>{
    constructor(props: CallStruct) {
        super(props)
        this.state = {
            id: 0,
            title: '',
            sponsorId: 0,
            sponsor: '',
            description: '',
            requirements: '',
            funding: 0,
            openingDate: '',
            closingDate: ''
        }
        this.handleSaveCall = this.handleSaveCall.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    componentDidMount() {
        // mudar o service para retornar o nome do Sponsor
        var callId = this.props.id;
        if (callId !== null) {
            axios.get(ServicePathsLabel.Calls + callId)
                .then(response => {
                    console.log(response.data);
                    const call = response.data;
                    this.setState({
                        id: call.id,
                        title: call.title,
                        sponsorId: call.sponsorId,
                        sponsor: call.sponsor,
                        description: call.description,
                        requirements: call.requirements,
                        funding: call.funding,
                        openingDate: call.openingDate,
                        closingDate: call.closingDate
                    });
                })
                .catch(error => {
                    console.log("Error:" + error);
                })
        }
    }

    async handleSaveCall() {
        // mudar o sponsorId por uma session variable
        let json = {
            id: this.state.id,
            title: this.state.title,
            sponsorId: 0,
            description: this.state.description,
            requirements: this.state.requirements,
            funding: this.state.funding,
            openingDate: new Date(this.state.openingDate),
            closingDate: new Date(this.state.closingDate)
        }

        await axios.post(ServicePathsLabel.Calls, json)
            .then(response => {
                console.log(response.data);
                window.location.hash = PathsLabel.Calls;
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
        var title = "New Call";
        if (this.state.id !== 0) {
            title = this.state.title;
        }
        return (
            <body className="p-5">
                <h1 className="mb-5">{title}</h1>
                <Col xs="5">
                    <Form>
                        <Form.Group controlId="formBasicTitle">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" name="title" placeholder="Call's title" onChange={this.onChange} value={this.state.title}/>
                        </Form.Group>
                        <Form.Group controlId="formBasicSponsor">
                            <Form.Label>Sponsor</Form.Label>
                            <Form.Control type="text" name="sponsor" value={this.state.sponsorId} disabled/>
                        </Form.Group>
                        <Form.Group controlId="formBasicDescription">
                            <Form.Label>Description</Form.Label>
                            <Form.Control type="text" name="description" placeholder="Call's description" onChange={this.onChange} value={this.state.description}/>
                        </Form.Group>
                        <Form.Group controlId="formBasicRequirement">
                            <Form.Label>Requeriments</Form.Label>
                            <Form.Control type="text" name="requirements" placeholder="Call's requirements" onChange={this.onChange} value={this.state.requirements}/>
                        </Form.Group>
                        <Form.Group controlId="formBasicFunding">
                            <Form.Label>Funding</Form.Label>
                            <Form.Control type="number" name="funding" placeholder="Call's funding" onChange={this.onChange} value={this.state.funding}/>
                        </Form.Group>
                        <Form.Group controlId="formBasicOpeningDate" placeholder="Call's opening date">
                            <Form.Label>Opening Date</Form.Label>
                            <Form.Control type="date" name="openingDate" onChange={this.onChange} value={this.state.openingDate}/>
                        </Form.Group>
                        <Form.Group controlId="formBasicClosingDate">
                            <Form.Label>Closing Date</Form.Label>
                            <Form.Control type="date" name="closingDate" placeholder="Call's closing date" onChange={this.onChange} value={this.state.closingDate}/>
                        </Form.Group>
                        <Row>
                            <Col>
                                <Button variant="primary" size="lg" block onClick={this.handleSaveCall}>
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
    id: state.job_reducer.callId
    //mentees: getMentees(state)
});

export default connect(mapStateToProps, {})(CallDetails);