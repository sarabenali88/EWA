<template>
  <div v-if="laptopCopy">
    <div class="row justify-content-between">
      <h4>
        {{ $t('laptop.editMessage') }} {{ laptopCopy.ean }}
      </h4>
      <div class="pt-2 m-1">
        <div v-if="!valid" class="alert alert-danger" role="alert">
          {{$t('laptop.alertMessage')}}
        </div>
        <div class="row gutters">
          <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
            <div class="form-group">
              <label for="articleNumber">{{ $t('imageDetail.articleNumber') }}</label>
              <input class="form-control" v-model="laptopCopy.articleNumber">
            </div>
          </div>
          <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
            <div class="form-group">
              <label for="brand">{{ $t('laptop.brand') }}</label>
              <input class="form-control" v-model="laptopCopy.brand">
            </div>
          </div>
          <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
            <div class="form-group">
              <label for="description">{{ $t('imageDetail.description') }}</label>
              <input class="form-control" v-model="laptopCopy.description">
            </div>
          </div>
          <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
            <div class="form-group">
              <label for="processor">{{ $t('laptop.processor') }}</label>
              <input class="form-control" v-model="laptopCopy.processor">
            </div>
          </div>
          <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
            <div class="form-group">
              <label for="articleNumber">RAM</label>
              <input class="form-control" v-model="laptopCopy.ram">
            </div>
          </div>
          <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
            <div class="form-group">
              <label for="articleNumber">GPU</label>
              <input class="form-control" v-model="laptopCopy.gpu">
            </div>
          </div>
        </div>
        <div class="row justify-content-md-end">
          <div class="col-auto pt-2">
            <button type="button" class="btn btn-danger m-1" @click="saveLaptop()">{{$t('imageDetail.saveButton')}}</button>
            <button type="button" class="btn btn-outline-secondary m-2" @click="this.$emit('refresh')">{{$t('imageDetail.closeButton')}}</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LaptopDetailComponent",
  inject: ["laptopsService"],
  data() {
    return {
      laptopCopy: null,
      valid: true
    }
  },
  watch: {
    '$route'() {
      this.reInitialise();
    }
  },
  async created() {
    await this.reInitialise();
  },
  methods: {
    /**
     * A methode that reinitializes the editing view
     *
     * @author Seyma Kaya
     * @returns {Promise<void>}
     */
    async reInitialise() {
      this.laptopCopy =
          await this.laptopsService.asyncFindById(this.$route?.params?.id)
    },
    /**
     * A methode that saves the edited image and updates the view
     *
     * @author Seyma Kaya
     * @returns {Promise<void>}
     */
    async saveLaptop(){
      if (this.checkInput()) {
        await this.laptopsService.asyncSave(this.laptopCopy);
        this.$emit('refresh')
        // eslint-disable-next-line no-empty
      } else {
        this.valid = false
      }
    },
    /**
     * A methode that checks if all the input fields were filled in.
     *
     * @author Seyma Kaya
     * @returns {boolean} Whether all the input is correctly filled in
     */
    checkInput(){
      if (this.laptopCopy.articleNumber === "" || this.laptopCopy.brand === "" || this.laptopCopy.ram === "" ||
          this.laptopCopy.description === "" || this.laptopCopy.processor === "" || this.laptopCopy.gpu === "") {
        return false;
      } return true;
    }
  }
}
</script>

<style scoped>

</style>