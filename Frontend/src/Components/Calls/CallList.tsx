import React, { Component } from 'react';
import { IoTrashOutline } from "react-icons/io5";
import { FaUsers } from 'react-icons/fa';
import axios from 'axios';
import { connect } from 'react-redux';
import { addCallId, cleanCallId } from '../Common/Utils/Redux/Actions/JobAction';
import { ServicePathsLabel, PathsLabel } from '../Common/Utils/Paths';
import { CallStruct } from '../Common/Utils/StructTypes';
import { Table, Button } from 'react-bootstrap';


class CallList extends Component<any, any>{
    constructor(props: any) {
        super(props)
        this.state = {
            calls: [],
            search: '',
            isLoading: false// meter loading spinner, talvez usar
        }
        // Functions
        this.callDetails = this.callDetails.bind(this);
        this.deleteCall = this.deleteCall.bind(this);
        this.createOrUpdatePanel = this.createOrUpdatePanel.bind(this);
        this.onSearch = this.onSearch.bind(this);
    }

    /**Receives the list of calls */
    componentDidMount() {
        var api = ServicePathsLabel.Api + ServicePathsLabel.Calls;
        // if student, api = getAtiveCalls
        axios(api)
            .then(response => {
                console.log(response.data);
                this.setState({ calls: response.data, isLoading: false });
            })
            .catch(error => {
                console.log("Error: " + error);
            });
    }

    callDetails = (id: Number) => (event: React.MouseEvent) => {
        if (id === -1) {
            this.props.cleanCallId(); // removes from the store
        } else {
            this.props.addCallId(id); // adds id to store
        }
        window.location.hash = PathsLabel.Calls + PathsLabel.Details;
        event.preventDefault();
    }

    deleteCall = (id: Number) => async (event: React.MouseEvent) => {
        await axios.delete(ServicePathsLabel.Api + ServicePathsLabel.Calls + id)
            .then(response => {
                var newCalls = this.state.calls.filter((el: any) => el.id !== id);
                this.setState({ calls: newCalls });
            })
            .catch(error => {
                console.log("Error: " + error);
            });
        event.preventDefault();
    }

    createOrUpdatePanel = (id: Number, title: string) => (event: React.MouseEvent) => {
        this.props.addCallId(id, title);
        window.location.hash = PathsLabel.Panels + PathsLabel.Details;
        event.preventDefault();
    }

    onSearch() {
        var calls = this.state.calls.filter((name: string) => {
            name.includes(this.state.search)
        });
        console.log(calls);
    }

    render() {
        const { calls, isLoading } = this.state;
        if (isLoading) {
            return <p>Loading...</p>;//substituir por loading spinner
        }
        return (
            <div className="p-5">
                <div>
                    <h1 className="float-left">List of Calls</h1>
                    <Button className="float-right mb-3" variant="primary" onClick={this.callDetails(-1)}>Create new Call</Button>{' '}
                </div>
                {calls.length === 0 &&
                    <div className="mt-5 ml-5">
                        <br />
                        <br />
                        <h2><p>There are no Calls created yet...</p></h2>
                    </div>
                }
                {calls.length !== 0 &&
                    <div>
                        <input type="text" placeholder="Search by name..." onChange={this.onSearch} value={this.state.search}/>
                        <Table striped bordered hover>
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Sponsor</th>
                                    <th>Description</th>
                                    <th>Requirements</th>
                                    <th>Funding</th>
                                    <th>Opening date</th>
                                    <th>Closing date</th>
                                    <th></th>
                                </tr>
                            </thead>
                            {calls.map((call: CallStruct) => (
                                <tbody>
                                    <td><button className="link-button" onClick={this.callDetails(call.id)}>{call.title}</button></td>
                                    <td>{call.sponsor}</td>
                                    <td>{call.description}</td>
                                    <td>{call.requirements}</td>
                                    <td>{call.funding}</td>
                                    <td>{call.openingDate}</td>
                                    <td>{call.closingDate}</td>
                                    <td>
                                        <button className="link-button" onClick={this.deleteCall(call.id)}><IoTrashOutline /></button>
                                        <button className="ml-3 link-button " onClick={this.createOrUpdatePanel(call.id, call.title)}><FaUsers /></button>
                                    </td>
                                </tbody>
                            ))}
                        </Table>
                    </div>
                }
            </div>
        );
    }

}

export default connect(null, { addCallId, cleanCallId })(CallList);