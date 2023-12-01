<template>
  <div class="page" :class="{ expanded: this.expanded }">
    <div class="headerContent">
      <!-- Mediamarkt logo-->
      <img class="logo" :src="mediaMarktLogo" alt="">
      <!-- Input Search Image-->
      <svg xmlns="http://www.w3.org/2000/svg" width="34" height="34" fill="lightgrey" class="bi bi-search mobile"
        viewBox="0 0 16 16" @click="expandSearch()">
        <path
          d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
      </svg>
      <div class="input-group-lg">
        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="lightgrey" class="bi bi-search"
          viewBox="0 0 16 16">
          <path
            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
        </svg>
        <input type="text" :placeholder="$t('header.placeholder')" class="input form-control">
      </div>
      <!-- Test button since for devices with no camera's-->
<!--      <button type="button" class="btn btn-danger m-lg-3" @click="onDecode('38000447')">Test Btn</button>-->
      <!-- button for BarcodeScanner code-->
      <button type="button" class="btn btn-danger m-lg-3" @click="toggleStreamBarcodeReader">Scan for an image</button>
      <!-- BarcodeScanner in modal pop up-->
      <div v-if="showModal" class="modal">
        <div class="modal-content">
          <span class="close" @click="toggleStreamBarcodeReader">&times;</span>
          <div class="barcodeScanner-container">
            <StreamBarcodeReader @decode="onDecode" @loaded="onLoaded" class="qrcode-stream"></StreamBarcodeReader>
      <!-- Seeing the value just for testing purpose-->
<!--            <h1>{{ scannedBarcode }}</h1>-->
          </div>
        </div>
      </div>
      <!--Translation select-->
      <div class="translation" v-if="checkScreenWidth">
        <select class="form-select language" v-model="$i18n.locale" @change="updateLocale">
          <option value="nl">Nederlands</option>
          <option value="en">English</option>
          <option value="fr">Français</option>
        </select>
      </div>
      <div class="translation-mobile" v-if="checkScreenWidth">
        <select class="form-select language" v-model="$i18n.locale" @change="updateLocale">
          <option value="nl">NL</option>
          <option value="en">ENG</option>
          <option value="fr">FR</option>
        </select>
      </div>
      <!-- Alert button-->
      <div class="bell">
        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="lightgrey" class="bi bi-bell" viewBox="0 0 16 16">
          <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"/>
        </svg>
      </div>
    </div>
    <Transition>
      <div v-if="expanded" >
        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="grey" class="bi bi-qr-code-scan" viewBox="0 0 16 16">
          <path d="M0 .5A.5.5 0 0 1 .5 0h3a.5.5 0 0 1 0 1H1v2.5a.5.5 0 0 1-1 0v-3Zm12 0a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0V1h-2.5a.5.5 0 0 1-.5-.5ZM.5 12a.5.5 0 0 1 .5.5V15h2.5a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5v-3a.5.5 0 0 1 .5-.5Zm15 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1 0-1H15v-2.5a.5.5 0 0 1 .5-.5ZM4 4h1v1H4V4Z"/>
          <path d="M7 2H2v5h5V2ZM3 3h3v3H3V3Zm2 8H4v1h1v-1Z"/>
          <path d="M7 9H2v5h5V9Zm-4 1h3v3H3v-3Zm8-6h1v1h-1V4Z"/>
          <path d="M9 2h5v5H9V2Zm1 1v3h3V3h-3ZM8 8v2h1v1H8v1h2v-2h1v2h1v-1h2v-1h-3V8H8Zm2 2H9V9h1v1Zm4 2h-1v1h-2v1h3v-2Zm-4 2v-1H8v1h2Z"/>
          <path d="M12 9h2V8h-2v1Z"/>
        </svg>
        <input v-model="searchQuery" type="text" :placeholder="$t('header.placeholder')" class="input form-control input-expanded">
        <div class="search-result shadow-sm" v-if="searchQuery !== '' " >
          <table class="table table-sm">
            <thead>
            <tr>
              <th scope="col">{{$t('allImages.ean')}}</th>
              <th scope="col">{{$t('allImages.employeeName')}}</th>
              <th scope="col">{{$t('allImages.status')}}</th>
              <th scope="col">{{$t('allImages.date')}}</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="image in this.filterImages" v-bind:key="image.ean">
              <td>{{ image.laptop[0].ean }}</td>
              <td v-if="image.imageMaker !== ''">{{ image.imageMaker }}</td>
              <td v-else class="text-secondary">Niet toegewezen</td>
              <td>{{ image.status }}</td>
              <td>{{ image.upDateDate }}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </Transition>

  </div>
</template>

<script>
import { StreamBarcodeReader } from "vue-barcode-reader";
import { ref } from 'vue';

