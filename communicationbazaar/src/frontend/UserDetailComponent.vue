<template>
  <div class="card h-100">
    <div class="card-body">
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <h6 class="mb-2 text-danger">{{$t('adminPanel.title')}}</h6>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="fullName">{{$t('adminPanel.name')}}</label>
            <input v-model="accountCopy.name" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="eMail">{{$t('adminPanel.email')}}</label>
            <input v-model="accountCopy.email" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="phone">{{$t('adminPanel.employeeNumber')}}</label>
            <input v-model="accountCopy.personalNumber" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="website">Wachtwoord</label>
            <input v-model="accountCopy.password" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="zIp">{{$t('adminPanel.role')}}</label>
            <input v-model="accountCopy.role" class="form-control mb-2">
          </div>
        </div>
      </div>
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <div class="text-right">
            <button @click="cancelEvent()" class="btn btn-secondary">{{$t('adminPanel.cancelButton')}}</button>
            <button @click="editEvent()" class="btn btn-danger m-lg-2">{{$t('adminPanel.editButton')}}</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserDetailComponent",
  props: ['currentAccount'],
  data() {
    return {
      showDesc: false,
      accountCopy: null
    }
  },
  created() {
    this.copyAccount(this.currentAccount);
  },
  watch: {
    currentAccount: {
      handler(newAccount) {
        if (newAccount !== null) {
          this.copyAccount(newAccount);
        }
      },
      deep: true,
    }
  },
  methods: {
    cancelEvent() {
      if (this.hasChanged) {
        if (confirm(this.$t('adminPanel.cancelMessage'))) {
          this.$emit('cancelEvent', null);
        }
      }
    },
    editEvent() {
      if (this.hasChanged) {
        if (confirm(this.$t('adminPanel.editMessage'))){
          this.$emit('editEvent', this.accountCopy);
        }
      }
    },
    copyAccount(currentAccount) {
      this.accountCopy = JSON.parse(JSON.stringify(currentAccount));
    },
    hasChanged() {
      return this.accountCopy !== this.currentAccount;
    }
  }
}
</script>

<style scoped>

</style>