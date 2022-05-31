<template>
  <v-card>
    <v-card-title>
      Users
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      </v-card-title>
  <v-card class=text-center color="brown">
      <v-btn color="green" @click="addUser">Add User</v-btn>
      <v-btn color="red" @click="deleteUser">Delete User</v-btn>
      <v-btn color="yellow" @click="editUser">Edit User</v-btn>
      <v-btn @click="goToBooks">Book</v-btn>
  </v-card> 
    <v-data-table
      :headers="headers"
      :items="users"
      @click:row="editUser"
      :search="search"
    ></v-data-table>
    <UserDialog 
      :opened="dialogVisible"
      :user="selectedUser"
      @refresh="refreshList"
    ></UserDialog>
  </v-card>
</template>

<script>
import api from "../api";
import router from "../router";
import UserDialog from "../components/UserDialog";

export default {
  name: "UserList",
  components: {
    UserDialog
    },
  data() {
    return {
      users: [],
      search: "",
      headers: [
        {
          text: "Username",
          align: "start",
          sortable: false,
          value: "username",
        },
        { text: "Email", value: "email" },
        { text: "Roles", value: "roles" },
      ],
      dialogVisible: false,
      selectedUser: {},
    };
  },
  methods: {

    async refreshList() {
      this.dialogVisible = false;
      this.selectedUser = {};
      this.users = await api.users.allUsers();
    },
     async editUser(user) {
    this.selectedUser = user;
    this.dialogVisible=true;
  },
  async addUser(){
    this.dialogVisible=true;
  },
  async deleteUser(user){
    this.selectedUser = user;
    this.dialogVisible=true;
  },
  goToBooks(){router.push("./adminBook")}
  },
  
  async created() {
    this.users = await api.users.allUsers();
  },
 
};
</script>

<style scoped></style>
