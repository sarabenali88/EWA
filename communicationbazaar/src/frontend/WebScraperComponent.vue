<template>
  <div>
    <h1>
      Test
    </h1>

    <h1>
      Laptops random lijst
    </h1>

    <div>
      <table>
        <thead>
        <tr>
          <th v-for="column in laptopColumns" :key="column.id">{{ column.name }}</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="laptop in laptops" :key="laptop.id">
          <td>{{ laptop.articleNumber }}</td>
          <td>{{ laptop.ean }}</td>
          <td>{{ laptop.brand }}</td>
          <td>{{ laptop.description }}</td>
          <td>{{ laptop.processor }}</td>
          <td>{{ laptop.ram }}</td>
          <td>{{ laptop.storage }}</td>
          <td>{{ laptop.gpu }}</td>
          <td>{{ laptop.sizeInch }}</td>
          <td>{{ laptop.sizeCm }}</td>
          <td>{{ laptop.os }}</td>
          <td>{{ laptop.prize }}</td>
        </tr>
        </tbody>
      </table>
    </div>

    <h1>
      Images random lijst
    </h1>

    <div>
      <table>
        <thead>
        <tr>
          <th v-for="column in imageColumns" :key="column.id">{{ column.name }}</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="image in images" :key="image.id">
          <td>{{ image.laptop.ean }}</td>
          <td>{{ image.version }}</td>
          <td>{{ image.store }}</td>
          <td>{{ image.upDateDate }}</td>
          <td>{{ image.status }}</td>
          <td>{{ image.release }}</td>
          <td>{{ image.problem }}</td>
          <td>{{ image.week }}</td>
          <td>{{ image.year }}</td>
          <td>{{ image.name }}</td>
          <td>{{ image.comment }}</td>
          <td>{{ image.imageMaker }}</td>
        </tr>
        </tbody>
      </table>
    </div>

    <h1>
      Accounts random lijst
    </h1>

    <div>
      <table>
        <thead>
        <tr>
          <th v-for="column in accountColumns" :key="column.id">{{ column.name }}</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="account in accounts" :key="account.id">
          <td>{{ account.personalNumber }}</td>
          <td>{{ account.password }}</td>
          <td>{{ account.name }}</td>
          <td>{{ account.email }}</td>
          <td>{{ account.role }}</td>
          <td>{{ account.imagesOnGoing[0].laptop.ean
            }}</td>
          <td>{{ account.imagesDone[0].laptop.ean }}</td>
          <td>{{ account.loggedIn }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
/**
 * This is a testcomponent for all the experiments.
 *
 * @author Jasper Fernhout
 */

export default {
  name: "WebScraperComponent",
  inject: ["imagesService", "laptopsService", "accountsService"],

  data() {
    return {
      laptops: [],
      laptopColumns: [
        { name: 'articleNumber' },
        { name: 'ean' },
        { name: 'brand' },
        { name: 'description' },
        { name: 'processor' },
        { name: 'ram' },
        { name: 'storage' },
        { name: 'gpu' },
        { name: 'sizeInch' },
        { name: 'sizeCm' },
        { name: 'os' },
        { name: 'prize' }
      ],
      images: [],
      imageColumns: [
        { name: 'laptop' },
        { name: 'version' },
        { name: 'store' },
        { name: 'upDateDate' },
        { name: 'status' },
        { name: 'release' },
        { name: 'problem' },
        { name: 'week' },
        { name: 'year' },
        { name: 'name' },
        { name: 'comment' },
        { name: 'imageMaker' }
      ],
      accounts: [],
      accountColumns: [
        { name: 'personalNumber' },
        { name: 'password' },
        { name: 'name' },
        { name: 'email' },
        { name: 'role' },
        { name: 'imagesOnGoing' },
        { name: 'imagesDone' },
        { name: 'loggedIn' }
      ]
    }
  },
  methods: {
  },
  async created() {
    this.laptops = await this.laptopsService.asyncFindAll();
    this.images = await this.imagesService.asyncFindAll();
    this.accounts = await this.accountsService.asyncFindAll();
  }
}

</script>

<style scoped>
table th, table td {
  padding: 10px;
  border: 1px solid black;
}

table th {
  border: 1px solid black;
}
</style>