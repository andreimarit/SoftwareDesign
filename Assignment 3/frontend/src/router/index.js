import Vue from "vue";
import VueRouter from "vue-router";
import UserList from "../views/UserList.vue";
import BookList from "../views/BookList.vue";
import { auth as store } from "../store/auth.module";
import Login from "../views/Login";
import AdminBook from "../views/AdminBook";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/user",
    name: "User",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "Patient" });
      }
    },
  },
  {
    path: "/consultation",
    name: "Consultation",
    component: ConsultationList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isDoctor) {
        next();
      } else {
        next({ name: "Patient" });
      }
    },
  },
  {
    path: "/consultation",
    name: "Consultation",
    component: SecretaryConsultation,
    beforeEnter: (to, from, next) => {
      if (store.getters.isSecretary) {
        next();
      } else {
        next({ name: "Patient" });
      }
    },
  },
  {
    path: "/patient",
    name: "Patient",
    component: PatientList,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },

  // {
  //   path: "/adminBook",
  //   name: "AdminBook",
  //   component: AdminBook,
  //   beforeEnter: (to, from, next) => {
  //     if (store.state.status.loggedIn) {
  //       next();
  //     } else {
  //       next({ name: "Home" });
  //     }
  //   },
  // },
];

const router = new VueRouter({
  routes,
});

export default router;
