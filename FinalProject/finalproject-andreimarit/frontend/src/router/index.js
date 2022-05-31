import Vue from "vue";
import VueRouter from "vue-router";
//import ConsultationList from "../views/ConsultationList.vue";
import SecretaryConsultation from "../views/SecretaryConsultation.vue";
import UserList from "../views/UserList.vue";
import PatientList from "../views/PatientList.vue";
import { auth as store } from "../store/auth.module";
import Login from "../views/Login";
import ProductList from "../views/ProductList.vue";
import DiscountList from "../views/DiscountList.vue";
import CartList from "../views/CartList.vue";


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
        next({ name: "Product" });
      }
    },
  },
  {
    path: "/product",
    name: "Product",
    component: ProductList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isEmployee) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/discount",
    name: "Discount",
    component: DiscountList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isEmployee) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  
  // {
  //   path: "/consultation",
  //   name: "Consultation",
  //   component: ConsultationList,
  //   beforeEnter: (to, from, next) => {
  //     if (store.getters.isDoctor) {
  //       next();
  //     } else {
  //       next({ name: "Patient" });
  //     }
  //   },
  // },
  {
    path: "/consultation",
    name: "Consultation",
    component: SecretaryConsultation,
    beforeEnter: (to, from, next) => {
      if (store.getters.isDoctor) {
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
    path: "/cart",
    name: "Cart",
    component: CartList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isClient) {
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
