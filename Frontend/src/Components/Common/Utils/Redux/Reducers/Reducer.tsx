import {Action, combineReducers} from 'redux';
import JobReducer from './JobReducer';

const constReducer = combineReducers ({
    job_reducer : JobReducer 
})

const rootReducer = (state: any, action: Action<any>) => {
    /*if (action.type === SET_CURRENT_USER) {
        state = undefined;
    }*/
    
    return constReducer(state, action);
};

export default rootReducer;