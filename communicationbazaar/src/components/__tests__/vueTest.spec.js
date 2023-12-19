import {mount} from "@vue/test-utils";
import headerComponent from "@/components/HeaderComponent";

let wrapper;

beforeEach(function () {
    wrapper = mount(headerComponent);
})

it('should renders properly', function () {
    expect(wrapper.element.children.length,
        `main page starting with ${wrapper.element.tagname} has no child elements`)
        .toBeGreaterThan(0);
    expect(wrapper.html(),
        `The header did not render the pageTitle`)
        .toContain(wrapper.vm.pageTitle);
});