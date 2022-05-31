<template>
  <v-card>
    <v-card-title>
      Consultations
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
      <v-btn @click="addConsultation">Add Consultation</v-btn>
      <v-btn @click="deleteConsultation">Delete Consultation</v-btn>
      <v-btn @click="editConsultation">Edit Consultation</v-btn>
      <v-btn @click="patient">GoTo Patients</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="consultations"
        :search="search"
        @click:row="editConsultation"
    ></v-data-table>
    <ConsultationDialog
        :opened="dialogVisible"
        :consultation="selectedConsultation"
        @refresh="refreshList"
    ></ConsultationDialog>
  </v-card>
</template>

<script>
import api from "../api";
import router from "../router";
import ConsultationDialog from "../components/ConsultationDialog";
export default {
  name: "ConsultationSecretary",
  components: {ConsultationDialog},
  data() {
    return {
      consultations: [],
      search: "",
      headers: [
        {
          text: "Consultation",
          align: "start",
          sortable: false,
          value: "consultation",
        },
        { text: "Patient", value: "patient" },
        { text: "Doctor", value: "doctor" },
        { text: "Date", value: "date" },
        { text: "Detalis", value: "details" },
      ],
      dialogVisible: false,
      selectedConsultation: {},
    };
  },
  methods: {
    patient(){
      router.push("./patient");
    },
    editConsultation(consultation) {
      this.selectedConsultation = consultation;
      this.dialogVisible = true;
    },
    deleteConsultation(consultation){
      this.selectedConsultation = consultation;
      this.dialogVisible = true;
    },
    addConsultation() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedConsultation = {};
      this.books = await api.consultations.allConsultations();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>