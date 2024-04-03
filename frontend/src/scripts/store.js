import { createStore } from 'vuex'

const store = createStore({
    state () {
        return {
            account:{
                id:0
            }
        }
    },
    mutations: {
        setAcccount(state, payload){
            state.account.id = payload;
        }
    }
})

export default store;