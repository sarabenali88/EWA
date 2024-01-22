/**
 * Test for add Image Component
 * @author Sara Benali
 */

import {mount} from "@vue/test-utils";
import {reactive} from "vue";
import i18n from "@/i18n";
import AddImageComponent from "@/components/AddImageComponent";
import {ImagesAdaptor} from "@/services/ImagesAdaptor";
import {LaptopsAdaptor} from "@/services/LaptopsAdaptor";

let wrapper;
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
        id: 0,
        selectedLaptop: mockLaptops.ean,
        startVersion: "1.0",
        imageName: "NewImage",
        statusImage: "TODO",
        date: "2024-01-15",
        week: "2024-W02",

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

    wrapper = mount(AddImageComponent, {
        global: {
            provide: {
                imagesService: reactive(imagesService),
                laptopsService: reactive(laptopsService),
            },
            plugins: [i18n],
        },
    });

});
it("can enter fields for new Image and save it", async function () {
    const button = wrapper.get("#save-button");
    const selectedLaptopField = wrapper.get("#selectedLaptopField");
    const imageNameField = wrapper.get("#imageNameField");
    const startVersionField = wrapper.get("#startVersionField");
    const statusImageField = wrapper.get("#statusImageField");
    const dateField = wrapper.get("#dateField");
    const weekField = wrapper.get("#weekField");

    expect(button.exists()).toBe(true);
    expect(selectedLaptopField.exists()).toBe(true);
    expect(imageNameField.exists()).toBe(true);
    expect(startVersionField.exists()).toBe(true);
    expect(statusImageField.exists()).toBe(true);
    expect(dateField.exists()).toBe(true);
    expect(weekField.exists()).toBe(true);

    await wrapper.setData({selectedLaptop: {ean: "4710180623840"}});
    await imageNameField.setValue("NewImage");
    await startVersionField.setValue("1.0");
    await statusImageField.setValue("TODO");
    await dateField.setValue("2024-01-15");
    await weekField.setValue("2024-W02");
    await wrapper.vm.$nextTick();

    expect(wrapper.vm.selectedLaptop.ean).toBe(mockLaptops[0].ean);
    expect(imageNameField.element.value).toBe(mockImages[0].imageName);
    expect(startVersionField.element.value).toBe(mockImages[0].startVersion);
    expect(statusImageField.element.value).toBe(mockImages[0].statusImage);
    expect(dateField.element.value).toBe(mockImages[0].date);
    expect(weekField.element.value).toBe(mockImages[0].week);

    await button.trigger("click");

});

it('displays the correct translation for English and French', async () => {
    expect(wrapper.text()).toContain('Startklaar Versie');
    expect(wrapper.text()).toContain('Image Naam');

    wrapper.vm.$i18n.locale = 'en';
    await wrapper.vm.$nextTick();

    expect(wrapper.text()).toContain('Ready To Use Version');
    expect(wrapper.text()).toContain('Image Name');

    wrapper.vm.$i18n.locale = 'fr';
    await wrapper.vm.$nextTick();

    expect(wrapper.text()).toContain("Version prête à l'emploi");
    expect(wrapper.text()).toContain("Nom de l'image");



});
