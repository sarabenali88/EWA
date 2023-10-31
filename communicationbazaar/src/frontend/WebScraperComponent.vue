<template>
  <div>
    <h1>
      Test
    </h1>
  </div>
</template>

<script>
const axios = require('axios')
 // const cheerio = require('cheerio')



export default {
  name: "WebScraperComponent",
  data() {
    return {
      latestArticles: [],
    }
  },
  methods: {
    getWebsiteData() {
      let url = 'https://www.msn.com/nl-nl';

      axios({
        method: 'get',
        url: url,
      })
          .then(function (response) {
            let html = response.data;

            // let $ = cheerio.load(html);
            console.log(html);
          }).catch(error => {
        if (error.response) {
          // The request was made and the server responded with a non-2xx status code
          console.error('Server responded with an error:', error.response.status);
        } else if (error.request) {
          // The request was made but no response was received
          console.error('No response received from the server.');
        } else {
          // Something else happened while setting up the request
          console.error('Error:', error.message);
        }
      });
    }
  },
  created() {
    this.getWebsiteData();
  }
}
</script>

<style scoped>

</style>