export const barcode = ref(null);

export default {
  name: 'HeaderComponent',
  components: {
    StreamBarcodeReader
  },
  data() {
    return {
      mediaMarktLogo: require('../assets/mediamarkt-logo-png-transparent.png'),
      expanded: false,
      mobile: false,
      showQRCodeStream: false,
      error: '',
      showModal: false,
      mobile: false,
      images: [],
      filteredImages: [],
      searchQuery: ''
    }
  },
  created() {
    for (let i in imageData) {
        this.images.push(imageData[i]);
        this.filteredImages.push(imageData[i])
    }
  },
  watch: {},
  computed: {
    filterImages(){
      if(this.searchQuery !== '') {
        this.filteredImages.filter(image =>
            image.laptop.ean.includes(this.searchQuery) ||
            image.imageMaker.includes(this.searchQuery) ||
            image.status.includes(this.searchQuery) ||
            image.upDateDate.includes(this.searchQuery)
        )
        console.log(this.filteredImages);
        return this.filteredImages
      }

      return this.filterImages()
    }
  },
  methods: {
    expandSearch() {
      this.expanded = !this.expanded;
    },
    updateLocale() {
      // update the i18n locale when the user selects a different language
      if (this.$i18n.locale === 'nl') {
        this.$i18n.locale = 'nl';
      } else if (this.$i18n.locale === 'en') {
        this.$i18n.locale = 'en';
      } else {
        this.$i18n.locale = 'fr';
      }
    },
    checkScreenWidth() {
      if (window.innerWidth < 600) {
        this.mobile = !this.mobile;
      }
    },
    toggleStreamBarcodeReader() {
      this.showModal = !this.showModal;
    },
    onDecode(code) {
      if (code) {
        barcode.value = code
        setTimeout(() => {
          this.showModal = false
        }, 1500)
      }
      // Made to reset the scanner but not necessary anymore
      /*setTimeout(() => {
        barcode.value = null
      }, 500);*/
    },
  }
}
</script>

<style scoped>

.logo {
  width: 400px;
  /*z-index: 0;*/
}

.input-group-lg {
  display: flex;
  margin-left: 10%;
  width: 30%;
}

.bi-search {
  margin-right: 5px;
  margin-top: 5px;
}

.bell {
  padding: 5px;
  position: absolute;
  right: 5%;
}

.bell:hover {
  border: solid lightgrey 1px;
  border-radius: 8px;
}

.form-control {
  border: none;
  background-color: #FAFAFA;
}

.form-control::placeholder {
  color: lightgrey;
}

.form-control:focus {
  border-color: #DA1C25;
  box-shadow: 0 0 0 0.15rem rgba(218, 28, 37, 0.25);
}

.headerContent {
  position: absolute;
  background-color: white;
  width: 100%;
  height: 100px;
  display: flex;
  align-items: center;
  padding: 20px;
  overflow-y: hidden;
  border-bottom: solid lightgrey 2px;
  overflow-x: hidden;
}

.mobile {
  display: none;
}

.translation-mobile {
  display: none;
}


@media (max-width: 700px) {

  .search-result {
    font-size: x-small;
    width: 80%;
    margin-left: 15%;
    margin-top: 10px;
    display: flex;
    background-color: white;
    border-radius: 5px;
    padding: 10px;
  }

  .translation-mobile {
    display: inherit;
    width: 145px;
    margin-left: -40px;
  }

  .translation {
    display: none;
  }

  .headerContent {
    height: 75px;
    border-bottom: none;
  }

  .logo {
    width: 150px;
    position: static;
    top: 10px;
  }

  .input-group-lg {
    display: none;
  }

  .mobile {
    display: inherit;
    position: absolute;
    right: 15%;
  }

  .page {
    position: absolute;
    width: 100%;
    height: 76px;
    background-color: white;
    border-bottom: solid lightgrey 2px;
    transition: 250ms ease-in-out;
    z-index: 1;
  }

  .expanded {
    height: 130px !important;
    transition: 250ms ease-in-out;
  }

  .input-expanded {
    margin-top: 80px;
    width: 80%;
    margin-left: 15%;
  }

  .bi-qr-code-scan {
    position: absolute;
    margin-left: 20px;
    transition: 250ms ease-in-out;
  }
}
.v-enter-active {
  transition: opacity 1s ease;
}
.v-leave-active {
  transition: opacity 0.1s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}

.language {
  margin-left: 50px
}

.language:hover {
  border-color: salmon;
}


/*BarcodeScanner*/
.barcodeScanner-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}


/*modal*/
.modal {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border: 1px solid #888;
  max-width: 80%;
  max-height: 80%;
  overflow: auto;
  position: relative;
}

.close {
  color: #aaa;
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
}
</style>