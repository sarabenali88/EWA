import {mount} from "@vue/test-utils";
import headerComponent from "@/components/HeaderComponent";
import {ImagesAdaptor} from "@/services/ImagesAdaptor";
import i18n from "@/i18n";
import {reactive} from "vue";

let wrapper

beforeEach(async function () {
    const mockImages = [
        {
            id: 1,
            laptop: 'Sample Laptop',
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
        {
            id: 2,
            laptop: 'Sample Laptop',
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
    const imagesService = new ImagesAdaptor('http://localhost:8086/api');

    jest.spyOn(imagesService, "asyncFindAll").mockResolvedValue(mockImages);

    // Mocking asyncFindById method
    jest.spyOn(imagesService, "asyncFindById").mockImplementation(async (id) => {
        const foundImage = mockImages.find(image => image.id === id);
        return foundImage ? foundImage : null;
    });

    jest.spyOn(imagesService, "asyncSave").mockImplementation(async (image) => {
        if (image.id === 0) {
            return {
                id: 123, // Mocked ID for a newly saved image
            };
        } else {
            return {
                id: image.id,
            };
        }
    });

    jest.spyOn(imagesService, "asyncDeleteById").mockResolvedValue({ success: true });

    wrapper = mount(headerComponent, {
        global: {
            provide: {
                "imagesService": reactive(imagesService)
            },
            plugins: [i18n]
        }
    });
});

it('should renders properly', function () {

    expect(wrapper.element.children.length,
        `main page starting with ${wrapper.element.tagname} has no child elements`)
        .toBeGreaterThan(0);
});

it('should all images have height and alt text', function () {
    const images = wrapper.findAll('img');
    for (let img of images) {
        expect(img.element.tagName).toBe("IMG");
        expect(img.element.height).toBe(0);
        expect(img.element.alt,
            `${img.html()} has no alt text`)
            .toBeTruthy();
    }
});
