import { ADD_CALL_ID, CLEAN_CALL_ID, ADD_DATA_ITEM_ID, CLEAN_DATA_ITEM_ID } from "../Actions/Types";

const initialState = {
    callId: null,
    name: '',
    dataItemId: null
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case ADD_CALL_ID: return {
            ...state, callId: action.callId, name: action.name
        }
        case CLEAN_CALL_ID: return {
            ...state, callId: 0, name: ''
        }
        case ADD_DATA_ITEM_ID: return {
            ...state, dataItemId: action.dataItemId
        }
        case CLEAN_DATA_ITEM_ID: return {
            ...state, dataItemId: 0
        }
        default:
            return state;
    }
}