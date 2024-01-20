 import WelcomeComponent from "@/components/WelcomeComponent";
import {Laptop} from "@/models/laptop";
import {createMemoryHistory, createRouter} from "vue-router";
import {LaptopsAdaptor} from "@/services/LaptopsAdaptor";
import {mount} from "@vue/test-utils";
import {reactive} from "vue";
import i18n from "@/i18n";
import laptopListComponent from "@/components/LaptopListComponent";

let laptop1, laptop2;
let wrapper;

const mockRoutes = [
    {
        path: '/',
        component: WelcomeComponent
    },
];

beforeEach(async function () {
    laptop1 = new Laptop(10, 1, "mockBrand", "mockDesc", "mockProcessor",
        "mockRam", "mockStorage", "mockGpu", 10, 10, "mockOs", 1);
    laptop2 = new Laptop(11, 2, "mockBrand", "mockDesc", "mockProcessor",
        "mockRam", "mockStorage", "mockGpu", 10, 10, "mockOs", 1);

    const router = createRouter({
        history: createMemoryHistory(),
        routes: mockRoutes,
    })

    const mockedLaptops = [laptop1, laptop2]
    const laptopsService = new LaptopsAdaptor('http://localhost:8086/api')

    jest.spyOn(laptopsService, "asyncFindAll").mockResolvedValue(mockedLaptops);
    jest.spyOn(laptopsService, "asyncFindById").mockImplementation(async (ean) => {
        const foundImage = mockedLaptops.find(image => image.ean === ean);
        return foundImage ? foundImage : null;
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

    wrapper = await mount(laptopListComponent, {
        global: {
            provide: {
                "laptopsService": reactive(laptopsService)
            },
            plugins: [i18n, router]
        }
    });

    await wrapper.vm.$router.isReady();
})
it('should renders properly', function () {

    expect(wrapper.element.children.length,
        `main page starting with ${wrapper.element.tagname} has no child elements`)
        .toBeGreaterThan(0);
});

it('should have a list of laptops', async function () {
    await wrapper.vm.$nextTick();
    console.log(wrapper.html());
    console.log(wrapper.vm.laptops)

    const laptopDivs = wrapper.findAll(".row justify-content-between").length
    const laptopList = wrapper.vm.laptops.length

    expect(wrapper.vm.laptops.length).toBeGreaterThan(0);
    expect(laptopDivs,
        'List of laptops are not loaded')
        .toBe(laptopList)
}); 