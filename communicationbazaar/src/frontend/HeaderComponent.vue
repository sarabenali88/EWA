<template>
    <div class="headerContent">
      <!-- Mediamarkt logo-->
      <img class="logo" :src="mediaMarktLogo" alt="">
      <!-- Input Search Image-->
      <div class="input-group-lg">
        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="lightgrey" class="bi bi-search" viewBox="0 0 16 16">
          <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
        </svg>
        <input type="text" :placeholder="$t('header.placeholder')" class="input form-control">
      </div>
      <!--Translation select-->
      <div>
        <select class="form-select language" v-model="$i18n.locale" @change="updateLocale">
          <option value="nl">Nederlands</option>
          <option value="en">English</option>
          <option value="fr">Français</option>
        </select>
      </div>
      <!-- button for QR code-->
      <button  type="button" class="btn btn-danger m-lg-3" @click="toggleQRCodeStream">Scan for an image</button>
      <!-- QR code in modal pop up-->
      <div v-if="showModal" class="modal">
        <div class="modal-content">
          <span class="close" @click="toggleQRCodeStream">&times;</span>
          <div class="qrcode-container">
          <qrcode-stream @init="onInit" class="qrcode-stream"></qrcode-stream>
          </div>
        </div>
      </div>
      <!-- Alert button-->
      <div class="bell">
        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="lightgrey" class="bi bi-bell" viewBox="0 0 16 16">
          <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"/>
        </svg>
      </div>
    </div>

</template>

<script>
import { QrcodeStream } from 'vue3-qrcode-reader'
export default {
  name: 'HeaderComponent',
  components: {
    QrcodeStream
  },
  data () {
    return {
      mediaMarktLogo: require('../assets/mediamarkt-logo-png-transparent.png'),
      showQRCodeStream: false,
      error: '',
      showModal: false,
    }
  },
  watch: {},
  computed: {},
  methods: {
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
    },
    toggleQRCodeStream() {
      this.showModal = !this.showModal;
    },
    async onInit( promise ) {
      try {
        // eslint-disable-next-line no-unused-vars
        const { capabilities } = await promise

        // successfully initialized
      } catch (error) {
        if (error.name === 'NotAllowedError') {
          this.error = "user denied camera access permisson"
        } else if (error.name === 'NotFoundError') {
          this.error = "no suitable camera device installed"
        } else if (error.name === 'NotSupportedError') {
          this.error = "is not served over HTTPS (or localhost)"
        } else if (error.name === 'NotReadableError') {
          this.error = "camera is already in use"
        } else if (error.name === 'OverconstrainedError') {
          this.error = "did you requested the front camera although there is none?"
        } else if (error.name === 'StreamApiNotSupportedError') {
          this.error = "browser seems to be lacking features"
        }
      } finally {
        // hide loading indicator
      }
    },
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
  margin-right: 20px;
  margin-top: 5px;
}

.bell {
  padding: 5px;
  position: absolute;
  right: 40px;
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
  border-bottom: solid lightgrey 2px;
  overflow-y: hidden;
}
.language{
  margin-left: 50px
}
.language:hover{
  border-color: salmon;
}


/*QR code*/
.qrcode-container {
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
