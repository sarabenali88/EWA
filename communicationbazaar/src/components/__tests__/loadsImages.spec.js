/**
 * Test for testing the image service and component
 * @author Sara Benali
 */
import { ImagesAdaptor } from "@/services/ImagesAdaptor";
import { mount } from "@vue/test-utils";
import { reactive } from "vue";
import i18n from "@/i18n";
import { createMemoryHistory, createRouter } from "vue-router";
import WelcomeComponent from "@/components/WelcomeComponent";
import {Account} from "@/models/Account";
import {Laptop} from "@/models/laptop";
import {Image} from "@/models/Image";
import allImagesComponent from "@/components/allImagesComponent";

let wrapper;
let laptop1;
let account1;
let image1, image2;

const mockRoutes = [
    {
        path: '/',
        component: WelcomeComponent
    },
];

beforeEach(async function () {
    account1 = new Account(1, 1, 1, 1, 1, 1, false)
    laptop1 = new Laptop(1, 1000, "Brand", "Desc", 'Processor', "RAM", "Storage", "GPU", 10, 20, "OS", 100)
    image1 = new Image(1, laptop1, 1, 1, 1, Image.Status.TODO, Image.Release.NEW, null, 1, 2024, "Image", "", account1)
    image2 = Image.createSampleImage(image1.id + 1)

    const router = createRouter({
        history: createMemoryHistory(),
        routes: mockRoutes,
    })

    const mockedImages = [image1, image2];
    const imagesService = new ImagesAdaptor('http://localhost:8086/api');

    jest.spyOn(imagesService, "asyncFindAll").mockResolvedValue(mockedImages);
    jest.spyOn(imagesService, "asyncFindById").mockImplementation(async (id) => {
        const foundImage = mockedImages.find(image => image.id === id);
        return foundImage ? foundImage : null;
    });
    jest.spyOn(imagesService, "asyncSave").mockImplementation(async (image) => {
        if (image.id === 0) {
            return {
                id: 123, // Mocked ID for a newly saved image
            };
        }
        return {
            id: image.id,
        };
    });
    jest.spyOn(imagesService, "asyncDeleteById").mockResolvedValue({success: true});

    wrapper = mount(allImagesComponent, {
        global: {
            provide: {
                "imagesService": reactive(imagesService)
            },
            plugins: [i18n, router]
        }
    });
})


it('renders properly', async () => {
    await wrapper.vm.$nextTick();

    expect(wrapper.exists()).toBe(true);

    expect(wrapper.element.children.length).toBeGreaterThan(0);

    expect(wrapper.text()).toContain(wrapper.vm.$t('allImages.titleName'));

    expect(wrapper.find('table').exists()).toBe(true);

    // Wait for the next tick to ensure the component has updated
    await wrapper.vm.$nextTick();

});

it('should load all the images', function () {
    const tableRow = wrapper.findAll('tr').length
    const imageList = wrapper.vm.images.length

    expect(imageList,
        'List of images are not loaded')
        .toBe(tableRow)
});

