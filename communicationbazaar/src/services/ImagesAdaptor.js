/**
 * This is a class that will fetch the data for the images from the back end in the front end.
 *
 * @author Jasper Fernhout
 */
import { Image } from '@/models/Image'

export class ImagesAdaptor {
    resourcesUrl;

    constructor (resourcesUrl) {
        this.resourcesUrl = process.env.VUE_APP_API_URL + resourcesUrl;
    }

    async fetchJson (url, options = null) {
        console.log(url);
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

    /**
     * Saves a new image if the id is 0 or updates an image
     * @param image -an image that will be created or updated
     * @return {Promise<any|null>}
     *
     * @author Sara Benali
     */

    async asyncSave(image) {
        if (image.id === 0) {
            return await this.fetchJson(this.resourcesUrl, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id: image.id,
                    laptop: image.laptop,
                    version: image.version,
                    store: image.store,
                    upDateDate: image.upDateDate,
                    status: image.status,
                    release: image.release,
                    problem: image.problem,
                    createdWeek: image.createdWeek,
                    createdYear: image.createdYear,
                    name: image.name,
                    comment: image.comment,
                    imageMaker: image.imageMaker
                })
            });
        } else {
            return this.fetchJson(this.resourcesUrl + "/" + image.id,
                {
                    method: "PUT",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        id: image.id,
                        laptop: image.laptop,
                        version: image.version,
                        store: image.store,
                        upDateDate: image.upDateDate,
                        status: image.status,
                        release: image.release,
                        problem: image.problem,
                        createdWeek: image.createdWeek,
                        createdYear: image.createdYear,
                        name: image.name,
                        comment: image.comment,
                        imageMaker: image.imageMaker
                    })
                });
        }
    }

    async asyncDeleteById (id) {
        return this.fetchJson(this.resourcesUrl + '/' + id,
            {
                method: 'DELETE'
            });
    }
}
