import {mount} from "@vue/test-utils";
import headerComponent from "@/components/HeaderComponent";
import {ImagesAdaptor} from "@/services/ImagesAdaptor";
import i18n from "@/i18n";
// import nl from "@/locales/nl.json"
// import en from "@/locales/en.json"
// import fr from "@/locales/fr.json"

describe('name', () => {
// const translations = {
//     en: en,
//     fr: fr,
//     nl: nl
// };

const mockI18n = {
    t: (key, locale = 'nl') => {
        return translations[locale][key];
    }
};

let wrapper;

beforeEach(function () {
    wrapper = mount(headerComponent, {
        global: {
            provide: {
                imagesService: ImagesAdaptor, // Replace with your mocked service
            }
        }
    });

    wrapper.setData({$i18n: 'nl'})
})

it('should renders properly', function () {
    expect(wrapper.element.children.length,
        `main page starting with ${wrapper.element.tagname} has no child elements`)
        .toBeGreaterThan(0);
    expect(wrapper.html(),
        `The header did not render the pageTitle`)
        .toContain(wrapper.vm.pageTitle);
});
});