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
    return this.fetchJson(this.resourcesUrl + '/' + laptop.ean,
      {
        method: 'POST'
      });
  }

  async asyncDeleteById (ean) {
    return this.fetchJson(this.resourcesUrl + '/' + ean,
      {
        method: 'DELETE'
      });
  }
}
