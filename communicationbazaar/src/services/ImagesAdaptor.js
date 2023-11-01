import { Image } from '@/models/Image'

export class ImagesAdaptor {
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
        const images = await this.fetchJson(this.resourcesUrl);
        return images?.map(image => Image.copyConstructor(image));
    }

    async asyncFindById (ean) {
        const imageData = await this.fetchJson(this.resourcesUrl + '/' + ean);
        return Image.copyConstructor(imageData);
    }

    async asyncSave (image) {
        return this.fetchJson(this.resourcesUrl + '/' + image.laptop.ean,
            {
                method: 'POST'
            });
    }

    async asyncDeleteById (id) {
        return this.fetchJson(this.resourcesUrl + '/' + id,
            {
                method: 'DELETE'
            });
    }
}
