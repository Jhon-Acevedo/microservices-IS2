<script setup>

</script>

<template>
  <div class="container-fluid">
    <div class="row no-gutter">
      <div class="col-md-6 d-none d-md-flex bg-image"></div>
      <div class="col-md-6 bg-light">
        <div class="login d-flex align-items-center py-5">
          <div class="container">
            <div class="row">
              <div class="col-lg-10 col-xl-7 mx-auto">
                <h3 class="display-6">CowShield</h3>
                <p class="text-muted mb-4">Ganado sano, futuro seguro ¡Vacuna y previene en Boyacá</p>
                <form v-on:submit.prevent="processLogInUser" class="form">
                  <div class="mb-3">
                    <input id="username" v-model="user.email" type="text" placeholder="Username" required=""
                           autofocus=""
                           class="form-control rounded-pill border-0 shadow-sm px-4"/>
                  </div>
                  <div class="mb-3">
                    <input id="password" v-model="user.password" type="password" placeholder="Password" required=""
                           class="form-control rounded-pill border-0 shadow-sm px-4 text-primary"/>
                  </div>
                  <div class="d-grid gap-2 mt-2">
                    <button type="submit" class="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm">
                      Login
                    </button>
                  </div>
                  <div class="d-flex align-items-center justify-content-center pb-4">
                    <p class="mb-0 me-2">¿No posees una cuenta?</p>
                    <button v-on:click="loadSignUp" type="button" class="btn btn-outline-primary">Create new</button>
                  </div>
                  <div class="text-center justify-content-between mt-4"><p>GRUPO 1 IS2 <a
                      class="font-italic text-muted"></a></p></div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="main-component">
    <router-view
        v-on:completedLogIn="completedLogIn"
        v-on:completedSignUp="completedSignUp"
    >
    </router-view>
  </div>
</template>

<script>
import axios from "axios";
import "./LogIn.css";

export default {
  name: "LogIn",
  data: function () {
    return {
      user: {
        email: "",
        password: ""
      }
    }
  },
  methods: {
    processLogInUser: function () {
      axios.post(
          "https://microservices-is2-production-0e1c.up.railway.app/login",
          this.user,
          {headers: {}}
      ).then((result) => {
        let dataLogin = {
          user: result.data.id,
          token_access: result.data.token,
        }
        const emits = ['completedLogIn'];
        this.$emit(emits[0], dataLogin);
      }).catch((err) => {
        if (err.response.status === 401) {
          alert("ERROR 401: Credenciales Incorrectas.");
        }
        if (err.response.status === 400) {
          alert("ERROR 400: Bad request.");
        }
        if (err.response.status === 500) {
          alert("ERROR 500: Internal server error.");
        }
      });
    },
    loadSignUp: function () {
      this.$router.push({name: 'signUp'});
    },

  },
}
</script>

<style scoped>
</style>