import { ImagesAdaptor } from "@/services/ImagesAdaptor";
import { mount } from "@vue/test-utils";
import { reactive } from "vue";
import i18n from "@/i18n";
import { createMemoryHistory, createRouter } from "vue-router";
import allImagesComponent from "@/components/allImagesComponent";

let wrapper;
const mockRoutes = [
    {
        path: '/',
        component: allImagesComponent
    },
];

const mockLaptops = [
    {
        articleNumber: "1637763",
        ean: "4710180623840",
        brand: "ACER",
        description: "SWIFT 5 SF515-51T-500N",
        processor: "Intel Core i5-8265U",
        ram: "8 GB",
        storage: "512 GB + 512 GB",
        gpu: "UHD Graphics 620",
        sizeInch: "15.6 inch",
        sizeCm: "39.6 cm",
        os: "WIN11",
        prize: "500",
    },
];
const mockImages = [
    {
        id: 1,
        laptop: mockLaptops[0],
        version: 'Sample Version',
        store: 'Sample Store',
        upDateDate: new Date(), // Sample date, you can adjust this as needed
        status: "TODO", // Using the predefined status 'Te doen'
        release: "NEW", // Using the predefined release 'Nieuw'
        problem: "YES", // Using the predefined problem 'Ja'
        createdWeek: 1, // Sample createdWeek value
        createdYear: 2023, // Sample createdYear value
        name: 'Sample Name',
        comment: 'Sample Comment',
        imageMaker: 'Sample Image Maker'
    },
];

beforeEach(async function () {
    const router = createRouter({
        history: createMemoryHistory(),
        routes: mockRoutes,
    })

    const imagesService = new ImagesAdaptor("http://localhost:8086/api");

    jest.spyOn(imagesService, "asyncFindAll").mockResolvedValue(mockImages);

    jest.spyOn(imagesService, "asyncFindById")
        .mockImplementation(async (id) => {
            const foundImage = mockImages.find((image) => image.id === id);
            return foundImage ? foundImage : null;
        });

    jest.spyOn(imagesService, "asyncSave")
        .mockImplementation(async (image) => {
            if (image.id === 0) {
                image.id = mockImages.length + 1;
                return image;
            } else {
                mockImages.push(image);
                return image;
            }
        });

    jest.spyOn(imagesService, "asyncDeleteById")
        .mockResolvedValue({ success: true });

    wrapper = mount(allImagesComponent, {
        global: {
            provide: {
                imagesService: reactive(imagesService),
            },
            plugins: [i18n, router],
        },
    });
});

it('it should open the detail of an image', async () => {
    const vm = wrapper.vm;

    const selectedImage = mockImages[0];

    vm.setImage(selectedImage);

    await wrapper.vm.$nextTick();

    expect(vm.selectedImage).toEqual(selectedImage);
});
