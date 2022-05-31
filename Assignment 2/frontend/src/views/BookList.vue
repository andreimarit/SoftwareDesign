<template>
  <v-card>
    <v-card-title>
      Books for regular users
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn color="blue" @click="sellBook">Sell Book</v-btn>
      <v-btn @click="sellBook">Search by TAG</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :books="books"
      @click: row = "sellBook"
      :search="search"
    ></v-data-table>
    <SellDialog
      :opened="dialogVisible"
      :book="selectedBook"
      @refresh="refreshList"
    ></SellDialog>
  </v-card>
</template>

<script>
import api from "../api";
import SellDialog from "../components/SellDialog";

export default {
  name: "BookList",
  components: { SellDialog },
  data() {
    return {
      books: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Author", value: "author" },
        { text: "Genre", value: "genre" },
        { text: "Quantity", value: "quantity" },
        { text: "Price", value: "price" },
      ],
      dialogVisible: false,
      selectedBook: {},
    };
  },
  methods: {
    sellBook(book) {
      this.selectedBook = book;
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedBook = {};
      this.books = await api.books.allBooks();
    },
  },
  created() {
    this.refreshList();
  },
  computed: {
    isRegular(){
      return this.$store.getters["auth/isRegular"];
    }
    }
};
</script>

<style scoped></style>
