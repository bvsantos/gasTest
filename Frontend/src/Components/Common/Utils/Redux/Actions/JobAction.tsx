import { ADD_CALL_ID, CLEAN_CALL_ID, ADD_DATA_ITEM_ID, CLEAN_DATA_ITEM_ID } from "./Types";


export const addCallId = (callId: number, name: string) => {
    return {
        type: ADD_CALL_ID,
        callId: callId,
        name: name
    };
};

export const cleanCallId = () => {
    return {
        type: CLEAN_CALL_ID
    }
}

export const addDataItemId = (dataItemId: number) => {
    return {
        type: ADD_DATA_ITEM_ID,
        dataItemId: dataItemId
    }
}

export const cleanDataItemId = () => {
    return {
        type: CLEAN_DATA_ITEM_ID
    }
}