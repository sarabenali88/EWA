import {mount} from "@vue/test-utils";
import headerComponent from "@/components/HeaderComponent";
import {ImagesAdaptor} from "@/services/ImagesAdaptor";
import i18n from "@/i18n";
import {reactive} from "vue";

let wrapper

beforeEach(async function () {
    let imagesService = new ImagesAdaptor('http://localhost:8086/api')
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
    expect(wrapper.html(),
        `The header did not render the pageTitle`)
        .toContain(wrapper.vm.pageTitle);
});
