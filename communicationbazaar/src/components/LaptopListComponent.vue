<template>
  <div class="m-2">
    <h1 class="mx-3">{{ $t('laptop.allLaptops') }}</h1>
    <div class="container-fluid p-3 normal">
      <label for="allImportedFiles" class="btn btn-danger mb-2">Import</label>
      <input type="file" id="allImportedFiles" name="Import" @change="importFile()"
             accept=".csv">
      <div id="errorMessageFileImport" class="text-danger"></div>
      <div id="successMessageFileImport" class="text-dark"></div>
      <div v-if="editLaptop !== null">
        <div class="card card-body">
          <router-view
              v-on:refresh="this.reInitialise()">

          </router-view>
        </div>
      </div>
      <!--    1st view-->
      <div class="container">
        <div class="row">
          <div class="card col-md-5 m-3 p-3" v-for="laptop of laptops" v-bind:key="laptop.ean">
            <div class="row justify-content-between">
              <div class="col-8">
                <h4>
                  {{ laptop.brand }} {{ laptop.description }}
                </h4>
              </div>
              <div class="col-auto">
                <div :class="{'hiddenButton': accounts.some(account => account.loggedIn) === false ||
                      accounts.some(account => account.loggedIn === true && account.role !== 'admin')}"
                     class="row justify-content-md-end">
                  <button type="button" class="btn btn-danger m-2 col-auto" @click="modalDelete(laptop)">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-trash-fill" viewBox="0 0 16 16">
                      <path
                          d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>
                    </svg>
                  </button>
                  <button type="button" class="btn btn-outline-secondary m-2 col-auto" @click="setRoute(laptop)">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-pencil-square" viewBox="0 0 16 16">
                      <path
                          d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                      <path fill-rule="evenodd"
                            d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                    </svg>
                  </button>
                </div>
              </div>
            </div>
            <div class="row justify-content-md-left">
              <div class="col col-md-5">
                {{ $t('imageDetail.ean') }}: {{ laptop.ean }}
              </div>
              <div class="col-md-auto">
                {{ $t('imageDetail.articleNumber') }}: {{ laptop.articleNumber }}
              </div>
            </div>
            <div class="mt-2">
              <div class="row justify-content-md-left">
                <div class="col col-sm-4 text-body-tertiary">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cpu"
                       viewBox="0 0 16 16">
                    <path
                        d="M5 0a.5.5 0 0 1 .5.5V2h1V.5a.5.5 0 0 1 1 0V2h1V.5a.5.5 0 0 1 1 0V2h1V.5a.5.5 0 0 1 1 0V2A2.5 2.5 0 0 1 14 4.5h1.5a.5.5 0 0 1 0 1H14v1h1.5a.5.5 0 0 1 0 1H14v1h1.5a.5.5 0 0 1 0 1H14v1h1.5a.5.5 0 0 1 0 1H14a2.5 2.5 0 0 1-2.5 2.5v1.5a.5.5 0 0 1-1 0V14h-1v1.5a.5.5 0 0 1-1 0V14h-1v1.5a.5.5 0 0 1-1 0V14h-1v1.5a.5.5 0 0 1-1 0V14A2.5 2.5 0 0 1 2 11.5H.5a.5.5 0 0 1 0-1H2v-1H.5a.5.5 0 0 1 0-1H2v-1H.5a.5.5 0 0 1 0-1H2v-1H.5a.5.5 0 0 1 0-1H2A2.5 2.5 0 0 1 4.5 2V.5A.5.5 0 0 1 5 0m-.5 3A1.5 1.5 0 0 0 3 4.5v7A1.5 1.5 0 0 0 4.5 13h7a1.5 1.5 0 0 0 1.5-1.5v-7A1.5 1.5 0 0 0 11.5 3zM5 6.5A1.5 1.5 0 0 1 6.5 5h3A1.5 1.5 0 0 1 11 6.5v3A1.5 1.5 0 0 1 9.5 11h-3A1.5 1.5 0 0 1 5 9.5zM6.5 6a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5z"/>
                  </svg>
                  {{ $t('laptop.processor') }}:
                </div>
                <div class="col-sm-auto">
                  {{ laptop.processor }}
                </div>
              </div>
              <div class="row justify-content-md-left">
                <div class="col col-sm-4 text-body-tertiary">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                       class="bi bi-memory" viewBox="0 0 16 16">
                    <path
                        d="M1 3a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h4.586a1 1 0 0 0 .707-.293l.353-.353a.5.5 0 0 1 .708 0l.353.353a1 1 0 0 0 .707.293H15a1 1 0 0 0 1-1V4a1 1 0 0 0-1-1zm.5 1h3a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-4a.5.5 0 0 1 .5-.5m5 0h3a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-4a.5.5 0 0 1 .5-.5m4.5.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5zM2 10v2H1v-2zm2 0v2H3v-2zm2 0v2H5v-2zm3 0v2H8v-2zm2 0v2h-1v-2zm2 0v2h-1v-2zm2 0v2h-1v-2z"/>
                  </svg>
                  RAM:
                </div>
                <div class="col-sm-auto">
                  {{ laptop.ram }}
                </div>
              </div>
              <div class="row justify-content-md-left">
                <div class="col col-sm-4 text-body-tertiary">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                       class="bi bi-gpu-card" viewBox="0 0 16 16">
                    <path d="M4 8a1.5 1.5 0 1 1 3 0 1.5 1.5 0 0 1-3 0m7.5-1.5a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3"/>
                    <path
                        d="M0 1.5A.5.5 0 0 1 .5 1h1a.5.5 0 0 1 .5.5V4h13.5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5H2v2.5a.5.5 0 0 1-1 0V2H.5a.5.5 0 0 1-.5-.5m5.5 4a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5M9 8a2.5 2.5 0 1 0 5 0 2.5 2.5 0 0 0-5 0"/>
                    <path
                        d="M3 12.5h3.5v1a.5.5 0 0 1-.5.5H3.5a.5.5 0 0 1-.5-.5zm4 1v-1h4v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5"/>
                  </svg>
                  GPU:
                </div>
                <div class="col-sm-auto">
                  {{ laptop.gpu }}
                </div>
              </div>
              <div class="row justify-content-md-left">
                <div class="col col-sm-4 text-body-tertiary">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                       class="bi bi-window-dock" viewBox="0 0 16 16">
                    <path
                        d="M3.5 11a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3.5.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm4.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                    <path
                        d="M14 1a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2zM2 14h12a1 1 0 0 0 1-1V5H1v8a1 1 0 0 0 1 1M2 2a1 1 0 0 0-1 1v1h14V3a1 1 0 0 0-1-1z"/>
                  </svg>
                  {{ $t('imageDetail.os') }}:
                </div>
                <div class="col-sm-auto">
                  {{ laptop.os }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--      modal pop-up view-->
      <div class="modal" tabindex="-1" role="dialog" style="display: block;" v-if="showModal">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">{{ $t('adminPanel.confirmation') }}</h5>
              <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <p>{{ $t('laptop.confirmMessage') }}</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" @click="closeModal()">
                {{ $t('adminPanel.cancelButton') }}
              </button>
              <button type="button" class="btn btn-danger" @click="onDelete(selectedLaptop)">
                {{ $t('imageDetail.deleteButton') }}
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import laptopDetailComponent from "@/components/LaptopDetailComponent";
import * as XLSX from 'xlsx';
import {Laptop} from "@/models/laptop";

export default {
  name: "LaptopListComponent",
  inject: ['laptopsService', 'accountsService'],
  components: laptopDetailComponent,
  data() {
    return {
      laptops: [],
      importedLaptops: [],
      accounts: [],
      showModal: false,
      selectedLaptop: null,
      editLaptop: null,
      fileTypes: [
        "csv",
        "text/csv",
        "xlsx"
      ]
    }
  },
  async created() {
    this.accounts = await this.accountsService.asyncFindAll();
    await this.reInitialise();
    this.account = this.accounts.find(account => account.loggedIn)
  },
  methods: {
    /**
     * A methode that sets the route link as well as the selected style for the editing view
     *
     * @author Seyma Kaya
     * @param laptop
     */
    setRoute(laptop) {
      let parentPath = this.$route?.fullPath.replace(new RegExp("/\\d*$"), '');
      if (this.editLaptop === laptop) {
        this.$router.push(parentPath);
        this.editLaptop = null;
      } else {
        this.$router.push(parentPath + "/" + laptop.ean);
        this.editLaptop = laptop;
      }
    },
    // /**
    //  * A methode that links the given id to the corresponding laptop
    //  *
    //  * @author Seyma Kaya
    //  * @param id that belongs to a laptop we are trying to find
    //  * @returns {null|*}
    //  */
    // findSelectedFromRouteParams(id) {
    //   if (id > 0) {
    //     id = parseInt(id)
    //     return this.laptops.find(value => value.id === id);
    //   }
    //   return null;
    // },
    /**
     * A methode that reinitializes the view with laptops
     *
     * @author Seyma Kaya
     * @returns {Promise<void>}
     */
    async reInitialise() {
      this.laptops = await this.laptopsService.asyncFindAll()
      this.selectedLaptop = null;
      this.editLaptop = null;
      this.setRoute(this.editLaptop)
    },
    /**
     * A methode opens the confirmation modal for delete
     *
     * @author Seyma Kaya
     * @param laptop that this modal is for
     * @returns {Promise<void>}
     */
    async modalDelete(laptop) {
      this.selectedLaptop = laptop
      this.showModal = true;
    },
    /**
     * A methode deletes a given laptop
     *
     * @author Seyma Kaya
     * @param laptop that needs to be deleted
     * @returns {Promise<void>}
     */
    async onDelete(laptop) {
      await this.laptopsService.asyncDeleteById(laptop.ean)
      this.reInitialise();
      this.closeModal();
    },
    /**
     * Method that will close the confirmation modal when the close button is clicked.
     *
     * @author Seyma Kaya
     */
    closeModal() {
      this.showModal = false;
      this.selectedLaptop = null
    },
    validFileType(file) {
      console.log(file.type)
      console.log(this.fileTypes.includes(file.type))
      return this.fileTypes.includes(file.type);
    },
    async importFile() {
      console.log(this.laptops)

      const allImportedFiles = document.getElementById("allImportedFiles").files;
      const errorMessageFileImport = document.getElementById("errorMessageFileImport");
      const successMessageFileImport = document.getElementById("successMessageFileImport");

      while (errorMessageFileImport.firstChild) {
        errorMessageFileImport.removeChild(errorMessageFileImport.firstChild);
      }

      while (successMessageFileImport.firstChild) {
        successMessageFileImport.removeChild(successMessageFileImport.firstChild);
      }

      if (allImportedFiles.length === 0) {
        errorMessageFileImport.textContent = "U heeft op dit moment geen nieuw bestand geselecteerd op te uploaden";
        return;
      }

      if (allImportedFiles.length > 1){
        errorMessageFileImport.textContent = "U heeft teveel bestanden geselecteerd om te uploaden";
        return
      }

      const correctImportedFile = allImportedFiles[0];
      if (!this.validFileType(correctImportedFile)) {
        errorMessageFileImport.textContent = "Kies een bestand van type .csv";
        return;
      }

      try {
        const fileReader = new FileReader();

        fileReader.onload = (e) => {
          const data = e.target.result;
          const workbook = XLSX.read(data, {type: 'binary'});

          const firstSheetName = workbook.SheetNames[0];
          const worksheet = workbook.Sheets[firstSheetName];
          const importedData = XLSX.utils.sheet_to_json(worksheet, {header: 1});

          // Now 'importedData' contains the parsed CSV data in an array
          console.log(importedData);

          // Add imported laptops without duplicates to the main list
          this.addImportedLaptopsWithoutDuplicates(importedData);
        };

        fileReader.readAsBinaryString(correctImportedFile);
      } catch (error) {
        console.error("Error importing file:", error);
        errorMessageFileImport.textContent = "Error occurred while importing the file";
      }
    },
    addImportedLaptopsWithoutDuplicates(importedData) {
      const successMessageFileImport = document.getElementById("successMessageFileImport");
      for (const laptop of importedData) {
        console.log(laptop)
        const existingLaptop = this.laptops.find((l) => l.ean === laptop[1]);
        if (!existingLaptop) {
          const newLaptop = new Laptop(laptop[0], laptop[1], laptop[2], laptop[3], laptop[4], laptop[5], laptop[6], laptop[7], laptop[8], laptop[9], laptop[10], laptop[11])
          if (newLaptop.ean !== "ean") {
            this.laptops.push(newLaptop);
          }
        }
      }
      console.log("Alle laptops zijn succesvol toegevoegd")
      console.log(this.laptops)
      successMessageFileImport.textContent = "De laptops zijn succesvol toegevoegd!";
    }
  }
}
</script>

<style scoped>
.hiddenButton {
  display: none;
}

input {
  opacity: 0;
}

</style>