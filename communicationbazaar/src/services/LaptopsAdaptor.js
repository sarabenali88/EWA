/**
 * This is a class that will fetch the data for the laptops from the back end in the front end.
 *
 * @author Jasper Fernhout
 */
import { Laptop } from '@/models/laptop'

export class LaptopsAdaptor {
  resourcesUrl;

  constructor (resourcesUrl) {
    this.resourcesUrl = resourcesUrl;
  }

  async fetchJson (url, options = null) {
    const response = await fetch(url, options);
    if (response.ok) {
      return await response.json();
    } else {
      // TODO hier moet nog een; response error die in de json body zit
      return null;
    }
  }

  async asyncFindAll () {
    const laptops = await this.fetchJson(this.resourcesUrl);
    return laptops?.map(laptop => Laptop.copyConstructor(laptop));
  }

  async asyncFindById (ean) {
    const laptopData = await this.fetchJson(this.resourcesUrl + '/' + ean);
    return Laptop.copyConstructor(laptopData);
  }

  async asyncSave (laptop) {
    const allLaptops = this.asyncFindAll();
    for (const currentLaptop in allLaptops){
      if (currentLaptop.id === laptop.id){
        return this.fetchJson(this.resourcesUrl + '/' + laptop.ean,
            {
              method: 'PUT',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify({
                articleNumber: laptop.articleNumber,
                ean: laptop.ean,
                brand: laptop.brand,
                description: laptop.description,
                processor: laptop.processor,
                ram: laptop.ram,
                storage: laptop.storage,
                gpu: laptop.gpu,
                sizeInch: laptop.sizeInch,
                sizeCm: laptop.sizeCm,
                os: laptop.os,
                prize: laptop.prize
              })
            });
      }
    }
    return this.fetchJson(this.resourcesUrl,
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          articleNumber: laptop.articleNumber,
          ean: laptop.ean,
          brand: laptop.brand,
          description: laptop.description,
          processor: laptop.processor,
          ram: laptop.ram,
          storage: laptop.storage,
          gpu: laptop.gpu,
          sizeInch: laptop.sizeInch,
          sizeCm: laptop.sizeCm,
          os: laptop.os,
          prize: laptop.prize
        })
      });
  }

  async asyncDeleteById (ean) {
    return this.fetchJson(this.resourcesUrl + '/' + ean,
      {
        method: 'DELETE'
      });
  }
}
