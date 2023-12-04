<template>
  <div>
    <h2>Claimed Images</h2>
    <table className="table table-sm">
      <thead>
      <tr>
        <th scope="col">EAN</th>
        <th scope="col">Laptop naam</th>
        <th scope="col">Medewerker</th>
        <th scope="col">Vestiging</th>
        <th scope="col">Status</th>
        <th scope="col">Datum</th>
      </tr>
      </thead>
      <tbody>
      <tr v-if="claimedImage">
        <td>{{ claimedImage.laptop[0].ean }}</td>
        <td>{{ claimedImage.name }}</td>
        <td>{{ claimedImage.imageMaker }}</td>
        <td>{{ claimedImage.store }}</td>
        <td>{{ claimedImage.status }}</td>
        <td>{{ claimedImage.upDateDate }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import imageData from '@/image.json';
import imageDetailComponent from "@/components/ImageDetailComponent";

export default {
  name: 'ClaimedImageComponent',
  components: imageDetailComponent,
  data() {
    return {
      claimedImage: null,
    };
  },
  created() {
    const claimedImageParam = this.$route.params.claimedImage;
    if (claimedImageParam) {
      this.claimedImage = JSON.parse(claimedImageParam);
    } else {
      this.claimedImage = imageData[0];
    }
  },
};
</script>


<style scoped>

</style>
