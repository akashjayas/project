import axios from "axios";
import { API_BASE_URL } from "../config/api";
import { LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS } from "./auth.actiontype";

export const loginUserAction = (loginData) => async (dispatch) => {
    dispatch({ type: LOGIN_REQUEST });
    try {
        const { data } = await axios.post(`${API_BASE_URL}/Admin/signin`, loginData);
        if (data.jwt) {
            localStorage.setItem("jwt", data.jwt);
        }
        console.log("login success", data);
        dispatch({ type: LOGIN_SUCCESS, payload: data.jwt });
    } catch (error) {
        console.log("-----", error);
        dispatch({ type: LOGIN_FAILURE, payload: error });
    }
};

export const registerUserAction = (loginData) => async (dispatch) => {
    dispatch({ type: LOGIN_REQUEST });
    try {
        const { data } = await axios.post(`${API_BASE_URL}/Admin`, loginData);
        if (data.jwt) {
            localStorage.setItem("jwt", data.jwt);
        }
        console.log("register", data);
        dispatch({ type: LOGIN_SUCCESS, payload: data.jwt });
    } catch (error) {
        console.log("-----", error);
        dispatch({ type: LOGIN_FAILURE, payload: error });
    }
};
