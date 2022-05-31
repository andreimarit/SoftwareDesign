<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create consultation" : "Edit consultation" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="consultation.patient" label="Patient" />
          <v-text-field v-model="consultation.doctor" label="Doctor" />
          <v-text-field v-model="consultation.details" label="Details" />
          <v-text-field v-model="consultation.date" label="Date" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
export default {
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultations
            .create({
              patient: this.consultation.patient,
              doctor: this.consultation.doctor,
              details: this.consultation.details,
              date: this.consultation.date,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.consultations
            .edit({
              id: this.consultation.id,
              patient: this.consultation.patient,
              doctor: this.consultation.doctor,
              details: this.consultation.details,
              date: this.consultation.date,
            })
            .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.consultation.id;
    },
  },
};
</script>

<style scoped></style>