import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    token: localStorage.getItem('token') || null,
    isAuthenticated: false,
    user: null,
};

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login: (state, action) => {
            state.token = action.payload;
            state.isAuthenticated = true;
        },
        logout: (state) => {
            state.token = null;
            state.isAuthenticated = false;
            state.user = null;
            localStorage.removeItem('token');
        },
        setUserData: (state, action) => {
            state.user = action.payload;
        },
    },
});

export const { login, logout, setUserData } = authSlice.actions;
export default authSlice.reducer;
