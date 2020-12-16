import React, { Component } from 'react';
import { IoTrashOutline } from "react-icons/io5";
import axios from 'axios';
import { connect } from 'react-redux';
import { addDataItemId, cleanDataItemId } from '../Common/Utils/Redux/Actions/JobAction';
import { ServicePathsLabel, PathsLabel } from '../Common/Utils/Paths';
import { DataItemStruct } from '../Common/Utils/StructTypes';
import { Table, Button } from 'react-bootstrap';


class DataItemsList extends Component<any, any>{
    constructor(props: any) {
        super(props)
        this.state = {
            dataItems: [],
            isLoading: false// meter loading spinner, talvez usar
        }
        // Functions
        this.dataItemDetails = this.dataItemDetails.bind(this);
        this.deleteDataItems = this.deleteDataItems.bind(this);
    }

    /**Receives the list of calls */
    componentDidMount() {
        axios(ServicePathsLabel.DataItems)
            .then(response => {
                console.log(response.data);
                this.setState({ dataItems: response.data, isLoading: false });
            })
            .catch(error => {
                console.log("Error: " + error);
            });
    }

    dataItemDetails = (id: Number) => (event: React.MouseEvent) => {
        if (id === -1) {
            this.props.cleanDataItemId(); // removes from the store
        } else {
            this.props.addDataItemId(id); // adds id to store
        }
        window.location.hash = PathsLabel.DataItems + PathsLabel.Details;
        event.preventDefault();
    }

    deleteDataItems = (id: Number) => async (event: React.MouseEvent) => {
        await axios.delete(ServicePathsLabel.DataItems + id)
            .then(response => {
                var newDataItems = this.state.dataItems.filter((el: any) => el.id !== id);
                this.setState({ dataItems: newDataItems });
            })
            .catch(error => {
                console.log("Error: " + error);
            });
        event.preventDefault();
    }

    render() {
        const { dataItems, isLoading } = this.state;
        if (isLoading) {
            return <p>Loading...</p>;//substituir por loading spinner
        }
        return (
            <div className="p-5">
                <div>
                    <h1 className="float-left">List of DataItems
                    </h1>
                    <Button className="float-right mb-3" variant="primary" onClick={this.dataItemDetails(-1)}>Create new Data Item</Button>{' '}
                </div>
                {dataItems.length === 0 &&
                    <div className="mt-5 ml-5">
                        <br />
                        <br />
                        <h2><p className="mt-4">There are no Data Items created yet...</p></h2>
                    </div>
                }
                {dataItems.length !== 0 &&
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Type</th>
                                <th></th>
                            </tr>
                        </thead>
                        {dataItems.map((dataItem: DataItemStruct) => (
                            <tbody>
                                <td><button className="link-button" onClick={this.dataItemDetails(dataItem.id)}>{dataItem.name}</button></td>
                                <td>{dataItem.type}</td>
                                <td>
                                    <button className="link-button" onClick={this.deleteDataItems(dataItem.id)}><IoTrashOutline /></button>
                                </td>
                            </tbody>
                        ))}
                    </Table>
                }
            </div>
        );
    }

}

export default connect(null, { addDataItemId, cleanDataItemId })(DataItemsList);