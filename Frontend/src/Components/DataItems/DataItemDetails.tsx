import React, { Component } from 'react';
import axios from 'axios';
import { Form, Col, Button, Row } from 'react-bootstrap';
import { connect } from 'react-redux';
import { DataItemStruct } from '../Common/Utils/StructTypes';
import { PathsLabel, ServicePathsLabel } from '../Common/Utils/Paths';

class DataItemDetails extends Component<DataItemStruct, DataItemStruct>{
    constructor(props: DataItemStruct) {
        super(props)
        this.state = {
            id: 0,
            name: '',
            type: ''
        }
        this.handleSaveDataItem = this.handleSaveDataItem.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    componentDidMount() {
        // mudar o service para retornar o nome do Sponsor
        var dataItemId = this.props.id;
        if (dataItemId !== null) {
            axios.get(ServicePathsLabel.DataItems + dataItemId)
                .then(response => {
                    console.log(response.data);
                    const dataItem = response.data;
                    this.setState({
                        id: dataItem.id,
                        name: dataItem.name,
                        type: dataItem.type
                    });
                })
                .catch(error => {
                    console.log("Error:" + error);
                })
        }
    }

    async handleSaveDataItem() {
        let json = {
            id: this.state.id,
            name: this.state.name,
            type: this.state.type
        }

        await axios.post(ServicePathsLabel.DataItems, json)
            .then(response => {
                console.log(response.data);
                window.location.hash = PathsLabel.DataItems;
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
        var title = "New DataItem";
        if (this.state.id !== 0) {
            title = this.state.name;
        }
        return (
            <body className="p-5">
                <h1 className="mb-5">{title}</h1>
                <Col xs="5">
                    <Form>
                        <Form.Group controlId="formBasicName">
                            <Form.Label>Name</Form.Label>
                            <Form.Control type="text" name="name" placeholder="Data Item's name" onChange={this.onChange} value={this.state.name} />
                        </Form.Group>
                        <Form.Group controlId="formBasicType">
                            <Form.Label>Type</Form.Label>
                            <Form.Control type="text" name="description" placeholder="Data Item's type" onChange={this.onChange} value={this.state.type} />
                        </Form.Group>
                        <Row>
                            <Col>
                                <Button variant="primary" size="lg" block onClick={this.handleSaveDataItem}>
                                    Save
                                </Button>
                            </Col>
                            <Col>
                                <Button href={"#" + PathsLabel.DataItems} variant="secondary" size="lg" block >
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
    id: state.job_reducer.dataItemId
    //mentees: getMentees(state)
});

export default connect(mapStateToProps, {})(DataItemDetails);