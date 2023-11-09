<template>
  <div class="card h-100">
    <div class="card-body">
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <h6 class="mb-2 text-danger">Account Details</h6>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !accountCopy.name }">
            <label for="fullName">Naam</label>
            <input v-model="accountCopy.name" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !accountCopy.email }">
            <label for="eMail">Email</label>
            <input v-model="accountCopy.email" class="form-control">
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="phone">Personeelsnummer</label>
            <input v-model="accountCopy.personalNumber" class="form-control" readonly>
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group">
            <label for="website">Wachtwoord</label>
            <input v-model="accountCopy.password" class="form-control" type="password" readonly>
          </div>
        </div>
        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
          <div class="form-group" :class="{ 'has-error': !accountCopy.role }">
            <label for="zIp">Rol</label>
            <select v-model="accountCopy.role" class="form-select" aria-label="Default select example">
              <option value="" disabled selected>Selecteer</option>
              <option value="ImageMaker">ImageMaker</option>
              <option value="admin">admin</option>
            </select>
          </div>
        </div>
      </div>
      <div class="row gutters">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <div class="text-right">
            <button @click="cancelEvent()" class="btn btn-secondary">Annuleren</button>
            <button @click="saveEvent()" class="btn btn-success m-lg-2">OK</button>
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
      if (!this.hasChanged()) {
        if (confirm("Weet je zeker dat je wilt annuleren?")) {
          this.$emit('cancelEvent', null);
        }
      } else {
        this.$emit('cancelEvent', null);
      }
    },
    saveEvent() {
      if (this.fieldsFilledCheck(this.accountCopy)) {
        if (!this.hasChanged()) {
          if (confirm("Weet u zeker dat u wilt wijzigen?")) {
            this.$emit('saveEvent', this.accountCopy);
          }
        } else {
          this.$emit('cancelEvent', null);
        }
      }
    },
    copyAccount(currentAccount) {
      this.accountCopy = JSON.parse(JSON.stringify(currentAccount));
    },
    hasChanged() {
      return this.compareAccounts(this.accountCopy, this.currentAccount);
    },
    compareAccounts(accountCopy, currentAccount) {
      if (!accountCopy || !currentAccount) {
        return false;
      }

      return accountCopy.personalNumber === currentAccount.personalNumber &&
          accountCopy.password === currentAccount.password &&
          accountCopy.name === currentAccount.name &&
          accountCopy.email === currentAccount.email &&
          accountCopy.role === currentAccount.role;
    },
    fieldsFilledCheck(accountCopy) {
      if (!accountCopy.name || !accountCopy.email || !accountCopy.role) {
        alert("Niet elk veld is ingevuld")
        return false;
      } else {
        return true;
      }
    }
  }
}
</script>

<style scoped>
.has-error input, .has-error select {
  border-color: red;
  box-shadow: 0 0 10px red;
}
</style>