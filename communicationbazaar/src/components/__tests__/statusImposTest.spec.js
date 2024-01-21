import {ImagesAdaptor} from "@/services/ImagesAdaptor";
import {mount} from "@vue/test-utils";
import {reactive} from "vue";
import i18n from "@/i18n";
import {LaptopsAdaptor} from "@/services/LaptopsAdaptor";
import imageDetailComponent from "@/components/ImageDetailComponent";

let wrapper;

const mockImages = [
    {
        selectedLaptop: "4710180623840",
        startVersion: "1.0",
        imageName: "NewImage",
        statusImage: "TODO",
        date: "2024-01-15",
        week: "2024-W02",
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

beforeEach(async function () {

    const imagesService = new ImagesAdaptor("http://localhost:8086/api");
    const laptopsService = new LaptopsAdaptor("http://localhost:8086/api");

    jest.spyOn(imagesService, "asyncFindAll").mockResolvedValue(mockImages);
    jest.spyOn(laptopsService, "asyncFindAll").mockResolvedValue(mockLaptops);

    jest
        .spyOn(imagesService, "asyncFindById")
        .mockImplementation(async (id) => {
            const foundImage = mockImages.find((image) => image.id === id);
            return foundImage ? foundImage : null;
        });

    jest
        .spyOn(imagesService, "asyncSave")
        .mockImplementation(async (image) => {
            if (image.id === 0) {
                image.id = mockImages.length + 1;
                return image;
            } else {
                mockImages.push(image);
                return image;
            }
        });

    jest
        .spyOn(imagesService, "asyncDeleteById")
        .mockResolvedValue({success: true});

    wrapper = mount(imageDetailComponent, {
        global: {
            provide: {
                imagesService: reactive(imagesService),
                laptopsService: reactive(laptopsService),
            },
            plugins: [i18n],
        },
    });

});

it('should be able to set the status to Impossible', async function () {
    const button = wrapper.find('.edit-button');
    expect(button.exists()).toBe(true);
    await button.trigger('click');

    await wrapper.vm.$nextTick();

    await wrapper.setData({'imageCopy.status': 'IMPOSSIBLE'});

    const commentTextArea = wrapper.find('textarea');
    await commentTextArea.setValue('This is a test comment');

    await wrapper.find('#save-button').trigger('click');

    await wrapper.vm.$nextTick();

});