<template>
<div>
  {{currentImage.laptop[0].ean}}
</div>
</template>

<script>
export default {
  name: "ImageDetailComponent",
  props: [
    'currentImage'
  ]
}
</script>

<style scoped>

</style>