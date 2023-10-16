<template>
  <div class="card h-100">
    <div class="card-body">
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <h6 class="mb-2 text-primary">Account Details</h6>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="fullName">Naam</label>
            <input v-model="accountCopy.name" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="eMail">Email</label>
            <input v-model="accountCopy.email" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="phone">Personeelsnummer</label>
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
            <label for="zIp">Role</label>
            <input v-model="accountCopy.role" class="form-control mb-2">
          </div>
        </div>
      </div>
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <div class="text-right">
            <button @click="cancelEvent()" class="btn btn-secondary">Annuleren</button>
            <button  class="btn btn-primary m-lg-2">Wijzigen</button>
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
        this.copyAccount(newAccount);
      },
      deep: true,
    }
  },
  methods: {
    setNav(word) {
      if (word === 'com') {
        this.showDesc = false;
      }
      if (word === 'desc') {
        this.showDesc = true;
      }
    },
    cancelEvent() {
      if (this.accountCopy !== this.currentAccount) {
        if (confirm("Weet je zeker dat je wilt cancelen?")) {
          this.$emit('cancelAccount', null)
        }
      }
    },
    copyAccount(currentAccount) {
      this.accountCopy = currentAccount;
    }
  }
}
</script>

<style scoped>

</style>