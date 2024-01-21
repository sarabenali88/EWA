import {mount} from "@vue/test-utils";
import headerComponent from "@/components/HeaderComponent";
import i18n from "@/i18n";
import {reactive} from "vue";


describe("HeaderComponent", () => {

    let wrapper;

    beforeEach(async () => {
        const mockImages = [
            {
                id: 1,
                laptop: {
                    ean: 121412301239
                },
                version: 'Sample Version',
                store: 'Sample Store',
                upDateDate: "24-7-2023", // Sample date, you can adjust this as needed
                status: "TODO", // Using the predefined status 'Te doen'
                release: "NEW", // Using the predefined release 'Nieuw'
                problem: "YES", // Using the predefined problem 'Ja'
                createdWeek: 1, // Sample createdWeek value
                createdYear: 2023, // Sample createdYear value
                name: 'Sample Name 1',
                comment: 'Sample Comment',
                imageMaker: {
                    name: "Test"
                }
            },
            {
                id: 2,
                laptop: {
                    ean: 1266630139
                },
                version: 'Sample Version 2',
                store: 'Sample Store',
                upDateDate: "27-7-2023", // Sample date, you can adjust this as needed
                status: "TODO", // Using the predefined status 'Te doen'
                release: "NEW", // Using the predefined release 'Nieuw'
                problem: "YES", // Using the predefined problem 'Ja'
                createdWeek: 1, // Sample createdWeek value
                createdYear: 2023, // Sample createdYear value
                name: 'Sample Name 2',
                comment: 'Sample Comment',
                imageMaker: {
                    name: "Test"
                }
            },
        ];

        const mockLaptops = [
            {
                articleNumber: 12312412,
                ean: 81812838,
                brand: "LENOVO",
                description: "test description",
                processor: "test cpu",
                ram: "test",
                storage: "big",
                gpu: "fast",
                sizeInch: "massive",
                sizeCm: "massive",
                os: "linux",
                prize: 100
            },
            {
                articleNumber: 12312222412,
                ean: 81123652838,
                brand: "ACER",
                description: "test description",
                processor: "test cpu",
                ram: "test",
                storage: "tiny",
                gpu: "Slow",
                sizeInch: "small",
                sizeCm: "small",
                os: "linux",
                prize: 100
            }
        ]

        const imagesService = {
            asyncFindAll: jest.fn().mockResolvedValue(mockImages),
        };

        const laptopsService = {
            asyncFindAll: jest.fn().mockResolvedValue(mockLaptops)
        }

        wrapper = mount(headerComponent, {
            global: {
                provide: {
                    "imagesService": reactive(imagesService),
                    "laptopsService": reactive(laptopsService)
                },
                plugins: [i18n]
            },
        });

    });


    it("should filter images based on search query", async () => {

        const query = "Sample Name";

        // Set the search query
        await wrapper.setData({searchQuery: query});

        // Get the filtered images
        const filteredImages = wrapper.vm.filterImages;

        // Assert that the filtered images match the expected result based on the search query
        expect(filteredImages, 'Expected two images to be filtered based on the search query')
            .toHaveLength(2);

    });

    it("should filter specific image correctly", async () => {

        const query = "Sample Name 2"

        // Set the search query
        await wrapper.setData({searchQuery: query})

        // Get the filtered images
        const filteredImages = wrapper.vm.filterImages;

        // Assert that the filtered images match the expected result based on the search query
        expect(filteredImages[0].name, 'Expected one image, that equals the search query')
            .toEqual(query)

    });

    it("should return correctly when nothing is found", async () => {

        const query = "Sample Name Of Non Existing Image"

        // Set the search query
        await wrapper.setData({searchQuery: query})

        // Get the filtered images
        const filteredImages = wrapper.vm.filterImages;

        // Assert that the filtered images match the expected result based on the search query
        expect(filteredImages, 'Expected no images to be found')
            .toHaveLength(0);

    })

    it("should filter laptops correctly", async () => {

        const query = "test cpu";

        // Set the search query
        await wrapper.setData({searchQuery: query})

        // Get the filtered images
        const filteredLaptops = wrapper.vm.filterLaptop;

        // Assert that the filtered images match the expected result based on the search query
        expect(filteredLaptops, 'Expected two laptops to be filtered based on the search query')
            .toHaveLength(2)

    })
});