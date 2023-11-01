<template>
  <div>
    <h1>
      Test
    </h1>

    <h1>
      Laptops random lijst
    </h1>

    <div>
      <table id="cabinOverview">
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
      <table id="cabinOverview">
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
  </div>
</template>

<script>

export default {
  name: "WebScraperComponent",
  inject: ["imagesService", "laptopsService"],

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
      ]
    }
  },
  methods: {
  },
  async created() {
    this.laptops = await this.laptopsService.asyncFindAll();
    this.images = await this.imagesService.asyncFindAll();
  }
}
</script>

<style scoped>

</style>