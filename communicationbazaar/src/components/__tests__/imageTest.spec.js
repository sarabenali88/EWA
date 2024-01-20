import {Image} from "@/models/Image";
import {Laptop} from "@/models/laptop";
import {Account} from "@/models/Account";
import {mount} from "@vue/test-utils";
import {reactive} from "vue";
import i18n from "@/i18n";
import {ImagesAdaptor} from "@/services/ImagesAdaptor";
import allImagesComponent from "@/components/allImagesComponent";
import {createMemoryHistory, createRouter} from "vue-router";
import WelcomeComponent from "@/components/WelcomeComponent";

let laptop1;
let account1;
let image1, image2;
let wrapper;

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

it('should renders properly', function () {

    expect(wrapper.element.children.length,
        `main page starting with ${wrapper.element.tagname} has no child elements`)
        .toBeGreaterThan(0);
});

it('should create a proper sample image', function () {
    expect(image2.id,
        'Image2 id was not set properly')
        .toBe(image1.id + 1);
    expect(image2.name,
        'Image2 name was not set properly')
        .toBeTruthy();
});

it('should have a list of images', function () {
    //const onRouterPushSpi = jest.spyOn(wrapper.vm.$router,'push');
    console.log(wrapper.html());

    const tableRow = wrapper.findAll('tr').length
    const imageList = wrapper.vm.images.length

    expect(imageList,
        'List of images are not loaded')
        .toBe(tableRow)
}); 