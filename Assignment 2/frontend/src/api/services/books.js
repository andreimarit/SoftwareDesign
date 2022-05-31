import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allBooks() {
    return HTTP.get(BASE_URL + "/book", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(book) {
    return HTTP.post(BASE_URL + "/book", book, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(book) {
    return HTTP.patch(BASE_URL + "/book", book, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  sell(id, quantity) {
    return HTTP.patch(
      BASE_URL + "/book/sell/" + id + "/" + quantity,
      {},
      {
        headers: authHeader(),
      }
    ).then((response) => {
      return response.data;
    });
  },

  searchTAG(name, author, genre){
    return HTTP.patch(BASE_URL + "/book/tag", name, author, genre, {headers: authHeader() }).then(
      (response)=>{
        return response.data;
      })
  },

  csvR() {
   //console.log("maybeError");
    return HTTP.get(BASE_URL + "/book/export/CSV", {headers: authHeader()}).then(
        (response) => {
            
            return response.data;
        }
    );
  },
  pdfR() {
    return HTTP.get(BASE_URL + "/book/export/PDF", {headers: authHeader()}).then(
        (response) => {
            return response.data;
        }
    );
  },
  pdfJasper() {
    return HTTP.get(BASE_URL + "/book/export/PDF2", {headers: authHeader()}).then(
        (response) => {
            return response.data;
        }
    );
  },
};
