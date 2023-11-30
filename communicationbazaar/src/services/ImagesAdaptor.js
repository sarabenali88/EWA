/**
 * This is a class that will fetch the data for the images from the back end in the front end.
 *
 * @author Jasper Fernhout
 */
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

    async asyncFindById (id) {
        const imageData = await this.fetchJson(this.resourcesUrl + '/' + id);
        return Image.copyConstructor(imageData);
    }

    async asyncSave(image) {
        let checkEan = await this.asyncFindById(image.laptop.ean);
        if (checkEan == null) {
            return await this.fetchJson(this.resourcesUrl, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    laptop: image.laptop,
                    version: image.version,
                    store: image.store,
                    upDateDate: image.upDateDate,
                    status: image.status,
                    release: image.release,
                    problem: image.problem,
                    week: image.week,
                    year: image.year,
                    name: image.name,
                    comment: image.comment,
                    imageMaker: image.imageMaker
                })
            });
        } else {
            return this.fetchJson(this.resourcesUrl + "/" + image.laptop,
                {
                    method: "PUT",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        image
                    })
                });
        }
    }

    async asyncDeleteById (ean) {
        return this.fetchJson(this.resourcesUrl + '/' + ean,
            {
                method: 'DELETE'
            });
    }
}
