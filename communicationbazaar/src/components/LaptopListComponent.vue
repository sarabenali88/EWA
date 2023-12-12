<template>
<div class="m-2">
  <h1 class="mx-3">{{$t('laptop.allLaptops')}}</h1>
  <div class="container-fluid p-3 normal">
<!--    1st view-->
    <div class="container">
      <div class="row">
        <div class="card col-md-5 m-3 p-3" v-for="laptop of laptops" v-bind:key="laptop.ean">
          <div class="col-auto">
            <h4>
              {{laptop.brand}} {{laptop.description}}
            </h4>
          </div>
          <div class="row justify-content-md-left">
            <div class="col col-md-5">
              {{ $t('imageDetail.ean') }}: {{laptop.ean}}
            </div>
            <div class="col-md-auto">
              {{ $t('imageDetail.articleNumber') }}: {{laptop.articleNumber}}
            </div>
          </div>
          <div class="mt-2">
            <div class="row justify-content-md-left">
              <div class="col col-sm-4 text-body-tertiary">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cpu" viewBox="0 0 16 16">
                  <path d="M5 0a.5.5 0 0 1 .5.5V2h1V.5a.5.5 0 0 1 1 0V2h1V.5a.5.5 0 0 1 1 0V2h1V.5a.5.5 0 0 1 1 0V2A2.5 2.5 0 0 1 14 4.5h1.5a.5.5 0 0 1 0 1H14v1h1.5a.5.5 0 0 1 0 1H14v1h1.5a.5.5 0 0 1 0 1H14v1h1.5a.5.5 0 0 1 0 1H14a2.5 2.5 0 0 1-2.5 2.5v1.5a.5.5 0 0 1-1 0V14h-1v1.5a.5.5 0 0 1-1 0V14h-1v1.5a.5.5 0 0 1-1 0V14h-1v1.5a.5.5 0 0 1-1 0V14A2.5 2.5 0 0 1 2 11.5H.5a.5.5 0 0 1 0-1H2v-1H.5a.5.5 0 0 1 0-1H2v-1H.5a.5.5 0 0 1 0-1H2v-1H.5a.5.5 0 0 1 0-1H2A2.5 2.5 0 0 1 4.5 2V.5A.5.5 0 0 1 5 0m-.5 3A1.5 1.5 0 0 0 3 4.5v7A1.5 1.5 0 0 0 4.5 13h7a1.5 1.5 0 0 0 1.5-1.5v-7A1.5 1.5 0 0 0 11.5 3zM5 6.5A1.5 1.5 0 0 1 6.5 5h3A1.5 1.5 0 0 1 11 6.5v3A1.5 1.5 0 0 1 9.5 11h-3A1.5 1.5 0 0 1 5 9.5zM6.5 6a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5z"/>
                </svg>
                {{ $t('laptop.processor') }}:
              </div>
              <div class="col-sm-auto">
                {{laptop.processor}}
              </div>
            </div>
            <div class="row justify-content-md-left">
              <div class="col col-sm-4 text-body-tertiary">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-memory" viewBox="0 0 16 16">
                  <path d="M1 3a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h4.586a1 1 0 0 0 .707-.293l.353-.353a.5.5 0 0 1 .708 0l.353.353a1 1 0 0 0 .707.293H15a1 1 0 0 0 1-1V4a1 1 0 0 0-1-1zm.5 1h3a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-4a.5.5 0 0 1 .5-.5m5 0h3a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-4a.5.5 0 0 1 .5-.5m4.5.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5zM2 10v2H1v-2zm2 0v2H3v-2zm2 0v2H5v-2zm3 0v2H8v-2zm2 0v2h-1v-2zm2 0v2h-1v-2zm2 0v2h-1v-2z"/>
                </svg>
                RAM:
              </div>
              <div class="col-sm-auto">
                {{laptop.ram}}
              </div>
            </div>
            <div class="row justify-content-md-left">
              <div class="col col-sm-4 text-body-tertiary">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gpu-card" viewBox="0 0 16 16">
                  <path d="M4 8a1.5 1.5 0 1 1 3 0 1.5 1.5 0 0 1-3 0m7.5-1.5a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3"/>
                  <path d="M0 1.5A.5.5 0 0 1 .5 1h1a.5.5 0 0 1 .5.5V4h13.5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5H2v2.5a.5.5 0 0 1-1 0V2H.5a.5.5 0 0 1-.5-.5m5.5 4a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5M9 8a2.5 2.5 0 1 0 5 0 2.5 2.5 0 0 0-5 0"/>
                  <path d="M3 12.5h3.5v1a.5.5 0 0 1-.5.5H3.5a.5.5 0 0 1-.5-.5zm4 1v-1h4v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5"/>
                </svg>
                GPU:
              </div>
              <div class="col-sm-auto">
                {{laptop.gpu}}
              </div>
            </div>
            <div class="row justify-content-md-left">
              <div class="col col-sm-4 text-body-tertiary">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-window-dock" viewBox="0 0 16 16">
                  <path d="M3.5 11a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3.5.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm4.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                  <path d="M14 1a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2zM2 14h12a1 1 0 0 0 1-1V5H1v8a1 1 0 0 0 1 1M2 2a1 1 0 0 0-1 1v1h14V3a1 1 0 0 0-1-1z"/>
                </svg>
                {{ $t('imageDetail.os') }}:
              </div>
              <div class="col-sm-auto">
                {{laptop.os}}
              </div>
            </div>
        </div>
      </div>
    </div>
    </div>
<!--    2nd view-->
    <table class="table table-sm">
      <thead>
      <tr>
        <th scope="col">{{ $t('imageDetail.ean') }}</th>
        <th scope="col">{{ $t('imageDetail.articleNumber') }}</th>
        <th scope="col">{{ $t('laptop.brand') }}</th>
        <th scope="col">{{ $t('imageDetail.description') }}</th>
        <th scope="col">{{ $t('imageDetail.os') }}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="laptop of laptops" v-bind:key="laptop.ean">
        <td>{{ laptop.ean }}</td>
        <td>{{ laptop.articleNumber }}</td>
        <td>{{ laptop.brand }}</td>
        <td>{{ laptop.description }}</td>
        <td>{{ laptop.os }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
</template>

<script>
export default {
  name: "LaptopListComponent",
  inject: ['laptopsService'],
  data(){
    return{
      laptops: []
    }
  },
  async created() {
    this.laptops = await this.laptopsService.asyncFindAll()
  },
  methods:{

  }
}
</script>

<style scoped>

</style>