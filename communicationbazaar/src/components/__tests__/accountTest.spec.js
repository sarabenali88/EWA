import {mount} from "@vue/test-utils";
import ProfilePageComponent from "@/components/ProfilePageComponent";
import i18n from "@/i18n";
import {reactive} from "vue";

/**
 * A test class for our relation between Image and Account
 *
 * @author Jonathan
 */
describe("ProfilePageComponent", () => {

    //variable to store the component wrapper
    let wrapper;

    //mock data for testing
    const mockAccounts = [
        {
            personalNumber: 10000,
            password: "IloveApples4%",
            name: "Pieter van den Broek",
            email: "pietervdbroek@gmail.com",
            role: "admin",
            location: "Amsterdam",
            loggedIn: true
        },
        {
            personalNumber: 10001,
            password: "IloveApps4%",
            name: "Piet van den Broek",
            email: "pietervdbroek@gmail.com",
            role: "admin",
            location: "Amsterdam",
            loggedIn: false
        }
    ]

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
                name: "Pieter van den Broek"
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
                name: "Pieter van den Broek"
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

    //setting up mock services for dependency injection
    beforeEach(async () => {

        //mock services for dependecy injection
        const imagesService = {
            asyncFindAll: jest.fn().mockResolvedValue(mockImages),
        };

        const laptopsService = {
            asyncFindAll: jest.fn().mockResolvedValue(mockLaptops)
        }

        const accountsService = {
            asyncFindAll: jest.fn().mockResolvedValue(mockAccounts),
            asyncGetImagesFromAccount : jest.fn().mockResolvedValue(mockImages)
        }

        //mounting the component with mocked services and initial data
        wrapper = mount(ProfilePageComponent, {
            global: {
                provide: {
                    "imagesService": reactive(imagesService),
                    "laptopsService": reactive(laptopsService),
                    "accountsService": reactive(accountsService)
                },
                plugins: [i18n]
            },
            data() {
                return {
                    loggedInAccount: mockAccounts[0],
                }
            }
        });


    });

    it("should display correct amount of images on profile page", async () => {

        // Get the actual images from the component
        const images = wrapper.vm.images
        expect(images).toHaveLength(2)

        // Each image is represented by two rows
        const expectedRowCount = images.length * 2;
        const tableRows = wrapper.findAll("tbody tr");
        expect(tableRows).toHaveLength(expectedRowCount);

    });


    it("should display correct image names in the table", () => {

        // Get all images and table rows from profile
        const images = wrapper.vm.images;
        const tableRows = wrapper.findAll("tbody tr");

        // For each row, check if the imageMaker name is correct
        tableRows.forEach((row, index) => {
            const expectedName = images[Math.floor(index / 2)].imageMaker.name;
            expect(row.text()).toContain(expectedName);
        });
    });

    it("should delete the selected image when calling deleteImage", async () => {

        // Select the first image and delete it
        const selectedImage = wrapper.vm.images[0];
        wrapper.setData({ selectedImage });
        await wrapper.vm.deleteImage();
        expect(wrapper.vm.images).not.toContain(selectedImage);
    });


});