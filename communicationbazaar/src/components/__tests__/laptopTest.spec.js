import WelcomeComponent from "@/components/WelcomeComponent";
import {Laptop} from "@/models/laptop";
import {createMemoryHistory, createRouter} from "vue-router";
import {LaptopsAdaptor} from "@/services/LaptopsAdaptor";
import {flushPromises, mount} from "@vue/test-utils";
import {reactive} from "vue";
import i18n from "@/i18n";
import laptopListComponent from "@/components/LaptopListComponent";
import {SessionSbService} from "@/services/SessionSbService";

let laptop1, laptop2;
let wrapper;
let laptopsService;

let mockedLaptops;

const mockRoutes = [
    {
        path: '/',
        component: WelcomeComponent
    },
];

 beforeEach(async function () {
     laptop1 = new Laptop(1, 1000, "Brand", "Desc", 'Processor', "RAM", "Storage", "GPU", 10, 20, "OS", 100)
     laptop2 = new Laptop(1, 1001, "Brand", "Desc", 'Processor', "RAM", "Storage", "GPU", 10, 20, "OS", 100)


     const router = createRouter({
         history: createMemoryHistory(),
         routes: mockRoutes,
     })

     mockedLaptops = [laptop1, laptop2];
     const sessionService = new SessionSbService('http://localhost:8086/api', "MOCK_SESSION")
     laptopsService = new LaptopsAdaptor('http://localhost:8086/api');

     jest.spyOn(laptopsService, "asyncFindAll").mockResolvedValue(mockedLaptops);
     jest.spyOn(laptopsService, "asyncFindById").mockImplementation(async (ean) => {
         const foundLaptop = mockedLaptops.find(image => image.ean === ean);
         return foundLaptop ? foundLaptop : null;
     });
     jest.spyOn(laptopsService, "asyncSave").mockImplementation(async (image) => {
         if (image.ean === 0) {
             return {
                 ean: 123, // Mocked ID for a newly saved image
             };
         }
         return {
             ean: image.ean,
         };
     });
     jest.spyOn(laptopsService, "asyncDeleteById").mockResolvedValue({success: true});

     wrapper = mount(laptopListComponent, {
         global: {
             provide: {
                 "laptopsService": reactive(laptopsService),
                 "sessionService": sessionService
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

it('should have a list of laptops', async function () {
    await wrapper.vm.$nextTick();

    const laptopDiv = wrapper.findAll('#laptop-div').length
    const laptopList = wrapper.vm.laptops.length

    expect(wrapper.vm.laptops.length).toBeGreaterThan(0);
    expect(laptopDiv,
        'List of laptops are not loaded')
        .toBe(laptopList)
});

it('should delete a given laptop', async function () {
    const deleteBTN = wrapper.find("#delete-btn")

    expect(deleteBTN.element.disabled,
        "Delete button is disabled").toBeFalsy();

    await deleteBTN.trigger('click');
    await flushPromises();
    await wrapper.vm.$nextTick();

    const selectedLaptop = wrapper.vm.selectedLaptop
    const deleteLaptop = wrapper.find("#delete-laptop")

    await deleteLaptop.trigger('click');
    await flushPromises();
    await wrapper.vm.$nextTick();

    expect(laptopsService.asyncDeleteById).toHaveBeenCalledWith(selectedLaptop.ean);

});